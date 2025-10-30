<template>
  <div class="login">
    <div class="login-form">
      <h2>{{ isRegisterMode ? '用户注册' : '用户登录' }}</h2>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="username">用户名:</label>
          <input
              type="text"
              id="username"
              v-model="form.username"
              required
          />
        </div>
        <div class="form-group">
          <label for="password">密码:</label>
          <input
              type="password"
              id="password"
              v-model="form.password"
              required
          />
        </div>
        <div v-if="isRegisterMode" class="form-group">
          <label for="confirmPassword">确认密码:</label>
          <input
              type="password"
              id="confirmPassword"
              v-model="form.confirmPassword"
              required
          />
        </div>
        <button type="submit" class="login-button">
          {{ isRegisterMode ? '注册' : '登录' }}
        </button>
      </form>
      <div class="form-footer">
        <p v-if="!isRegisterMode">
          没有账户？ <a href="#" @click.prevent="switchToRegister">立即注册</a>
        </p>
        <p v-else>
          已有账户？ <a href="#" @click.prevent="switchToLogin">立即登录</a>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'

const userStore = useUserStore()
const router = useRouter()

const isRegisterMode = ref(false)

const form = reactive({
  username: '',
  password: '',
  confirmPassword: ''
})

const switchToRegister = () => {
  isRegisterMode.value = true
  resetForm()
}

const switchToLogin = () => {
  isRegisterMode.value = false
  resetForm()
}

const resetForm = () => {
  form.username = ''
  form.password = ''
  form.confirmPassword = ''
}

const handleSubmit = async () => {
  if (isRegisterMode.value) {
    await handleRegister()
  } else {
    await handleLogin()
  }
}

const handleLogin = async () => {
  try {
    await userStore.login(form)
    // 显示成功消息
    alert('登录成功')
    router.push('/dashboard')
  } catch (error) {
    // 显示错误消息
    alert(error.message)
  }
}

const handleRegister = async () => {
  if (form.password !== form.confirmPassword) {
    alert('两次输入的密码不一致')
    return
  }

  try {
    await userStore.register({
      username: form.username,
      password: form.password
    })
    // 显示成功消息
    alert('注册成功')
    // 注册成功后自动登录并跳转
    router.push('/dashboard')
  } catch (error) {
    // 显示错误消息
    alert(error.message)
  }
}
</script>

<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.login-form {
  width: 100%;
  max-width: 400px;
  padding: 30px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.login-button {
  width: 100%;
  padding: 12px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.login-button:hover {
  background-color: #359c6d;
}

.form-footer {
  text-align: center;
  margin-top: 20px;
}

.form-footer a {
  color: #42b983;
  text-decoration: none;
}

.form-footer a:hover {
  text-decoration: underline;
}
</style>
