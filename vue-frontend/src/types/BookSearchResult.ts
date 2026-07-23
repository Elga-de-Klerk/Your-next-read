export interface BookSearchResult {
    externalId: string | null;
    title: string;
    author: string;
    description: string | null;
    pageCount: number | null;
    publishedYear: number | null;
    coverImageUrl: string | null;
    tags: string[];
}