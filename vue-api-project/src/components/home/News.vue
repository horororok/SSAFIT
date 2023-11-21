<template>
  <div class="news-container">
    <h3 class="news-title">스포츠 뉴스</h3>
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
/* 스타일 수정 */
.news-container {
  text-align: center;
  padding: 20px;
}

.news-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.news-table {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.news-item {
  background-color: #f5f5f5;
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
