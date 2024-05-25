import { Photo } from "./photo";

export interface ServiceProd {
    id: number;
    name: string;
    description: string;
    price: number;
    photos: Photo[];
}