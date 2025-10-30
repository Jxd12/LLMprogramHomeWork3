import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '../stores/user'
import Home from '../views/Home.vue'
import Dashboard from '../views/Dashboard.vue'
import Login from '../views/Login.vue'

const routes = [
    { path: '/', component: Home , meta: { requiresAuth: true } },
    { path: '/dashboard', component: Dashboard, meta: { requiresAuth: true } },
    { path: '/login', component: Login }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// 添加导航守卫
router.beforeEach((to, from, next) => {
    const userStore = useUserStore()

    // 检查路由是否需要认证
    if (to.meta.requiresAuth && !userStore.isLoggedIn) {
        // 需要认证但未登录，重定向到登录页
        next('/login')
    } else if (to.path === '/login' && userStore.isLoggedIn) {
        // 已登录用户访问登录页，重定向到仪表板
        next('/dashboard')
    } else {
        // 其他情况允许访问
        next()
    }
})

export default router