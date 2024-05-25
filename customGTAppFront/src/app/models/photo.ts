import { Product } from "./product";
import { ServiceProd } from "./serviceProd";

export interface Photo {
    id: number;
    url: string;
    product: Product;
    serviceProd: ServiceProd;
}