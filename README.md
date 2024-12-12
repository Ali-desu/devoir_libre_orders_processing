
# 🎉 Welcome to the Order Processing Party! 🎉

----------

## **Project Vibe:**
The **Order Processing System** is like the magic wand for orders, zipping through customer data, product stocks, and payments while keeping everything neatly organized in JSON files. It’s your go-to assistant for handling orders, validating them, and ensuring everything runs smoothly — from confirmations to errors — all recorded in style!

----------

## **✨ Features That’ll Make Your Life Easier:**
- 📝 **Order Reading & Validation**: It's like a detective for your orders, making sure everything checks out!
- 👥 **Customer, Product, and Payment Handling**: Manage your people, stuff, and payments without breaking a sweat.
- 💾 **JSON Output**: The system generates neat little files to keep confirmed orders and errors organized.
- 🏢 **Database Integration**: Store your customer and order data in a lovely MySQL database. You’ll never lose track of your orders again!

----------

## **💻 Tech Stack We’re Rocking:**
- **Programming Language**: Java (because who doesn’t love a good Java brew?)
- **Libraries**:
  - **Jackson** (your JSON superhero for parsing and writing)
  - **JUnit** (for keeping those bugs under control with some good old unit testing)
  - **MySQL** (for that solid database foundation)

----------

## **🛠️ Setup Instructions — Ready, Set, Go!**

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Ali-desu/devoir_libre_orders_processing.git
   cd OrderProcessingSystem
  

2. **Install Dependencies**: Ensure Java and Maven are ready to roll.

3. **Create Your Database**:
   - First, create a MySQL database called `devoir_db`.

4. **Import Database Schema**:
   - Use phpMyAdmin to import the **tables.sql** file from the **util** folder. It has all the SQL commands to create tables and insert test data.
   - Don't forget to update **DatabaseUtil.java** with your correct database connection URL. If you're using the default MySQL port, change it to:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/devoir_db";
     ```

5. **Build the Project**:
   - Time to unleash the magic with Maven! 
   ```bash
   mvn clean install
   ```

6. **Run the Project**:
   - Let the show begin by running it with:
   ```bash
   mvn exec:java
   ```

----------

## **🤝 Contribute Like a Pro:**
- Fork the repository
- Create a new branch
- Make your awesome changes
- Commit them like a legend
- Push your branch to the cloud
- Open a Pull Request and get the party started!

----------

## **🙌 Big Thanks To:**
- **Jackson** for making JSON look easy-peasy 🍊
- **JUnit** for keeping things bug-free 🐞
- **MySQL** for being the steady rock of our database world 💪

----------

