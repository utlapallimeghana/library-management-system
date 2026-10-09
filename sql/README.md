# Library Management System using Java and SQL

## 📌 Project Overview

The Library Management System is a console-based application developed using Java and MySQL. It helps manage library books, student records, and book issue and return operations.

The project uses Java for application logic and MySQL for storing and retrieving data from the database.

## 🎯 Objectives

* To maintain book records efficiently.
* To register and manage student details.
* To issue books to students.
* To record book returns and update available copies.
* To store and retrieve library information using SQL.

## 🛠️ Technologies Used

* **Programming Language:** Java
* **Database:** MySQL
* **Database Connectivity:** JDBC (Java Database Connectivity)
* **IDE:** Eclipse
* **Version Control:** Git and GitHub

## ✨ Features

1. **Add Books:** Add new books with title, author, and number of copies.
2. **View Books:** Display book details and available copies.
3. **Register Students:** Store student names and email addresses.
4. **Issue Books:** Issue available books to registered students.
5. **Return Books:** Record book returns and update availability.
6. **Database Management:** Store records in MySQL tables.

## 📂 Project Structure

```text
Library-Management-System/
│
├── schema.sql
├── DBConnection.java
├── LibraryService.java
├── Main.java
└── README.md
```

## 🗄️ Database Design

The project uses a MySQL database named `library_db` with three tables:

* **books:** Stores book details and available copies.
* **students:** Stores student information.
* **issue_records:** Tracks book issues and returns.

## ⚙️ Installation and Setup

### Step 1: Install the Requirements

Install the following software:

* Java Development Kit (JDK)
* MySQL Server
* MySQL Workbench (optional, for managing the database)
* MySQL Connector/J (JDBC driver)

### Step 2: Create the Database

1. Open MySQL Workbench.
2. Open the `schema.sql` file.
3. Execute the SQL script to create the database and tables.

### Step 3: Configure Database Connectivity

Open `DBConnection.java` and update the database username and password according to your MySQL configuration.

### Step 4: Add the JDBC Driver

Add the MySQL Connector/J JAR file to your Java project's classpath.

### Step 5: Compile and Run

Compile and run the Java files using your IDE or terminal, ensuring the JDBC driver is included in the classpath.

Run the `Main.java` file to start the application.

## ▶️ How to Use

1. Run the application.
2. Select an option from the menu.
3. Add books or register students.
4. Issue books using the relevant book and student IDs.
5. Return books using the issue record ID.
6. View the updated book availability.

## 📚 Learning Outcomes

* Understanding Java programming and object-oriented concepts.
* Connecting Java applications to MySQL using JDBC.
* Writing SQL queries for inserting, retrieving, and updating records.
* Using prepared statements for database operations.
* Implementing basic database relationships and transactions.

## 🚀 Future Enhancements

* Add a graphical user interface.
* Implement student login and administrator authentication.
* Add book search functionality.
* Generate reports for issued and returned books.
* Add due dates and fine calculations.

## 👩‍💻 Author

**Meghana Utlapalli**
