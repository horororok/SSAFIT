import { createApp } from 'vue';
import { createPinia } from 'pinia';
import axios from 'axios';
import createPersistedState from 'pinia-plugin-persistedstate';

import App from './App.vue';
import router from './router';

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.use(pinia);
app.use(IconsPlugin);

pinia.use(createPersistedState);

app.mount('#app');
