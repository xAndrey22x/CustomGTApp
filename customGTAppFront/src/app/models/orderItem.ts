import { OrderClient } from "./orderClient";
import { Product } from "./product";
import { ServiceProd } from "./serviceProd";

export interface OrderItem {
    id: number;
    quantity: number;
    price: number;
    product: Product;
    serviceProd: ServiceProd;
    orderClient: OrderClient;
}