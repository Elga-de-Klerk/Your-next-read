export interface Book {
    id: number;
    title: string;
    author: string;
    description: string | null;
    pageCount: number | null;
    publishedYear: number | null;
    coverImageUrl: string | null;
    tags: { id: number; name: string }[];
}