# Custom GT Application ![GitHub](https://img.shields.io/github/license/xAndrey22x/CustomGTApp) ![GitHub stars](https://img.shields.io/github/stars/xAndrey22x/CustomGTApp) ![GitHub forks](https://img.shields.io/github/forks/xAndrey22x/CustomGTApp)

## Introduction

The Custom GT Application serves as an innovative web application designed for automotive enthusiasts and professionals alike. By offering a diverse array of customization services and parts for various car models, we aim to be the quintessential one-stop solution for all your car customization needs. Our objective is to bring together high-quality components plus outstanding services to transform your vehicle into a masterpiece of your choosing.

### Features

The application has a wide range of features aimed at reaching to every aspect of automotive customization:

- **Custom Parts**: We provide an extensive array of custom parts suitable for a wide range of car models, ensuring the perfect fit and enhancement for your vehicle.
- **Exclusive Services**:Our unique services include:
  - **Car Wrap**: Premium quality vinyl wrapping services designed to either rejuvenate your car's appearance or safeguard its original paintwork.
  - **Detailing**: Meticulous detailing services covering both the interior and exterior, employing only the finest materials for an impeccable finish.
  - **Custom Lights**: State-of-the-art custom lighting solutions to not only boost your car's aesthetic appeal but also enhance its visibility.
- **Additional Accessories**: Explore a wealth of extra accessories to further personalize and highlight your car's originality.

<p align="center">
  <img src="https://github.com/xAndrey22x/CustomGTApp/assets/126805939/29a4ec74-7dd5-4cc1-9d8f-b17314d291a5" width="600">
</p>

### Relationships between Tables

A deep understanding of our application's data model is essential to grasp the intricate operations of the Custom GT Application. The following are the essential relationships between our tables:

- **Product to Photo**: One-to-Many. This relationship allows a single product to be linked with multiple photos, showcasing it from various perspectives and details.
- **Service to Photo**: One-to-Many. In a similar vein, a single service can be represented by multiple photos, providing a visual narrative of the service process or its outcomes.
- **OrderItem to Order**: Many-to-One. This facilitates the inclusion of multiple items within a single order, enabling a comprehensive customization package in one transaction.
- **OrderItem to Product**: One-to-Many. Each order item corresponds to a distinct product, ensuring specificity in purchases, also multiple products can correspond to different order items.
- **OrderItem to Service**: One-to-Many. Likewise, each order item can also represent a distinct service, allowing for detailed customization services, also multiple services can correspond to different order items.

## Implementation Plan

### Backend Development

The following plan represents the structure of our backend application:

<p align="center">
  <img src="https://github.com/xAndrey22x/CustomGTApp/assets/126805939/808a80c4-bfc8-473c-a03b-4dd0b2ddad29" width="650">
</p>

The application utilizes a layered architecture approach, designed for separation of concerns and modularity. Below is a description of each layer:

**HTTP API**
- Serves as the application's entry point.
- Handles HTTP requests and routes them to the appropriate controller.

**Controller**
- Acts as an intermediary between the HTTP API and service layer.
- Processes incoming requests, validates them, and delegates actions to the service layer.
- Returns HTTP responses after the execution of business logic.

**Service**
- Contains the core business logic of the application.
- Coordinates the application's operations and manages the flow of data to and from the data layer.

**Data**
- Responsible for data persistence and retrieval operations.
- Communicates with the database to perform CRUD (Create, Read, Update, Delete) operations on entities.
- Mostly makes use of the JPA Repository to complete necessary tasks.

**Model**
- Defines the domain models or entities representing the database structure.
- Used across the application to transport data between layers, with attributes matching the database schema.

This structure follows the Spring MVC (Model-View-Controller) framework, ensuring maintainable, testable, and scalable code. The separation allows each layer to be independently managed and evolved.

During this stage, we have focused on developing the server-side logic by leveraging the powerful features of the Java Spring Framework. Establishing a strong foundation for our application was our main priority. Below is an outline of the key components of our application's architecture and features:

### Database Management
- **Secure and Efficient Schema**: Our database schema is designed to handle intricate relationships between products, services, and orders securely and efficiently.

### CRUD Operations
- **Manage Data Seamlessly**: Our CRUD operations enable:
  - **Additions**: New products, services, and accessories can be added to the application.
  - **Retrievals**: Existing offerings are retrievable for display and management purposes.
  - **Updates**: Offerings details are updated as needed to ensure they remain current and accurate.
  - **Deletions**: Offerings that are no longer available or relevant can be deleted.

### API Development
- **Secure Data Transmission**: We have developed a comprehensive set of APIs to facilitate secure and efficient communication between the frontend and backend.
  - ***Product Table***
    - /product/all - list all the products. *GET*
    - /product/{productId}/photos - list all the photos of a product based on the product id. *GET*
    - /product/find/{id} - find a product based on the id provided. *GET*
    - /product/add - add a product, the product will be received as a request body. *POST*
    - /product/update - update a product, the product will be received as a request body. *PUT*
    - /product/delete/{id} - delete a product based on the id provided. *DELETE*
    - /product/updateQuantity/{productId} - update the quantity of a product, quantity as a request parameter, parameter name is 'quantity'. *PUT*
    - /product/updatePrice/{productId} - update the price of a product, price as a request parameter, parameter name is 'price'. *PUT*
  - ***Service Table***
    - /service/all - list all the services. *GET*
    - /service/find/{id} - find a service based on the id provided. *GET*
    - /service/add - add a service, the service will be received as a request body. *POST*
    - /service/update - update a service, the new service will be received as a request body. *PUT*
    - /service/delete/{id} - delete a service based on the id provided. *DELETE* 
    - /service/{serviceId}/photos - list all the photos of a service based on the service id. *GET*
    - /service/updatePrice/{serviceId} - update the price of a service based on the service id, price as a request parameter, parameter name is 'price'. *PUT*
  - ***Photo Table***
    - /photo/all - list all the photos. *GET* 
    - /photo/addProduct/{productId} - add a photo to a product based on his id, the photo will be received as a request body. *POST*
    - /photo/addService/{serviceId} - add a photo to a service based on his id, the photo will be received as a request body. *POST*
    - /photo/updateProduct - update photo of a product, the photo will be received as a request body. *PUT* 
    - /photo/updateService - update photo of a service, the photo will be received as a request body. *PUT*
    - /photo/delete/{photoId} - delete photo based on the photo id. *DELETE*
  - ***OrderClient Table***
    - /order/all - list all the orders. *GET*
    - /order/add - add an order, the order will be received as a request body. *POST*
    - /order/update - update an order, the order will be received as a request body. *PUT*
    - /order/delete/{id} - delete an order based on the id provided. *DELETE*
  - ***OrderItem Table***
    - /addOrderProduct/{productId}/{orderId} - add a product to an order, the order item will be received as a request body. *POST*
    - /addOrderService/{serviceId}/{orderId} - add a service to an order, the order item will be received as a request body. *POST*
  - ***OrderOption Table***
    - /addOrderOptions/{orderClientId} - add order options to an order, the order options will be received as a request body. *POST*
    - /updateOrderNewsletter/{orderClientId} - update newsletter for an order, the newsletter will be received as a request parameter, parameter name is 'newsletter'. *PUT* 
    - /updateOrderConfirmed/{orderClientId} - update order confirmation for an order, the order confirmation will be received as a request parameter, parameter name is 'orderConfirmed'. *PUT*

### Observer Design Pattern Enhancements
- **New Features**: We've added a new table for client details, which includes order confirmation status and newsletter registration.
  - **Email Notifications**: Clients are now notified via email when a new product is added or an order is confirmed.
  - **Observer Implementation**: These notifications are managed using the Observer design pattern, which ensures that clients receive timely updates based on the relevant actions in the database.
 
<p align="left">
  <img src="https://github.com/xAndrey22x/CustomGTApp/assets/126805939/e9da2a4f-1672-4901-ab94-572ffd68cad1" width="250">
</p>

- **OrderOptions to OrderClient**: A one-to-one relationship exists where every order placed by a client is linked with options for newsletter subscription and order status.

### Unit Testing
- **Comprehensive Tests**: We have implemented unit testing across all layers:
  - **Service Layer**: All business logic is thoroughly tested to ensure functionality and reliability.
  - **Observer Layer**: The functionality of the Observer pattern is verified through various scenarios to ensure correct behavior.
  - **Data Layer**: Database interactions are tested to confirm both the integrity and security of data operations.

### Frontend Development

We promised our users an immersive experience by building a strong foundation through backend development, which prepared the way for a dynamic, responsive, and user-friendly frontend. The flexibility and robustness of our backend guarantee that, even when we shift our attention to the frontend, we will stay firm in our commitment to producing a product that is both aesthetically pleasing and exceptionally functional.
