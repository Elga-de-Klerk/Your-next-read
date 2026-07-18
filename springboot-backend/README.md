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

## 📖 API docs

Swagger UI, for poking around without writing curl commands:
`http://localhost:8080/swagger-ui/index.html`

## ⚙️ Config

Database settings live in `src/main/resources/application.yml`.

## 🌐 CORS

Configured to allow requests from `http://localhost:5173` (the Vue dev server) — see `WebConfig.java`. If you ever change the frontend's port, update the allowed origin there too.

## 🔌 Endpoints so far

**Books** (`BookController`)

| Method | Path                 | What it does                              |
|--------|----------------------|--------------------------------------------|
| GET    | `/api/books`         | Get every book                             |
| GET    | `/api/books/{id}`    | Get one specific book                      |
| POST   | `/api/books`         | Add a book by hand                         |
| POST   | `/api/books/import`  | Pull real books in from Open Library 🌐    |

**TBR list** (`TbrController`)

| Method | Path                     | What it does                          |
|--------|--------------------------|-----------------------------------------|
| GET    | `/api/tbr`               | Get your current TBR list              |
| POST   | `/api/tbr/{id}`          | Add a book to your TBR list            |
| DELETE | `/api/tbr/{id}`          | Remove a book from your TBR list       |
| GET    | `/api/tbr/next-read`     | Randomly pick your next read 🎲        |

*Single-user for now — no accounts yet, so it's one shared TBR list.*