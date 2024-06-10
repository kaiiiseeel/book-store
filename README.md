![image](https://img.shields.io/badge/Java-17-blue) 
![image](https://img.shields.io/badge/Spring_Boot-3.2-Green)
![image](https://img.shields.io/badge/License-MIT-orange)


# 📚 Online Bookstore API
Welcome to the Online Book Store API!
This project is designed to provide a seamless and efficient way to manage an online book store's inventory, customer orders, and user accounts.
Whether you are an independent bookstore owner looking to digitize your catalog or a developer seeking a robust backend solution for an e-commerce platform,
this API is tailored to meet your needs.

## ✨ Project inspiration
The inspiration for this project stemmed from the need for a modern, flexible, and easy-to-use backend system that can handle the complexities
of managing an online book store. In an age where digital transformation is key to staying competitive, many bookstores still struggle with outdated systems
that are not scalable or adaptable to the evolving market demands. This project aims to bridge that gap by providing a powerful API built on reliable and well-supported
technologies like Java, Spring Boot, and MySQL.

## 🛠️ Technologies
- **Java**: The primary programming language used for developing the API. 
- **Spring Boot**: A framework that simplifies the development of Java applications by providing pre-configured templates and reducing boilerplate code. It helps in quickly setting up the API with minimal configuration.
- **MySQL**: Database management system
- **Spring Data JPA**: A part of the Spring framework that provides easy integration with JPA (Java Persistence API) to simplify data access and manipulation.
- **Spring Security**: Handles user authentication, authorization, and protection against common security threats.
- **Spring Web**: Simplifies the development of web applications by providing built-in support for common web functionalities.
- **Maven**: A build automation tool used for project management and dependency management.
- **Swagger**: Interactive API documentation and testing
- **Testcontainers, JUnit & Mockito**: For testing
- **GitHub Actions**: CI (Continuous integration) tool
- **Checkstyle plugin**: Helps to keep code style clean and consistent

## 💡 How to install

1. **Clone repository to your machine**
```shell
git clone https://github.com/kaiiiseeel/book-store.git
cd book-store
```
2. **Open the application.properties file located in `src/main/resources`, configure it**
```properties
spring.datasource.url=jdbc:mysql://localhost/your_database_name?serverTimezone=UTC
spring.datasource.username=your_db_user_name
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
```

3. **Add the `.env` file and configure it**
```properties
MYSQLDB_USER=your_db_user_name
MYSQLDB_ROOT_PASSWORD=your_db_password
MYSQLDB_LOCAL_PORT=3307
MYSQLDB_DOCKER_PORT=3306
JWT_SECRET=useSomeLongAndReallySecretStringForThis
JWT_EXPIRATION=any number required in your project (ex. 1440000)

SPRING_LOCAL_PORT=8088
SPRING_DOCKER_PORT=8080
DEBUG_PORT=5005
```

4. **Add all needed dependencies and build project using Maven**
```shell
mvn clean install
```

5. **Build and run your docker containers via:**
```shell
docker-compose up
```

## ❗ How to use

1. Open documentation at http://localhost:8088/swagger-ui.html
2. Use tools like Postman or cURL to interact with API

## 🏣 Postman collection

To facilitate testing and exploration of our Online Bookstore API, we have provided a Postman collection. This collection is a comprehensive set of pre-configured API requests that demonstrate the functionality of the API.

[![Open in postman](https://run.pstmn.io/button.svg)](https://www.postman.com/flight-cosmonaut-8680004/workspace/kaiiiseeel/collection/34443387-258e23b2-2831-4b7f-a5e7-7ac546ccdb80?action=share&creator=34443387)

## 🚀 Project functionality

### 🔑 Authentication

<details>
<summary>Endpoints for user authentication</summary>

- **Register a new user**
    - Request: `POST /api/auth/registration`
```json
  {
    "email": "usermail@gmail.com",
    "password": "awesomePassword123",
    "repeatedPassword": "awesomePassword123",
    "firstName": "Bob",
    "lastName": "Alison"
  }
```
- **Login user**
  - Request: `POST /api/auth/login`
```json
{
    "email": "usermail@gmail.com",
    "password": "awesomePassword123"
}
```

In response to login request we will get JWT: 
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTcxODA0NDA0MiwiZXhwIjoxNzE4MDU4NDQyfQ.xh2soBCL2oDE21NdqEeNj9hyM9Z7pcGB2dH22Z8w1Fo"
}
```

We will use it in our future request to secure endpoints where authentication required

To use it in Postman, go to the auth section,
select the bearer token type and paste the resulting token into the appropriate field
![token-usage.png](token-usage.png)
</details>

#### **Examples:** 

To test the application you can use already created users

- **Admin**:
  - ```json
    {
    "email": "john.doe@example.com",
    "password": "pass1234"
    }
    ```
- **User**: 
  - ```json
    {
    "email": "bob.jackson@example.com",
    "password": "pass0000"
    }
    ```
 


### 📖 Book
<details>
<summary>Endpoints for book management</summary>

- User endpoints
    - **Get all books**
        - Request: `GET /api/books`
    - **Get a specific book by ID**
        - Request: `GET /api/books/{id}`
- Admin endpoints
    - **Add new book**
        - Request: `POST /api/books`
    - **Update existing book**
        - Request: `PUT /api/books/{id}`
    - **Delete book** 
        - Request: `DELETE /api/books/{id}`
</details>

### 📂 Category
<details>
<summary>Endpoints for category management</summary>

- User endpoints
    - **Get all categories**
        - Request: `GET /api/categories`
    - **Get a specific category by ID**
        - Request: `GET /api/categories/{id}`
    - **Get all books with a specific category**
        - Request: `GET /api/categories/{id}/books`
- Admin endpoints
    - **Add new category**
        - Request: `POST /api/categories`
    - **Update existing category**
        - Request: `PUT /api/categories/{id}`
    - **Delete category**
        - Request: `DELETE /api/categories/{id}`
</details>

### 🛒 Shopping Cart
<details>
<summary>Endpoints for shopping cart management</summary>

- User endpoints
    - **Get shopping cart**
        - Request: `GET /api/cart`
    - **Add a book to a shopping cart**
        - Request: `POST /api/cart`
    - **Update item quantity in shopping cart**
        - Request: `PUT /api/cart-items/{cartItemId}`
    - **Delete item from shopping cart**
        - Request: `DELETE /api/cart-items/{cartItemId}`
</details>

### 📦 Order
<details>
<summary>Endpoints for order management</summary>

- User endpoints
    - **Get all orders**
        - Request: `GET /api/orders`
    - **Get all items of an order**
        - Request: `GET /api/{orderId}/items`
    - **Get a specific item of a specific order**
        - Request: `GET /api/{orderId}/items/{orderItemId}`
    - **Create a new order**
        - Request: `POST /api/orders`
- Admin endpoints
    - **Update order status**
        - Request: `PATCH /api/orders/{orderId}`
</details>


## 🔥 Challenges Faced

- **Security and Authentication** - Implementing secure authentication and authorization mechanisms to protect user data. Solution - use of JWT.
<br></br>
- **API documentation** - Providing clear and comprehensive documentation for the API to facilitate ease of use by other developers. Solution - swagger for generation interactive documentation
<br></br>
- **Code Quality and Consistency** - Ensuring the codebase remains clean, maintainable, and adheres to best practices throughout the development lifecycle. Solution - checkstyle plugin, CI tool
<br></br>
- **Handling Database Migrations** - Managing database schema changes in a reliable and maintainable way across different environments. Solution - Liquibase was integrated to handle database migrations. 

## Author

- LinkedIn - [Mykola Skrypalov](https://www.linkedin.com/in/mykola-skrypalov-978492257/)
- GitHub - [@kaiiiseeel](https://github.com/kaiiiseeel)

## Licence

Project Online Bookstore is distributed under the MIT licence.

