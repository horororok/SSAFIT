<template>
  <div class="home-container">
    <!-- <img src="@/assets/img/SSAFIT.png" alt="homeImage" class="responsive-image" /> -->
    <img src="@/assets/img/homecat2.jpg" alt="homeImage" style="width:100%; z-index: -1; object-fit: cover;" />

    <div class="divider"></div>

    <News />

    <div class="divider"></div>

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


.divider {
  width: 100%;
  border-bottom: 1px solid #ccc;
  margin: 20px 0; /* 간격을 조절할 수 있습니다. */
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
  z-index: 1;
}
</style>
