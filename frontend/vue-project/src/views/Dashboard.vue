<template>
  <div class="dashboard">
    <h1>仪表板</h1>
    <div class="stats">
      <div class="stat-card">
        <h3>用户数</h3>
        <p class="stat-value">1,234</p>
      </div>
      <div class="stat-card">
        <h3>订单数</h3>
        <p class="stat-value">567</p>
      </div>
      <div class="stat-card">
        <h3>收入</h3>
        <p class="stat-value">¥89,012</p>
      </div>
    </div>
    <div class="charts">
      <div class="chart-container">
        <h3>数据图表</h3>
        <div class="chart-placeholder">
          <!-- 这里可以集成图表库如 Chart.js 或 ECharts -->
          <p>图表占位符</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.stat-card {
  background-color: #f8f9fa;
  border: 1px solid #dee2e6;
  border-radius: 8px;
  padding: 20px;
  text-align: center;
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
  color: #42b983;
  margin: 10px 0 0;
}

.charts {
  margin-top: 30px;
}

.chart-container {
  background-color: white;
  border: 1px solid #dee2e6;
  border-radius: 8px;
  padding: 20px;
}

.chart-placeholder {
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f8f9fa;
  border-radius: 4px;
}
</style>
