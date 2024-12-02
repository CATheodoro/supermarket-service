# Supermarket Service API
This project implements a supermarket system with cart and promotion management. It includes endpoints for managing products, cart items, orders, and promotions. It is built using Spring Boot and includes RESTful APIs that handle operations related to the supermarket system.

## Sections
- **[Technologies](#technologies)**
- **[Setup Instructions](#setup-instructions)**
- **[Get Start Docker](#get-start-docker)**
- **[API Documentation](#api-documentation)**
- **[Testing](#testing)**
- **[Conclusion](#conclusion)**

## Technologies
- **Spring Boot 3.4: Backend framework**
- **Spring Data JPA: Database integration**
- **PostgreSQL: Database**
- **H2: In-memory database for testing**
- **Spring HATEOAS: Hypermedia support**
- **Swagger: API documentation**
- **Wiremock: Mocking external services** //Work in progress

## Setup Instructions
### Pre requisites
- **Java 23** 
- **Maven**
- **PostgreSQL for production setup (H2 is used for testing)**
- **Running the Application**

### Running the Application
1. **Clone the repository:** 
```Git Clone
https://github.com/your-username/supermarket-service.git
```

2. **Navigate into the project directory:**

```Directory
cd supermarket-service
````
3. **Build and run the application:**

```Build
mvn spring-boot:run
```
The application will run on `http://localhost:8081`.

### Database Configuration
The database configurations, whether for production or testing, are already set up, but if you wish to modify them, you can configure them in `application.properties-prod` or `application.properties-test` file.

## Get Start Docker
Inside the `src/main/resources/supermarket-service-docker/docker-compose.yml` folder, execute the following command to start the application within Docker:
```Docker Compose
docker-compose up --build
```
This command will build and start the necessary Docker containers to run Postgress database from application.

## API Documentation
Once the application is running, you can access the Swagger API documentation at:

Swagger UI: http://localhost:8080/swagger-ui.html
API Documentation
The API consists of multiple endpoints that manage carts, products, promotions, and orders. Below are the main endpoints exposed by the service.

## Cart Endpoints
### Create a Cart
- ### POST `/carts`
- Request body: `CartRequest`
- Creates a new cart.
### Get All Carts
- ### GET `/carts`
- Query Parameters: `page`, `size`, `sort`
- Returns a paginated list of carts.
### Get Cart by ID
- ### GET `/carts/{id}`
- Path Variable: `id` (Cart ID)
- Returns the details of a specific cart.
### Clear Cart
- ### DELETE `/carts/{id}/clear`
- Path Variable: `id` (Cart ID)
- Clears all items in the specified cart.
## Cart Item Endpoints
### Add Item to Cart
- ### POST `/cart-items/add`
- Request body: `CartItemRequest`
- Adds a product to the specified cart.
- Get Cart Item by ID
- ### GET `/cart-items/{id}`
Path Variable: id (CartItem ID)
Returns the details of a specific cart item.
## Product Endpoints
### Create a Product
- ### POST `/products`
- Request body: `ProductRequest`
- Adds a new product to the inventory.
### Get All Products
- ### GET `/products`
- Query Parameters: `page`, `size`, `sort`
- Returns a paginated list of products.
- Get Product by ID
- ### GET `/products/{id}`
- Path Variable: `id` (Product ID)
- Returns the details of a specific product.
## Order Endpoints
### Create an Order
- ### `POST /orders`
- Request Parameter: `idCart`
- Creates an order based on the cart contents.
- Get Order by ID
- ### GET `/orders/{id}`
- Path Variable: `id` (Order ID)
- Returns the details of a specific order.
## Promotion Endpoints
### Create a Promotion
- ### POST `/promotions`
- Request body: `PromotionRequest`
- Creates a new promotion for a product.
- Get All Promotions
- ### GET `/promotions`
- Returns a list of all active promotions.
- Get Promotion by ID
- ### GET `/promotions/{id}`
- Path Variable: `id` (Promotion ID)
- Returns the details of a specific promotion.

## Testing
### Unit Tests
To run the unit tests, execute the following command:
```Test
mvn test
```

### Integration Tests
For integration tests, the in-memory H2 database will be used. You can run them with the same `mvn test` command.

## Conclusion
This project implements a comprehensive supermarket service that manages shopping carts, products, orders, and promotions, built with Spring Boot and leveraging various modern Java technologies. The application allows for:

Cart Management: Users can create, retrieve, and manage carts, add products, and clear the cart.
Product and Promotion Management: The service supports product creation, retrieval, and promotion management, ensuring efficient handling of promotional offers.
Order Management: Users can create orders based on their shopping carts and retrieve detailed order information.
RESTful API Design: All features are exposed through a clean and maintainable RESTful API, utilizing the HATEOAS approach for linking resources.
With Spring Boot's powerful capabilities, such as Spring Data JPA for database interaction and validation for ensuring data integrity, this application is robust and scalable. It also incorporates best practices like separation of concerns and proper exception handling, providing a seamless user experience.

Additionally, the project is designed with extensibility in mind, allowing easy integration of additional services such as payment processing, user authentication, and more.

This application is an excellent foundation for a fully-functional supermarket system, offering flexibility and scalability for future growth and enhancement.