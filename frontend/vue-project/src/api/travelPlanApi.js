// src/api/travelPlanApi.js
import axios from 'axios'

const travelPlanApi = axios.create({
    baseURL: 'http://localhost:8083',
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json'
    }
})

export default travelPlanApi