import { PhoneNumber } from "./PhoneNumber";

export interface Customer {
    id: number;
    name: string;
    phoneNumber: PhoneNumber;
}