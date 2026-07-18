# 📚 Your Next Read

> Don't know what to read next? Neither did I, so I built a thing.

A cozy little book recommendation app, built while learning Spring Boot and Vue. Tell it your mood, get a book. That's the dream, anyway. 🌙

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
4. Go find your next read 📖

## 🧰 Built with

- **Backend:** Spring Boot · Spring Data JPA · PostgreSQL · Swagger (springdoc-openapi)
- **Frontend:** Vue 3 · TypeScript · Vite
- **Book data:** [Open Library](https://openlibrary.org/developers/api) — free, no key needed, no drama
