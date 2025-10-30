<template>
  <header class="header">
    <div class="logo">Vue Project</div>
    <nav class="nav">
      <router-link to="/">首页</router-link>
      <router-link to="/dashboard">仪表板</router-link>
      <button @click="handleLogin" v-if="!isLoggedIn">登录</button>
      <button @click="handleLogout" v-else>退出</button>
    </nav>
  </header>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'

const userStore = useUserStore()
const router = useRouter()

const isLoggedIn = computed(() => userStore.isLoggedIn)

const handleLogin = () => {
  router.push('/login')
}

const handleLogout = () => {
  userStore.logout()
  router.push('/')
}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
  padding: 0 20px;
  background-color: #42b983;
  color: white;
}

.nav a {
  margin-right: 20px;
  color: white;
  text-decoration: none;
}

.nav button {
  background: white;
  border: none;
  padding: 5px 15px;
  border-radius: 4px;
  cursor: pointer;
}
</style>
