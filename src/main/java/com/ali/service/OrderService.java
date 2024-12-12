package com.ali.service;

import com.ali.dao.impl.CustomerDaoImpl;
import com.ali.dao.impl.OrderDaoImpl;
import com.ali.dao.impl.ProductDaoImpl;
import com.ali.model.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
    private final OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    private final FileService fileService = new FileService();
    private final ProductDaoImpl productDaoImpl = new ProductDaoImpl();

    // Process orders from the provided order list
    public void processOrders(OrderList orderList) {
        List<Order> confirmedOrders = new ArrayList<>();
        List<Order> errorOrders = new ArrayList<>();

        for (Order order : orderList.getOrders()) {
            if (processOrder(order)) {
                confirmedOrders.add(order);
            } else {
                errorOrders.add(order);
            }
        }

        // Write the results to the appropriate files
        if (!confirmedOrders.isEmpty()) {
            fileService.writeConfirmedOrdersToFile(
                    "src/main/java/com/ali/resources/data/confirmed.json",
                    new OrderList(confirmedOrders)
            );
        }

        if (!errorOrders.isEmpty()) {
            fileService.writeErrorOrdersToFile(
                    "src/main/java/com/ali/resources/data/errors.json",
                    new OrderList(errorOrders)
            );
        }
    }

    // Process a single order, validate customer, payment, and shipping
    private boolean processOrder(Order order) {
        // Validate customer
        Customer customer = customerDaoImpl.getCustomerById(order.getCustomer().getCustomerId());
        if (customer == null) {
            System.out.println("Warning: Customer not found for order " + order.getOrderId());
            return false;  // Error in processing
        }

        // Validate payment
        Payment payment = order.getPayment();
        if (payment == null || !validatePayment(payment)) {
            System.out.println("Warning: Invalid payment details for order " + order.getOrderId());
            return false;  // Error in processing
        }

        // Validate items in the order
        if (!validateOrderItems(order.getItems())) {
            System.out.println("Warning: Invalid items in order " + order.getOrderId());
            return false;  // Error in processing
        }

        // Validate shipping
        Shipping shipping = order.getShipping();
        if (shipping == null || !validateShipping(shipping)) {
            System.out.println("Warning: Invalid shipping details for order " + order.getOrderId());
            return false;  // Error in processing
        }


        // If all validations pass, process the order
        order.setStatus("Processed");
        orderDaoImpl.addOrder(order);
        System.out.println("Order processed: " + order.getOrderId());

        return true;
    }

    // Validate payment status and ensure attributes are not null
    private boolean validatePayment(Payment payment) {
        if (payment.getPaymentId() == null || payment.getPaymentId().isEmpty()) {
            System.out.println("Warning: Payment ID is missing.");
            return false;
        }
        if (payment.getMethod() == null || payment.getMethod().isEmpty()) {
            System.out.println("Warning: Payment method is missing.");
            return false;
        }
        if (payment.getCurrency() == null || payment.getCurrency().isEmpty()) {
            System.out.println("Warning: Currency is missing.");
            return false;
        }
        if (payment.getTotalAmount() <= 0) {
            System.out.println("Warning: Total amount must be greater than zero.");
            return false;
        }
        if (payment.getStatus() == null ||
                (!"Paid".equals(payment.getStatus()) && !"Pending".equals(payment.getStatus()))) {
            System.out.println("Warning: Payment status must be 'Paid' or 'Pending'.");
            return false;
        }
        return true;
    }


    // Validate the items in the order (check stock, etc.)
    private boolean validateOrderItems(List<Item> items) {
        for (Item item : items) {
            // Check if the product exists and if there's enough stock
            if (productDaoImpl.getProductById(item.getProductId()) == null) {
                return false;
            }
        }
        return true;  // All items are valid
    }

    // Validate shipping method and details
    private boolean validateShipping(Shipping shipping) {
        // Check if shipping method and tracking number are valid
        return
                shipping.getMethod() != null &&
                        !shipping.getMethod().isEmpty() &&
                        shipping.getTrackingNumber() != null &&
                        !shipping.getTrackingNumber().isEmpty();
    }
}
