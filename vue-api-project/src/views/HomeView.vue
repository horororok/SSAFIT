<template>
  <div class="home-container">
    <!-- <img src="@/assets/img/SSAFIT.png" alt="homeImage" class="responsive-image" /> -->
    <img src="@/assets/img/homecat.jpg" alt="homeImage" style="width:100%; z-index: -1;" />
    <div class = "components">
      <News />
    </div>
    
    <footer class="footer mt-auto py-3 bg-white">
      <div class="container text-center">
        <span class="text-muted">© 2023 SSAFITMATE. All rights reserved.</span>
      </div>
    </footer>
  </div>
</template>

<script setup>
import News from '@/components/home/News.vue';
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
}

.background-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url("@/assets/img/고양이1.jpg") center center/cover no-repeat fixed;
  z-index: -1;
  opacity: 0.5; /* 배경의 투명도 조절 */
}

.responsive-image {
  max-width: 600px;
  height: auto;
  display: block;
  margin: 0 auto;
}

h1 {
  text-align: center;
}

.components {
  position: relative;
  z-index: 100; /* 배경 이미지 위로 올리기 */
}

.wrapper {
  min-height: calc(100vh - 60px);
}

.footer {
  width: 100%;
  height: 60px;
  line-height: 60px;
  z-index: 1;
}


</style>
