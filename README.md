# Custom GT Application

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
  <img src="https://github.com/xAndrey22x/CustomGTApp/assets/126805939/7d021867-a434-4ccf-be2e-22091f9d2e7f" width="600">
</p>

### Relationships between Tables

A deep understanding of our application's data model is essential to grasp the intricate operations of the Custom GT Application. The following are the essential relationships between our tables:

- **Product to Photo**: One-to-Many. This relationship allows a single product to be linked with multiple photos, showcasing it from various perspectives and details.
- **Service to Photo**: One-to-Many. In a similar vein, a single service can be represented by multiple photos, providing a visual narrative of the service process or its outcomes.
- **OrderItem to Order**: Many-to-One. This facilitates the inclusion of multiple items within a single order, enabling a comprehensive customization package in one transaction.
- **OrderItem to Product**: One-to-One. Each order item corresponds to a distinct product, ensuring specificity in purchases.
- **OrderItem to Service**: One-to-One. Likewise, each order item can also represent a distinct service, allowing for detailed customization services.

## Implementation Plan

### Backend Development

During this stage, we have only focused on developing the server-side logic by leveraging the powerful features of the Java Spring Framework. Establishing a strong foundation for the application was our main priority, and this includes:

- **Database Management**: Establishing a secure and efficient database schema to handle the intricate relationships between products, services, and orders.
- **CRUD Operations**: Implementing Create, Read, Update, and Delete operations for each table, thereby enabling seamless data management. This allows for:
  - Adding new products, services, and accessories to the application.
  - Retrieving information about existing offerings for display and management purposes.
  - Updating details of the offerings as needed, ensuring the information remains current and accurate.
  - Deleting offerings that are no longer available or relevant.
- **API Development**: Developing a comprehensive set of APIs to facilitate communication between the frontend and backend, ensuring data is transmitted securely and efficiently.

### Frontend Development

By laying a robust foundation through backend development, we set the stage for a dynamic, responsive, and user-friendly frontend, promising an immersive experience for our users. Our backend's adaptability and strength ensure that as we transition to focusing on the frontend, we remain committed to delivering a product that is not only visually appealing but also functionally superior.
