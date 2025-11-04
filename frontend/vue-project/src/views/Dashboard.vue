<!-- src/views/Dashboard.vue -->
<template>
  <div class="dashboard">
    <h1>仪表板</h1>

    <!-- 旅行计划展示区域 -->
    <div class="travel-plans-section">
      <h2>我的旅行计划</h2>
      <div v-if="travelPlans.length > 0" class="plans-container">
        <div
            v-for="(plan, index) in travelPlans"
            :key="plan.id"
            class="plan-card"
        >
          <h3>{{ plan.destination }}</h3>
          <div class="plan-details">
            <div class="plan-item">
              <span class="label">天数:</span>
              <span class="value">{{ plan.duration }}</span>
            </div>
            <div class="plan-item">
              <span class="label">预算:</span>
              <span class="value">{{ plan.budget }}</span>
            </div>
            <div class="plan-item">
              <span class="label">同行人数:</span>
              <span class="value">{{ plan.companions }}</span>
            </div>
            <div class="plan-item">
              <span class="label">旅行偏好:</span>
              <span class="value">{{ plan.preferences }}</span>
            </div>
          </div>
          <button @click="showPlanDetails(plan)" class="details-btn">查看详情</button>
        </div>
      </div>
      <div v-else class="no-plans">
        <p>暂无旅行计划</p>
      </div>
    </div>

    <div class="stats">
      <div class="stat-card">
        <h3>旅行计划数</h3>
        <p class="stat-value">{{ travelPlans.length }}</p>
      </div>
      <div class="stat-card">
        <h3>总预算</h3>
        <p class="stat-value">¥{{ totalBudget }}</p>
      </div>
      <div class="stat-card">
        <h3>目的地</h3>
        <p class="stat-value">{{ uniqueDestinations }}</p>
      </div>
    </div>

    <!-- 旅行计划详情弹窗 -->
    <TravelPlanModal
        v-model:visible="showDetailModal"
        :plan="selectedPlan"
        @save="handleSaveChanges"
        @day-select="handleDaySelect"
    />
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import travelPlanApi from '../api/travelPlanApi.js'
import { useUserStore } from '../stores/user.js'
import TravelPlanModal from '../components/TravelPlanModal.vue'

// 旅行计划数据
const travelPlans = ref([])

// 弹窗相关状态
const showDetailModal = ref(false)
const selectedPlan = ref(null)

// 计算总预算
const totalBudget = computed(() => {
  return travelPlans.value.reduce((total, plan) => {
    const budgetValue = parseFloat(plan.budget) || 0;
    return total + budgetValue;
  }, 0);
})

// 计算不同目的地数量
const uniqueDestinations = computed(() => {
  const destinations = travelPlans.value.map(plan => plan.destination)
  return [...new Set(destinations)].length
})

// 显示计划详情
const showPlanDetails = (plan) => {
  selectedPlan.value = plan
  showDetailModal.value = true
}

// 处理保存更改
const handleSaveChanges = async ({ dayIndex, updatedItinerary }) => {
  if (selectedPlan.value && selectedPlan.value.id) {
    try {
      // 更新本地数据
      selectedPlan.value.dailyItineraries[dayIndex] = updatedItinerary

      // 调用后端API更新整个旅行计划
      const response = await travelPlanApi.put(`/api/travel-plans/${selectedPlan.value.id}`, selectedPlan.value)

      // 更新本地数据
      const planIndex = travelPlans.value.findIndex(plan => plan.id === selectedPlan.value.id)
      if (planIndex !== -1) {
        travelPlans.value[planIndex] = response.data
      }

      alert('行程已保存成功！')
    } catch (error) {
      console.error('保存失败:', error)
      alert('保存失败，请稍后重试')
    }
  }
}

// 处理日期选择
const handleDaySelect = (dayIndex) => {
  // 可以在这里添加额外的逻辑
  console.log('选择了第', dayIndex + 1, '天')
}

// 获取用户旅行计划
const fetchUserTravelPlans = async () => {
  try {
    // 从用户存储中获取当前用户ID
    const userStore = useUserStore();
    const userId = userStore.user?.id;
    console.log('获取旅行计划，用户ID:', userStore.user);

    if (!userId) {
      console.warn('用户未登录，无法获取旅行计划');
      return;
    }

    // 调用后端API，通过用户ID获取旅行计划（现在包含完整的dailyItineraries数据）
    const response = await travelPlanApi.get(`/api/travel-plans/user/${userId}`);
    travelPlans.value = response.data;
    console.log('旅行计划数据:', travelPlans.value);
  } catch (error) {
    console.error('获取旅行计划失败:', error);
    alert('获取旅行计划失败，请稍后重试');
  }
}

onMounted(() => {
  fetchUserTravelPlans()
})
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

/* 旅行计划样式 */
.travel-plans-section {
  margin-bottom: 30px;
}

.travel-plans-section h2 {
  color: #333;
  margin-bottom: 20px;
}

.plans-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
}

.plan-card {
  background-color: white;
  border: 1px solid #dee2e6;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.plan-card h3 {
  color: #42b983;
  margin-top: 0;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}

.plan-details {
  margin-top: 15px;
}

.plan-item {
  display: flex;
  margin-bottom: 10px;
}

.label {
  font-weight: bold;
  width: 100px;
  color: #666;
}

.value {
  flex: 1;
  color: #333;
}

.details-btn {
  background-color: #42b983;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 15px;
  width: 100%;
}

.details-btn:hover {
  background-color: #359c6d;
}

.no-plans {
  text-align: center;
  padding: 40px;
  background-color: #f8f9fa;
  border-radius: 8px;
  color: #666;
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
</style>
