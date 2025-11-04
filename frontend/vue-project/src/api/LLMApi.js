// LLMApi.js
import axios from 'axios'

const llmApi = axios.create({
  baseURL: 'http://localhost:8082',
  timeout: 10000000,
  headers: {
    'Content-Type': 'application/json'
  }
})

export default llmApi
