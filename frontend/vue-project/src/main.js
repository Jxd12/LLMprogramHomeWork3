import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router/index.js'

const app = createApp(App)
const pinia = createPinia()

// 先安装 Pinia
app.use(pinia)
// 再安装路由
app.use(router)

app.mount('#app')