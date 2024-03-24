# Custom GT Application

### Web application which will provide:

- Custom parts for different models of cars
- Custom services like: car wrap, detailing and custom lights.
- Detailing interior and exterior with different materials.
- Several additional things.

<p aling="center">
  <img src = "https://github.com/xAndrey22x/CustomGTApp/assets/126805939/7d021867-a434-4ccf-be2e-22091f9d2e7f" width = "600">
</p>

### Relantionships between tables:

- Product to Photo: One-to-Many (A single product can have multiple photos).
- Service to Photo: One-to-Many (A single service can have multiple photos).
- OrderItem to Order: Many-to-One (Multiple order items can be included in a single order).
- OrderItem to Product: One-to-One (Each order item can be a product).
- OrderItem to Service: One-to-One (Each order item can be a service).

## Implementation

The backend of this web application will be implemented using Java Spring FrameWork, while the frontend will be implemented with Angular. We will begin by working on the backend, and we will eventually shift our focus to the frontend.
