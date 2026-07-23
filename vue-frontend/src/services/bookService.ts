import type { Book } from '../types/Book';
import type { BookDetails } from "../types/BookDetails.ts";

const API_BASE = 'http://localhost:8080/api';

export async function searchBooks(query: string, limit = 10): Promise<Book[]> {
    const response = await fetch(`${API_BASE}/books/search?query=${encodeURIComponent(query)}&limit=${limit}`);
    if (!response.ok) throw new Error(`Search failed: ${response.status}`);
    return response.json();
}

export async function addToTbr(book: Book): Promise<Book> {
    const response = await fetch(`${API_BASE}/tbr`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(book),
    });
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

export async function fetchBookDetails(externalId: string): Promise<BookDetails> {
    const response = await fetch(`${API_BASE}/books/details?externalId=${encodeURIComponent(externalId)}`);
    if (!response.ok) throw new Error(`Failed to fetch details: ${response.status}`);
    return response.json();
}

export async function fetchBookById(id: number): Promise<Book> {
    const response = await fetch(`${API_BASE}/books/${id}`);
    if (!response.ok) throw new Error(`Failed to fetch book: ${response.status}`);
    return response.json();
}

export async function fetchNextRead(): Promise<Book> {
    const response = await fetch(`${API_BASE}/tbr/next-read`);
    if (!response.ok) throw new Error(`Failed to get next read: ${response.status}`);
    return response.json();
}