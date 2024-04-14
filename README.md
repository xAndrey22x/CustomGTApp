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

During this stage, we have only focused on developing the server-side logic by leveraging the powerful features of the Java Spring Framework. Establishing a strong foundation for the application was our main priority, and this includes:

- **Database Management**: Establishing a secure and efficient database schema to handle the intricate relationships between products, services, and orders.
- **CRUD Operations**: Implementing Create, Read, Update, and Delete operations for each table, thereby enabling seamless data management. This allows for:
  - Adding new products, services, and accessories to the application.
  - Retrieving information about existing offerings for display and management purposes.
  - Updating details of the offerings as needed, ensuring the information remains current and accurate.
  - Deleting offerings that are no longer available or relevant.
- **API Development**: Developing a comprehensive set of APIs to facilitate communication between the frontend and backend, ensuring data is transmitted securely and efficiently.
- **New futures using the Observer Design Pattern**: Added a new table with the client's details, including whether or not the order has been confirmed and whether the client is registered to our newsletter. The Observer is used to implement all of this, and the clients will be informed via the appropriate actions in the DataBase.
<p align="left">
  <img src="https://github.com/xAndrey22x/CustomGTApp/assets/126805939/e9da2a4f-1672-4901-ab94-572ffd68cad1" width="250">
</p>

- **OrderOptions to OrderClient**: One-to-One. Every order of a client has the options for newsletter and order status.


### Frontend Development

We promised our users an immersive experience by building a strong foundation through backend development, which prepared the way for a dynamic, responsive, and user-friendly frontend. The flexibility and robustness of our backend guarantee that, even when we shift our attention to the frontend, we will stay firm in our commitment to producing a product that is both aesthetically pleasing and exceptionally functional.
