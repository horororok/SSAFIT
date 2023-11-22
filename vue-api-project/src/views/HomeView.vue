<template>
  <div class="home-container">
    <img src="@/assets/img/SSAFIT.png" alt="homeImage" class="responsive-image" />
    <News />
    <footer class="footer mt-auto py-3 bg-light">
      <div class="container text-center">
        <span class="text-muted">© 2023 SSAFIT. All rights reserved.</span>
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

.wrapper {
  min-height: calc(100vh - 60px);
}

.footer {
  width: 100%;
  height: 60px;
  line-height: 60px;
  background-color: #f5f5f5;
}

.carousel-control-prev,
.carousel-control-next {
  top: 50%;
  transform: translateY(-50%);
}

.carousel img {
  height: 100% !important;
}
</style>
