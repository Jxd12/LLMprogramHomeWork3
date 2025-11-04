<!-- src/components/TravelPlanModal.vue -->
<template>
  <div v-if="visible" class="modal-overlay" @click="closeModal">
    <div class="modal-content" @click.stop>
      <h3>{{ plan.destination }} 旅行详情</h3>
      <div class="modal-body">
        <!-- 时间轴 -->
        <div class="timeline">
          <div
              v-for="(day, dayIndex) in plan.dailyItineraries"
              :key="day.id"
              class="timeline-item"
              :class="{ active: currentDayIndex === dayIndex }"
              @click="selectDay(dayIndex)"
          >
            <div class="timeline-date">第{{ dayIndex + 1 }}天</div>
          </div>
        </div>

        <!-- 住宿信息 -->
        <div class="accommodation-info" v-if="selectedDay">
          <h4>住宿信息</h4>
          <div class="activity">
            <div class="editable-field">
              <label>地点:</label>
              <input v-model="editableItinerary.accommodation.place" class="editable-input" />
            </div>
            <div class="editable-field">
              <label>预算:</label>
              <input v-model="editableItinerary.accommodation.budget" class="editable-input" />
            </div>
            <div class="editable-field">
              <label>说明:</label>
              <input v-model="editableItinerary.accommodation.content" class="editable-input" />
            </div>
          </div>
        </div>

        <!-- 每日行程详情 -->
        <div class="daily-itinerary" v-if="selectedDay">
          <h4>第{{ currentDayIndex + 1 }}天 行程安排</h4>

          <div class="time-slot">
            <h5>早餐</h5>
            <div class="activity">
              <div class="editable-field">
                <label>地点:</label>
                <input v-model="editableItinerary.breakfast.place" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>预算:</label>
                <input v-model="editableItinerary.breakfast.budget" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>内容:</label>
                <input v-model="editableItinerary.breakfast.content" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>交通:</label>
                <input v-model="editableItinerary.breakfast.transport" class="editable-input" />
              </div>
            </div>
          </div>

          <div class="time-slot">
            <h5>上午</h5>
            <div class="activity">
              <div class="editable-field">
                <label>地点:</label>
                <input v-model="editableItinerary.morning.place" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>预算:</label>
                <input v-model="editableItinerary.morning.budget" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>内容:</label>
                <input v-model="editableItinerary.morning.content" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>交通:</label>
                <input v-model="editableItinerary.morning.transport" class="editable-input" />
              </div>
            </div>
          </div>

          <div class="time-slot">
            <h5>中餐</h5>
            <div class="activity">
              <div class="editable-field">
                <label>地点:</label>
                <input v-model="editableItinerary.lunch.place" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>预算:</label>
                <input v-model="editableItinerary.lunch.budget" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>内容:</label>
                <input v-model="editableItinerary.lunch.content" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>交通:</label>
                <input v-model="editableItinerary.lunch.transport" class="editable-input" />
              </div>
            </div>
          </div>

          <div class="time-slot">
            <h5>下午</h5>
            <div class="activity">
              <div class="editable-field">
                <label>地点:</label>
                <input v-model="editableItinerary.afternoon.place" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>预算:</label>
                <input v-model="editableItinerary.afternoon.budget" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>内容:</label>
                <input v-model="editableItinerary.afternoon.content" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>交通:</label>
                <input v-model="editableItinerary.afternoon.transport" class="editable-input" />
              </div>
            </div>
          </div>

          <div class="time-slot">
            <h5>晚餐</h5>
            <div class="activity">
              <div class="editable-field">
                <label>地点:</label>
                <input v-model="editableItinerary.dinner.place" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>预算:</label>
                <input v-model="editableItinerary.dinner.budget" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>内容:</label>
                <input v-model="editableItinerary.dinner.content" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>交通:</label>
                <input v-model="editableItinerary.dinner.transport" class="editable-input" />
              </div>
            </div>
          </div>

          <div class="time-slot">
            <h5>夜晚</h5>
            <div class="activity">
              <div class="editable-field">
                <label>地点:</label>
                <input v-model="editableItinerary.evening.place" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>预算:</label>
                <input v-model="editableItinerary.evening.budget" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>内容:</label>
                <input v-model="editableItinerary.evening.content" class="editable-input" />
              </div>
              <div class="editable-field">
                <label>交通:</label>
                <input v-model="editableItinerary.evening.transport" class="editable-input" />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-actions">
        <button @click="saveChanges" class="save-btn">保存</button>
        <button @click="closeModal" class="close-btn">关闭</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, reactive, watch } from 'vue'

// 定义组件属性
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  plan: {
    type: Object,
    default: () => ({})
  }
})

// 定义组件事件
const emit = defineEmits(['update:visible', 'save', 'day-select'])

// 当前选中的天数索引
const currentDayIndex = ref(0)

// 可编辑的行程数据
const editableItinerary = reactive({
  accommodation: {
    place: '',
    budget: '',
    content: ''
  },
  breakfast: {
    place: '',
    budget: '',
    content: '',
    transport: ''
  },
  morning: {
    place: '',
    budget: '',
    content: '',
    transport: ''
  },
  lunch: {
    place: '',
    budget: '',
    content: '',
    transport: ''
  },
  afternoon: {
    place: '',
    budget: '',
    content: '',
    transport: ''
  },
  dinner: {
    place: '',
    budget: '',
    content: '',
    transport: ''
  },
  evening: {
    place: '',
    budget: '',
    content: '',
    transport: ''
  }
})

// 计算当前选中日期的行程
const selectedDay = computed(() => {
  if (!props.plan || !props.plan.dailyItineraries) return null
  return props.plan.dailyItineraries[currentDayIndex.value]
})

// 监听计划变化，初始化数据
watch(
    () => props.plan,
    () => {
      if (props.plan && props.plan.dailyItineraries && props.plan.dailyItineraries.length > 0) {
        currentDayIndex.value = 0
        initializeEditableData()
      }
    },
    { immediate: true }
)

// 监听选中日期变化，更新可编辑数据
watch(currentDayIndex, () => {
  initializeEditableData()
  emit('day-select', currentDayIndex.value)
})

// 初始化可编辑数据
const initializeEditableData = () => {
  if (selectedDay.value) {
    const day = selectedDay.value

    // 初始化住宿信息
    editableItinerary.accommodation = {
      place: day.accommodationPlace || '',
      budget: day.accommodationBudget || '',
      content: day.accommodationContent || ''
    }

    // 初始化活动信息
    const activities = day.activities || []
    const activityKeys = ['breakfast', 'morning', 'lunch', 'afternoon', 'dinner', 'evening']

    // 为每个活动类型设置默认空值
    activityKeys.forEach(key => {
      editableItinerary[key] = {
        place: '',
        budget: '',
        content: '',
        transport: ''
      }
    })

    // 根据实际活动数据填充
    for (let i = 0; i < activities.length && i < activityKeys.length; i++) {
      const key = activities[i].activityType
      editableItinerary[key] = {
        place: activities[i].place || '',
        budget: activities[i].budget || '',
        content: activities[i].content || '',
        transport: activities[i].transport || ''
      }
    }

    console.log('Initialized editableItinerary:', editableItinerary)
  }
}

// 选择日期
const selectDay = (dayIndex) => {
  currentDayIndex.value = dayIndex
}

// 关闭模态框
const closeModal = () => {
  emit('update:visible', false)
}

// 保存更改
const saveChanges = async () => {
  // 获取当前的dailyItinerary数据
  const currentDailyItinerary = props.plan.dailyItineraries[currentDayIndex.value];

  // 创建新的activities数组，通过activityType正确匹配并更新编辑的值
  const updatedActivities = currentDailyItinerary.activities.map((activity) => {
    const activityType = activity.activityType;

    // 确保editableItinerary中有对应的活动类型数据
    if (activityType && editableItinerary[activityType]) {
      return {
        ...activity,
        place: editableItinerary[activityType].place,
        budget: editableItinerary[activityType].budget,
        content: editableItinerary[activityType].content,
        transport: editableItinerary[activityType].transport
      };
    }
    // 如果没有匹配的活动类型，返回原始活动数据
    return activity;
  });

  // 构造更新后的dailyItinerary对象
  const updatedDailyItinerary = {
    ...currentDailyItinerary,
    accommodationPlace: editableItinerary.accommodation.place,
    accommodationBudget: editableItinerary.accommodation.budget,
    accommodationContent: editableItinerary.accommodation.content,
    activities: updatedActivities
  };

  console.log('Updated dailyItinerary:', updatedDailyItinerary);

  emit('save', {
    dayIndex: currentDayIndex.value,
    updatedItinerary: updatedDailyItinerary
  });
}
</script>

<style scoped>
/* 弹窗样式 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  border-radius: 8px;
  padding: 20px;
  max-width: 800px;
  width: 90%;
  max-height: 90vh;
  overflow-y: auto;
}

.modal-content h3 {
  margin-top: 0;
  color: #42b983;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}

.modal-body {
  margin: 15px 0;
}

/* 时间轴样式 */
.timeline {
  display: flex;
  overflow-x: auto;
  margin-bottom: 30px;
  padding: 10px 0;
  border-bottom: 1px solid #eee;
}

.timeline-item {
  flex: 0 0 auto;
  padding: 10px 20px;
  margin-right: 10px;
  border: 1px solid #ddd;
  border-radius: 20px;
  cursor: pointer;
  text-align: center;
  min-width: 100px;
}

.timeline-item.active {
  background-color: #42b983;
  color: white;
  border-color: #42b983;
}

.timeline-date {
  font-weight: bold;
}

/* 住宿信息样式 */
.accommodation-info {
  margin-bottom: 30px;
}

.accommodation-info h4 {
  color: #333;
  margin-bottom: 15px;
}

/* 每日行程详情样式 */
.daily-itinerary h4 {
  color: #333;
  margin-bottom: 20px;
}

.time-slot {
  margin-bottom: 20px;
}

.time-slot h5 {
  color: #42b983;
  margin-bottom: 10px;
  padding-bottom: 5px;
  border-bottom: 1px solid #eee;
}

.activity {
  background-color: #f8f9fa;
  padding: 15px;
  border-radius: 4px;
  margin-bottom: 10px;
}

.editable-field {
  margin-bottom: 10px;
}

.editable-field label {
  display: inline-block;
  width: 60px;
  font-weight: bold;
  color: #666;
}

.editable-input {
  width: calc(100% - 70px);
  padding: 5px 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.modal-actions {
  text-align: right;
  margin-top: 20px;
}

.save-btn {
  background-color: #42b983;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
}

.save-btn:hover {
  background-color: #359c6d;
}

.close-btn {
  background-color: #cccccc;
  color: #333;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}

.close-btn:hover {
  background-color: #bbbbbb;
}
</style>
