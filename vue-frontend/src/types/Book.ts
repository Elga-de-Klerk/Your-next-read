export interface Book {
    id: number | null;
    externalId: string | null;
    title: string;
    author: string;
    description: string | null;
    pageCount: number | null;
    publishedYear: number | null;
    coverImageUrl: string | null;
    onTbr: boolean;
    tags: string[];
}