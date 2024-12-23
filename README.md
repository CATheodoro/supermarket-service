﻿# Supermarket Service API
This project implements a supermarket system with cart and promotion management. It includes endpoints for managing products, cart items, orders, and promotions. It is built using Spring Boot and includes RESTful APIs that handle operations related to the supermarket system.

## Sections
- **[Technologies](#technologies)**
- **[Setup Instructions](#setup-instructions)**
- **[Get Start Docker](#get-start-docker)**
- **[API Documentation](#api-documentation)**
- **[Testing](#testing)**
- **[Wiremock Testing](#wiremock-testing)**
- **[Conclusion](#conclusion)**

## Technologies
- **Spring Boot 3.4: Backend framework**
- **Spring Data JPA: Database integration**
- **PostgreSQL: Database**
- **H2: In-memory database for testing**
- **Spring HATEOAS: Hypermedia support**
- **Swagger: API documentation**
- **Wiremock: Mocking external services**

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

Swagger UI: http://localhost:8081/swagger-ui.html
API Documentation
The API consists of multiple endpoints that manage carts, products, promotions, and orders. Below are the main endpoints exposed by the service.

## Cart Endpoints
### Create a Cart
- ### POST `/api/carts`
- - Creates a new cart.
### Get All Carts
- ### GET `/api/carts`
- - Query Parameters: `page`, `size`, `sort`
- - Returns a paginated list of carts.
### Get Cart by ID
- ### GET `/api/carts/{id}`
- - Path Variable: `id` (Cart ID)
- - Returns the details of a specific cart.
### Clear Cart
- ### DELETE `/api/carts/{id}/clear`
- - Path Variable: `id` (Cart ID)
- - Clears all items in the specified cart.
## Cart Item Endpoints
### Add Item to Cart
- ### POST `/api/cart-items/add`
- - Request body: `CartItemRequest`
- - Adds a product to the specified cart.
- - Get Cart Item by ID
- ### GET `/api/cart-items/{id}`
- - Path Variable: id (CartItem ID)
- - Returns the details of a specific cart item.
## Product Endpoints
### Create a Product
- ### POST `/api/products`
- - Request body: `ProductRequest`
- - Adds a new product to the inventory.
### Get All Products
- ### GET `/api/products`
- - Query Parameters: `page`, `size`, `sort`
- - Returns a paginated list of products.
- ### GET `/api/products/{id}`
- - Path Variable: `id` (Product ID)
- - Returns the details of a specific product.
## Order Endpoints
### Create an Order
- ### POST `/api/orders`
- - Request Parameter: `idCart`
- - Creates an order based on the cart contents.
- - Get Order by ID
- ### GET `/api/orders/{id}`
- - Path Variable: `id` (Order ID)
- - Returns the details of a specific order.
## Promotion Endpoints
### Create a Promotion
- ### POST `/api/promotions`
- - Request body: `PromotionRequest`
- - Creates a new promotion for a product.
- - Get All Promotions
- ### GET `/api/promotions`
- - Returns a list of all active promotions.
- ### GET `/api/promotions/{id}`
- - Path Variable: `id` (Promotion ID)
- - Returns the details of a specific promotion.

## Testing
### Unit Tests
To run the unit tests, execute the following command:
```Test
mvn test
```

### Integration Tests
For integration tests, the in-memory H2 database will be used. You can run them with the same `mvn test` command.

## Wiremock Testing
To run the wire mock, execute the following command:
```Test
\src\main\resources\supermarket-service-docker docker-compose up
```
The wiremock will run on `http://localhost:8080`.

Wiremock is disabled in supermarket-service. To enable it, go to `CalculateDiscountService.java` and change the comment `//Optional<Promotion> promotionOpt = promotionService.findFindByIdWithWiremock(item.getIdProduct());`. 

If you wish to modify the promotion, go to `src/main/resources/supermarket-service-docker/mappings/promotion-*` and adjust it to fit your specification.

## Promotion with Wiremock Endpoints
- ### GET `/api/promotions-wiremock`
- - Returns a list of all active promotions.
- ### GET `/api/promotions/wiremock`
- - supermarket-service consume wiremock and returns a list of all active promotions.

- ### GET `/api/promotions-wiremock/{id}`
- - Path Variable: `id` (Promotion ID)
- - Returns the details of a specific promotion.
- ### GET `/api/promotions/{id}/wiremock`
- - Path Variable: `id` (Promotion ID)
- - supermarket-service consume wiremock and returns the details of a specific promotion.
## Conclusion
This project implements a comprehensive supermarket service that manages shopping carts, products, orders, and promotions, built with Spring Boot and leveraging various modern Java technologies. The application allows for:

Cart Management: Users can create, retrieve, and manage carts, add products, and clear the cart.
Product and Promotion Management: The service supports product creation, retrieval, and promotion management, ensuring efficient handling of promotional offers.
Order Management: Users can create orders based on their shopping carts and retrieve detailed order information.
RESTful API Design: All features are exposed through a clean and maintainable RESTful API, utilizing the HATEOAS approach for linking resources.
With Spring Boot's powerful capabilities, such as Spring Data JPA for database interaction and validation for ensuring data integrity, this application is robust and scalable. It also incorporates best practices like separation of concerns and proper exception handling, providing a seamless user experience.

Additionally, the project is designed with extensibility in mind, allowing easy integration of additional services such as payment processing, user authentication, and more.

This application is an excellent foundation for a fully-functional supermarket system, offering flexibility and scalability for future growth and enhancement.

---

1. How long did you spend on the test? What would you add if you had more time?
   
Not much, I focused on completing the basic features and their rules while trying to maintain a loosely coupled design. To simplify navigation, I prioritized the core functionality, even though some features remain incomplete. I plan to finish those later and then move on to testing.

2. What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.
   
I used Java 23, but I didn't take advantage of any specific features from it. I just prefer to use the latest version to have access to its new functionalities if needed. What I used the most was the Stream API with the toList method introduced in Java 16.
   List<String> productIds = carts.stream()
   .flatMap(cart -> cart.getItems().stream())
   .map(CartItem::getIdProduct).toList();

3. What did you find most difficult?
  
What I found most challenging was managing my time more effectively.

4. What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do.
   
I didn't implement it, but I would use a more specific logging system like ELK (Elasticsearch, Kibana, and Logstash) since I'm more familiar with it.

5. Explain your interpretation of the list of requirements and what was delivered or not delivered and why.
   
The main feature I didn’t integrate was the WireMock system for testing, due to a lack of time. I plan to include it in future updates along with some smaller features. I also didn’t deploy the application to a host for financial reasons. Initially, I considered using Heroku, but I found out it is now a paid service.
