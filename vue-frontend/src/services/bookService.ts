import type { Book } from '../types/Book';

const API_BASE = 'http://localhost:8080/api';

export async function fetchBooks(): Promise<Book[]> {
    const response = await fetch(`${API_BASE}/books`);
    if (!response.ok) throw new Error(`Failed to fetch books: ${response.status}`);
    return response.json();
}

export async function searchAndImportBooks(query: string, limit = 10): Promise<Book[]> {
    const response = await fetch(
        `${API_BASE}/books/import?query=${encodeURIComponent(query)}&limit=${limit}`,
        { method: 'POST' }
    );
    if (!response.ok) throw new Error(`Search failed: ${response.status}`);
    return response.json();
}

export async function addToTbr(bookId: number): Promise<Book> {
    const response = await fetch(`${API_BASE}/tbr/${bookId}`, { method: 'POST' });
    if (!response.ok) throw new Error(`Failed to add to TBR: ${response.status}`);
    return response.json();
}

export async function removeFromTbr(bookId: number): Promise<Book> {
    const response = await fetch(`${API_BASE}/tbr/${bookId}`, { method: 'DELETE' });
    if (!response.ok) throw new Error(`Failed to remove from TBR: ${response.status}`);
    return response.json();
}

export async function fetchTbrList(): Promise<Book[]> {
    const response = await fetch(`${API_BASE}/tbr`);
    if (!response.ok) throw new Error(`Failed to fetch TBR list: ${response.status}`);
    return response.json();
}

export async function fetchNextRead(): Promise<Book> {
    const response = await fetch(`${API_BASE}/tbr/next-read`);
    if (!response.ok) throw new Error(`Failed to get next read: ${response.status}`);
    return response.json();
}