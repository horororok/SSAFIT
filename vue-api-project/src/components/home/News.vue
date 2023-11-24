<template>
  <div class="news-container">
    <h3 class="news-title">오늘의 스포츠 이슈</h3>
    <div class="news-table">
      <div v-for="(item, index) in newsList" :key="index" class="news-item">
        <a :href="item.link" class="news-link" v-html="item.title"></a>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

onMounted(() => {
    getNews();
})

const newsList = ref([]);

const API_URL = "http://localhost:8080/home/news";

const getNews = function(){
    axios({
        url: API_URL,
        method : "GET"
    })
    .then((res) => {
        newsList.value = res.data;
    })
    .catch((err) => {
        console.log("뉴스 서버 에러 : ", err);
    })
}
</script>

<style scoped>
.news-container {
  text-align: center;
  padding: 20px;
  z-index: 100;
  background-color: rgba(255, 255, 255, 0.8);
}

.news-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.news-table {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 20px;
}

.news-item {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 15px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease;
}

.news-link {
  text-decoration: none;
  color: #333;
  font-size: 18px;
  display: block;
}

.news-link:hover {
  text-decoration: underline;
}

</style>