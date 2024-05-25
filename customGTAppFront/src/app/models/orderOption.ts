import { OrderClient } from "./orderClient";

export interface OrderOption {
    id: number;
    newsletter: boolean;
    orderConfirmed: boolean;
    orderClient: OrderClient;
}