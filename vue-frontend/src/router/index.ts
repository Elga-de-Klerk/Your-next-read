import { createRouter, createWebHistory } from 'vue-router';
import SearchView from "../views/SearchView/SearchView.vue";
import TbrView from "../views/TbrView/TbrView.vue";
import NextReadView from "../views/NextReadView/NextReadView.vue";
import BookDetailsView from "../views/BookDetailsView/BookDetailsView.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', redirect: '/search' },
        { path: '/search', name: 'search', component: SearchView },
        { path: '/book', name: 'book-detail', component: BookDetailsView },
        { path: '/tbr', name: 'tbr', component: TbrView },
        { path: '/next-read', name: 'next-read', component: NextReadView },
    ],
});

export default router;