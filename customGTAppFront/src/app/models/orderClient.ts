import { OrderItem } from "./orderItem";
import { OrderOption } from "./orderOption";

export interface OrderClient {
    id: number;
    name: string;
    email: string;
    phoneNumber: string;
    county: string;
    city: string;
    address: string;
    postalCode: string;
    totalPrice: number;
    orderItems: OrderItem[];
    orderOption: OrderOption;
}