<template>
  <aside class="sidebar">
    <ul>
      <li>
        <router-link to="/">首页</router-link>
      </li>
      <li>
        <router-link to="/dashboard">仪表板</router-link>
      </li>
    </ul>
  </aside>
</template>

<script setup>
</script>

<style scoped>
.sidebar {
  width: 200px;
  background-color: #f5f5f5;
  padding: 20px 0;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar li a {
  display: block;
  padding: 10px 20px;
  text-decoration: none;
  color: #333;
}

.sidebar li a.router-link-active {
  background-color: #42b983;
  color: white;
}
</style>
