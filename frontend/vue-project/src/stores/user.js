import { defineStore } from 'pinia'
import securityApi from "@/api/securityApi.js";

export const useUserStore = defineStore('user', {
    state: () => ({
        user: JSON.parse(localStorage.getItem('user')) || null
    }),

    getters: {
        isLoggedIn: (state) => !!state.user
    },

    actions: {
        async register(userData) {
            try {
                // 调用实际的后端注册接口
                const response = await securityApi.post('/auth/register', userData)
                const apiResponse = response.data

                if (apiResponse.success) {
                    this.user = apiResponse.data
                    localStorage.setItem('user', JSON.stringify(apiResponse.data))
                } else {
                    throw new Error(apiResponse.message)
                }
            } catch (error) {
                // 处理网络错误或后端返回的错误信息
                if (error.response && error.response.data) {
                    throw new Error(error.response.data.message || '注册失败')
                }
                throw new Error('注册失败')
            }
        },

        async login(credentials) {
            try {
                // 调用实际的后端登录接口
                const response = await securityApi.post('/auth/login', credentials)
                const apiResponse = response.data

                if (apiResponse.success) {
                    this.user = apiResponse.data
                    localStorage.setItem('user', JSON.stringify(apiResponse.data))
                } else {
                    throw new Error(apiResponse.message)
                }
            } catch (error) {
                // 处理网络错误或后端返回的错误信息
                if (error.response && error.response.data) {
                    throw new Error(error.response.data.message || '登录失败')
                }
                throw new Error('登录失败')
            }
        },

        logout() {
            this.user = null
            localStorage.removeItem('user')
        }
    }
})