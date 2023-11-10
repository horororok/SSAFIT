import { createApp } from "vue";
import App from "./App.vue";
import router from "@/router";
import axios from "axios";
// axios , router 를 위한 초기 설정

const app = createApp(App);

app.config.globalProperties.$axios = axios;

app.use(router);

app.mount("#app");
