# 🎨 Frontend — Vue

The part you actually look at.

## What you need

- 🟢 Node 22.12+
- npm

## Running it

```powershell
npm install
npm run dev
```

Opens up at `http://localhost:5173` ✨

⚠️ **Heads up:** the backend needs to be running on `http://localhost:8080` too, or you'll just get errors instead of books.

## Building for real

```powershell
npm run build
```

## 🌱 What's here

- `SearchBooks.vue` — search Open Library and add results to your TBR list
- `TbrList.vue` — your current to-be-read shelf, with remove buttons
- `NextRead.vue` — the "Pick for me" button and its little catalog-card reveal

## 🎨 Styling

Each component has its own `.scss` file next to it (e.g. `NextRead.vue` + `NextRead.scss`), using `<style lang="scss" src="./ComponentName.scss" scoped>`. Shared styles (fonts, colors, base elements) live in `src/style.scss`.