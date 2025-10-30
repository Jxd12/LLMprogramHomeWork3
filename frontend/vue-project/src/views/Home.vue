<template>
  <div class="home">
    <h1>欢迎来到 Vue 项目</h1>
    <p>这是一个基于 Vue 3 和 Vite 构建的前端项目。</p>
    <div class="features">
      <div class="feature-card">
        <h3>快速开发</h3>
        <p>利用 Vite 的快速构建能力提升开发效率</p>
      </div>
      <div class="feature-card">
        <h3>响应式设计</h3>
        <p>适配各种设备屏幕尺寸</p>
      </div>
      <div class="feature-card">
        <h3>现代化工具</h3>
        <p>集成最新的前端开发工具链</p>
      </div>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.home {
  padding: 20px;
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-top: 30px;
}

.feature-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  text-align: center;
}

.feature-card h3 {
  margin-top: 0;
  color: #42b983;
}
</style>
