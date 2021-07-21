import { Country } from "./Country";
export interface PhoneNumber {
    number: string;
    country:Country;
    isValid:boolean;
}