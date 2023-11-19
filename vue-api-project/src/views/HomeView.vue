<template>
    <div>
      <h2>HomeView</h2>
      <h1>SSAFIT 메인 홈페이지</h1>
  
      <!-- Bootstrap Carousel -->
      <div id="imageCarousel" class="carousel slide wrapper" data-bs-ride="carousel">
        <div class="carousel-inner">
          <!-- Sample Images -->
          <div class="carousel-item active">
            <img src="https://via.placeholder.com/800x400" class="d-block w-100" alt="First slide">
          </div>
          <div class="carousel-item">
            <img src="https://via.placeholder.com/800x400" class="d-block w-100" alt="Second slide">
          </div>
          <div class="carousel-item">
            <img src="https://via.placeholder.com/800x400" class="d-block w-100" alt="Third slide">
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#imageCarousel" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#imageCarousel" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
  
      <!-- Your existing footer -->
      <footer class="footer mt-auto py-3 bg-light">
        <div class="container text-center">
          <span class="text-muted">© 2023 SSAFIT. All rights reserved.</span>
        </div>
      </footer>
    </div>
  </template>
  
  <script setup></script>
  
  <style scoped>
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
  </style>
  