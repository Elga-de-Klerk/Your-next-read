# 📚 Your Next Read

> Not sure what to read next? Neither was I, so I built a thing.

A cozy little book recommendation app, built while learning Spring Boot and Vue. Search for books, build a to-be-read pile, and let the app pick your next read for you. 🌙

## 🗂️ What's in here

This is a monorepo — two little projects living together in harmony:

- 🍃 [`springboot-backend/`](./springboot-backend) — the brains (Java, Spring Boot, PostgreSQL)
- 🎨 [`vue-frontend/`](./vue-frontend) — the face (Vue 3, TypeScript, Vite)

Each has its own README with the nitty-gritty setup details.

## ✅ Before you start, you'll need

- ☕ Java 21+
- 🟢 Node 22.12+
- 🐳 Docker (so Postgres has somewhere to live)

## 🚀 Quick start

1. Wake up the database: `docker compose up -d` (from here, the repo root)
2. Fire up the backend — see [`springboot-backend/README.md`](./springboot-backend/README.md)
3. Fire up the frontend — see [`vue-frontend/README.md`](./vue-frontend/README.md)
4. Search for a book, add it to your TBR shelf, then hit "Pick for me" 🎲📖

## 🧰 Built with

- **Backend:** Spring Boot · Spring Data JPA · PostgreSQL · Swagger (springdoc-openapi)
- **Frontend:** Vue 3 · TypeScript · Vite · Sass
- **Book data:** [Open Library](https://openlibrary.org/developers/api) — free, no key needed, no drama

## 🌱 Current features

- Search Open Library and import real book data (title, author, description, page count, cover, tags)
- Build a personal TBR (to-be-read) list
- Get a random pick from your TBR list when you can't decide