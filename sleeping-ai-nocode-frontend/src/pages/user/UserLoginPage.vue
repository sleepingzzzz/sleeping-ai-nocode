<template>
  <main class="login-page">
    <div class="login-container">
      <div class="login-header">
        <h2 class="title">sleeping AI 零代码</h2>
        <p class="subtitle">用户登录</p>
        <p class="desc">不写一行代码，生成完整应用</p>
      </div>
      <a-form
        :model="formState"
        name="loginForm"
        autocomplete="off"
        @finish="handleSubmit"
        class="login-form"
      >
        <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入账号' }]">
          <a-input
            v-model:value="formState.userAccount"
            placeholder="请输入账号"
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
            { min: 8, message: '密码长度不能小于 8 位' },
          ]"
        >
          <a-input-password
            v-model:value="formState.userPassword"
            placeholder="请输入密码"
            size="large"
            class="form-input"
          >
            <template #prefix>
              <LockOutlined class="input-icon" />
            </template>
          </a-input-password>
        </a-form-item>
        <div class="form-tips">
          没有账号？
          <RouterLink to="/user/register" class="register-link">去注册</RouterLink>
        </div>
        <a-form-item>
          <a-button
            type="primary"
            html-type="submit"
            size="large"
            class="submit-btn"
            :loading="loading"
          >
            登录
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </main>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { message } from 'ant-design-vue'
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue'
import { userLogin } from '@/api/userController.ts'
import { useLoginUserStore } from '@/stores/loginUser.ts'

const formState = reactive<API.UserLoginRequest>({
  userAccount: '',
  userPassword: '',
})

const loading = ref(false)
const router = useRouter()
const route = useRoute()
const loginUserStore = useLoginUserStore()

const handleSubmit = async (values: API.UserLoginRequest) => {
  loading.value = true
  try {
    const res = await userLogin(values)
    if (res.data.code === 0 && res.data.data) {
      await loginUserStore.fetchLoginUser()
      message.success('登录成功')
      const redirect = route.query.redirect as string
      router.push({
        path: redirect || '/',
        replace: true,
      })
    } else {
      message.error('登录失败，' + res.data.message)
    }
  } catch {
    message.error('登录失败，网络错误')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-page {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 200px);
}

.login-container {
  background: var(--color-bg-primary);
  max-width: 420px;
  width: 100%;
  padding: var(--spacing-2xl);
  border-radius: var(--radius-xl);
  box-shadow: var(--shadow-lg);
  border: 1px solid var(--color-border-light);
}

.login-header {
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

.login-form {
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

.register-link {
  color: var(--color-primary);
  font-weight: var(--font-weight-medium);
  transition: color var(--transition-fast);
}

.register-link:hover {
  color: var(--color-primary-light);
}

.submit-btn {
  width: 100%;
  background: var(--color-primary);
  border-color: var(--color-primary);
  font-weight: var(--font-weight-medium);
  border-radius: var(--radius-md);
  height: 44px;
  font-size: var(--font-size-base);
  transition: all var(--transition-fast);
}

.submit-btn:hover {
  background: var(--color-primary-dark);
  border-color: var(--color-primary-dark);
  transform: translateY(-1px);
  box-shadow: var(--shadow-md);
}

@media (max-width: 480px) {
  .login-container {
    padding: var(--spacing-xl) var(--spacing-md);
    margin: var(--spacing-md);
    border-radius: var(--radius-lg);
  }

  .title {
    font-size: var(--font-size-xl);
  }
}
</style>
