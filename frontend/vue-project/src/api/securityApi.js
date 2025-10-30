import axios from 'axios'

const securityApi = axios.create({
  baseURL: 'http://localhost:8081',
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json'
  }
})

export default securityApi
