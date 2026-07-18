# 🍃 Backend — Spring Boot

The part that does the actual thinking.

## What you need

- ☕ Java 21+
- Maven (or just use the included `./mvnw`, no install needed)
- 🐘 PostgreSQL running (see the root README — `docker compose up -d`)

## Running it

```powershell
./mvnw spring-boot:run
```

Boots up at `http://localhost:8080` 🎉

## 🌐 CORS

Configured to allow requests from `http://localhost:5173` (the Vue dev server) — see `WebConfig.java`. If you ever change the frontend's port, update the allowed origin there too.

## 📖 API docs

Swagger UI, for poking around without writing curl commands:
`http://localhost:8080/swagger-ui/index.html`

## ⚙️ Config

Database settings live in `src/main/resources/application.yml`.

## 🔌 Endpoints so far

| Method | Path                 | What it does                              |
|--------|----------------------|--------------------------------------------|
| GET    | `/api/books`         | Get every book                             |
| GET    | `/api/books/{id}`    | Get one specific book                      |
| POST   | `/api/books`         | Add a book by hand                         |
| POST   | `/api/books/import`  | Pull real books in from Open Library 🌐    |