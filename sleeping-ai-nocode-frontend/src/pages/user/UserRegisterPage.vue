<template>
  <main class="register-page">
    <div class="register-container">
      <div class="register-header">
        <h2 class="title">sleeping AI 零代码</h2>
        <p class="subtitle">用户注册</p>
        <p class="desc">不写一行代码，生成完整应用</p>
      </div>
      <a-form
        :model="formState"
        name="registerForm"
        autocomplete="off"
        @finish="handleSubmit"
        class="register-form"
      >
        <a-form-item
          name="userAccount"
          :rules="[
            { required: true, message: '请输入账号' },
            { min: 5, message: '账号长度不能小于 5 位' },
            { max: 16, message: '账号长度不能超过 16 位' },
            { pattern: /^[a-zA-Z][a-zA-Z0-9_-]*$/, message: '账号需以字母开头，只能包含字母、数字、下划线和减号' },
          ]"
        >
          <a-input
            v-model:value="formState.userAccount"
            placeholder="请输入账号（5-16位，字母开头）"
            size="large"
            class="form-input"
          >
            <template #prefix>
              <UserOutlined class="input-icon" />
            </template>
          </a-input>
        </a-form-item>
        <a-form-item
          name="userPassword"
          :rules="[
            { required: true, message: '请输入密码' },
            { min: 8, message: '密码不能小于 8 位' },
            { max: 16, message: '密码不能超过 16 位' },
            { validator: validatePasswordLetter },
            { validator: validatePasswordDigit },
          ]"
        >
          <a-input-password
            v-model:value="formState.userPassword"
            placeholder="请输入密码（8-16位，含字母和数字）"
            size="large"
            class="form-input"
          >
            <template #prefix>
              <LockOutlined class="input-icon" />
            </template>
          </a-input-password>
        </a-form-item>
        <a-form-item
          name="checkPassword"
          :rules="[
            { required: true, message: '请确认密码' },
            { min: 8, message: '密码不能小于 8 位' },
            { validator: validateCheckPassword },
          ]"
        >
          <a-input-password
            v-model:value="formState.checkPassword"
            placeholder="请确认密码"
            size="large"
            class="form-input"
          >
            <template #prefix>
              <SafetyOutlined class="input-icon" />
            </template>
          </a-input-password>
        </a-form-item>
        <div class="form-tips">
          已有账号？
          <RouterLink to="/user/login" class="login-link">去登录</RouterLink>
        </div>
        <a-form-item>
          <a-button
            type="primary"
            html-type="submit"
            size="large"
            class="submit-btn"
            :loading="loading"
          >
            注册
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </main>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { message } from 'ant-design-vue'
import { reactive, ref } from 'vue'
import { UserOutlined, LockOutlined, SafetyOutlined } from '@ant-design/icons-vue'
import { userRegister } from '@/api/userController.ts'

const router = useRouter()
const loading = ref(false)

const formState = reactive<API.UserRegisterRequest>({
  userAccount: '',
  userPassword: '',
  checkPassword: '',
})

const validateCheckPassword = (rule: unknown, value: string, callback: (error?: Error) => void) => {
  if (value && value !== formState.userPassword) {
    callback(new Error('两次输入密码不一致'))
  } else {
    callback()
  }
}

const validatePasswordLetter = (rule: unknown, value: string, callback: (error?: Error) => void) => {
  const letterPattern = /[a-zA-Z]/
  if (value && !letterPattern.test(value)) {
    callback(new Error('密码需包含至少一个字母'))
  } else {
    callback()
  }
}

const validatePasswordDigit = (rule: unknown, value: string, callback: (error?: Error) => void) => {
  const digitPattern = /[0-9]/
  if (value && !digitPattern.test(value)) {
    callback(new Error('密码需包含至少一个数字'))
  } else {
    callback()
  }
}

const handleSubmit = async (values: API.UserRegisterRequest) => {
  loading.value = true
  try {
    const res = await userRegister(values)
    if (res.data.code === 0) {
      message.success('注册成功')
      router.push({
        path: '/user/login',
        replace: true,
      })
    } else {
      message.error('注册失败，' + res.data.message)
    }
  } catch {
    message.error('注册失败，网络错误')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-page {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 200px);
}

.register-container {
  background: var(--color-bg-primary);
  max-width: 420px;
  width: 100%;
  padding: var(--spacing-2xl);
  border-radius: var(--radius-xl);
  box-shadow: var(--shadow-lg);
  border: 1px solid var(--color-border-light);
}

.register-header {
  text-align: center;
  margin-bottom: var(--spacing-xl);
}

.title {
  font-size: var(--font-size-2xl);
  font-weight: var(--font-weight-bold);
  color: var(--color-primary);
  margin-bottom: var(--spacing-xs);
}

.subtitle {
  font-size: var(--font-size-lg);
  font-weight: var(--font-weight-medium);
  color: var(--color-text-primary);
  margin-bottom: var(--spacing-sm);
}

.desc {
  color: var(--color-text-muted);
  font-size: var(--font-size-sm);
}

.register-form {
  width: 100%;
}

.form-input {
  border-radius: var(--radius-md);
}

.input-icon {
  color: var(--color-text-muted);
}

.form-tips {
  text-align: right;
  color: var(--color-text-muted);
  font-size: var(--font-size-sm);
  margin-bottom: var(--spacing-md);
}

.login-link {
  color: var(--color-primary);
  font-weight: var(--font-weight-medium);
  transition: color var(--transition-fast);
}

.login-link:hover {
  color: var(--color-primary-light);
}

.submit-btn {
  width: 100%;
  background: var(--color-accent);
  border-color: var(--color-accent);
  font-weight: var(--font-weight-medium);
  border-radius: var(--radius-md);
  height: 44px;
  font-size: var(--font-size-base);
  transition: all var(--transition-fast);
}

.submit-btn:hover {
  background: var(--color-accent-dark);
  border-color: var(--color-accent-dark);
  transform: translateY(-1px);
  box-shadow: var(--shadow-md);
}

@media (max-width: 480px) {
  .register-container {
    padding: var(--spacing-xl) var(--spacing-md);
    margin: var(--spacing-md);
    border-radius: var(--radius-lg);
  }

  .title {
    font-size: var(--font-size-xl);
  }
}
</style>
