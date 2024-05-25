import { Photo } from "./photo";

export interface Product {
    id: number;
    name: string;
    description: string;
    price: number;
    carModel: string;
    quantity: number;
    photos: Photo[];
}