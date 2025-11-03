
<template>
  <div class="home">
    <h1>欢迎来到智能旅行规划系统</h1>
    <p>通过AI技术为您生成个性化的旅行路线和建议</p>

    <div class="features">
      <div class="feature-card">
        <h3>智能行程规划</h3>
        <p>通过语音或文字输入需求，AI 自动生成个性化旅行路线</p>
        <div class="prompt-input">
          <textarea
              v-model="userPrompt"
              placeholder="请输入您的旅行需求，例如：我想去日本，5天，预算1万元，喜欢美食和动漫，带孩子"
              @keyup.ctrl.enter="handlePromptSubmit"
              rows="4"
          ></textarea>
          <div class="input-actions">
            <button @click="startSpeechRecognition" :disabled="isListening" v-if="isSpeechSupported">
              {{ isListening ? 'Listening...' : '🎤 语音输入' }}
            </button>
            <button @click="handlePromptSubmit">生成行程</button>
          </div>
        </div>
      </div>
    </div>

    <!-- 确认弹窗 -->
    <div v-if="showConfirmModal" class="modal-overlay" @click="showConfirmModal = false">
      <div class="modal-content" @click.stop>
        <h3>确认行程信息</h3>
        <div class="modal-body">
          <table class="data-table">
            <thead>
            <tr>
              <th>字段</th>
              <th>值</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>旅行目的地</td>
              <td><input v-model="editableTravelData.destination" class="data-input" /></td>
            </tr>
            <tr>
              <td>日期</td>
              <td><input v-model="editableTravelData.duration" class="data-input" /></td>
            </tr>
            <tr>
              <td>预算</td>
              <td><input v-model="editableTravelData.budget" class="data-input" /></td>
            </tr>
            <tr>
              <td>同行人数</td>
              <td><input v-model="editableTravelData.companions" class="data-input" /></td>
            </tr>
            <tr>
              <td>旅行偏好</td>
              <td><input v-model="editableTravelData.preferences" class="data-input" /></td>
            </tr>
            </tbody>
          </table>
        </div>
        <div class="modal-actions">
          <button @click="cancelGenerate" class="cancel-btn">取消</button>
          <button @click="confirmGenerate" class="confirm-btn">确认生成</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>import { ref, reactive, onMounted } from 'vue'
import llmApi from '../api/LLMApi.js'

const userPrompt = ref('')
const isListening = ref(false)
const isSpeechSupported = ref(false)
const showConfirmModal = ref(false)
const parsedTravelData = ref(null)
// 可编辑的旅行数据
const editableTravelData = reactive({
  destination: '',
  duration: '',
  budget: '',
  companions: '',
  preferences: ''
})

let recognition = null
let existingContent = ''

// 检查浏览器是否支持语音识别
onMounted(() => {
  if ('webkitSpeechRecognition' in window || 'SpeechRecognition' in window) {
    isSpeechSupported.value = true
    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition
    recognition = new SpeechRecognition()
    recognition.continuous = false
    recognition.interimResults = true
    recognition.lang = 'zh-CN'

    recognition.onresult = (event) => {
      const transcript = Array.from(event.results)
          .map(result => result[0])
          .map(result => result.transcript)
          .join('')
      userPrompt.value = existingContent + (existingContent ? '\n' : '') + transcript
    }

    recognition.onerror = (event) => {
      console.error('语音识别错误:', event.error)
      isListening.value = false
    }

    recognition.onend = () => {
      isListening.value = false
    }
  }
})

const startSpeechRecognition = () => {
  if (recognition) {
    isListening.value = true
    existingContent = userPrompt.value
    recognition.start()
  }
}

// 使用LLM API调用
const sendPromptToBackend = async (prompt) => {
  try {
    const response = await llmApi.post('/api/llm/travel-plan', { prompt })
    return response.data
  } catch (error) {
    console.error('API请求失败:', error)
    throw error
  }
}

const handlePromptSubmit = async () => {
  if (userPrompt.value.trim()) {
    try {
      // 显示加载状态
      const submitButton = document.querySelector('.input-actions button:last-child');
      const originalText = submitButton.textContent;
      submitButton.textContent = '处理中...';
      submitButton.disabled = true;

      // 发送请求到后端
      const responseData = await sendPromptToBackend(userPrompt.value);
      parsedTravelData.value = responseData;

      // 初始化可编辑数据
      editableTravelData.destination = responseData.destination || '';
      editableTravelData.duration = responseData.duration || '';
      editableTravelData.budget = responseData.budget || '';
      editableTravelData.companions = responseData.companions || '';
      editableTravelData.preferences = responseData.preferences || '';

      showConfirmModal.value = true;

      // 恢复按钮状态
      submitButton.textContent = originalText;
      submitButton.disabled = false;
    } catch (error) {
      console.error('请求失败:', error);
      alert('请求失败，请稍后重试');
      // 恢复按钮状态
      const submitButton = document.querySelector('.input-actions button:last-child');
      submitButton.textContent = '生成行程';
      submitButton.disabled = false;
    }
  } else {
    alert('请输入您的旅行需求')
  }
}

const confirmGenerate = () => {
  showConfirmModal.value = false;
  alert(`已确认，正在为您生成行程...\n目的地: ${editableTravelData.destination}\n日期: ${editableTravelData.duration}\n预算: ${editableTravelData.budget}\n同行人数: ${editableTravelData.companions}\n旅行偏好: ${editableTravelData.preferences}`);
  // 这里可以继续调用生成行程的API
}

const cancelGenerate = () => {
  showConfirmModal.value = false;
}
</script>

<style scoped>.home {
  padding: 20px;
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-top: 30px;
}

.feature-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  text-align: center;
}

.feature-card h3 {
  margin-top: 0;
  color: #42b983;
}

.prompt-input {
  margin-top: 15px;
}

.prompt-input textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-bottom: 10px;
  box-sizing: border-box;
  font-family: inherit;
  resize: vertical;
  font-size: 14px;
}

.input-actions {
  display: flex;
  gap: 10px;
  justify-content: center;
}

.input-actions button {
  background-color: #42b983;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  flex: 1;
}

.input-actions button:hover:not(:disabled) {
  background-color: #359c6d;
}

.input-actions button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

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
  max-width: 500px;
  width: 90%;
  max-height: 80vh;
  overflow-y: auto;
}

.modal-content h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 15px 0;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
}

.data-table th,
.data-table td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.data-table th {
  background-color: #f5f5f5;
  font-weight: bold;
}

.data-input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 20px;
}

.modal-actions button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.cancel-btn {
  background-color: #cccccc;
  color: #333;
}

.cancel-btn:hover {
  background-color: #bbbbbb;
}

.confirm-btn {
  background-color: #42b983;
  color: white;
}

.confirm-btn:hover {
  background-color: #359c6d;
}
</style>