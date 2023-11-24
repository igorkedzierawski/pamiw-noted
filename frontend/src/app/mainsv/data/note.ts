import { LoggedUser } from "./loggeduser";

export interface Note {
    author: LoggedUser;
    title: string;
    content: string;
    createdAt: string;
    editedAt: string;
    id: number;
}
