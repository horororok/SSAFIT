import { createApp } from 'vue';
import { createPinia } from 'pinia';
import axios from 'axios';
import createPersistedState from 'pinia-plugin-persistedstate';
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.bundle.js'

import App from './App.vue';
import router from './router';

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.use(pinia);

pinia.use(createPersistedState);

app.mount('#app');
