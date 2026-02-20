<template>
  <a-layout-header class="header">
    <a-row :wrap="false" align="middle" class="header-row">
      <a-col class="logo-col">
        <RouterLink to="/">
          <div class="header-left">
            <img class="logo" src="@/assets/logo.svg" alt="Logo" />
            <h1 class="site-title">
              <span class="title-full">sleeping AI 零代码</span>
              <span class="title-short">sleeping AI</span>
            </h1>
          </div>
        </RouterLink>
      </a-col>
      <a-col flex="auto" class="nav-col">
        <a-menu
          v-model:selectedKeys="selectedKeys"
          mode="horizontal"
          :items="menuItems"
          class="nav-menu"
          @click="handleMenuClick"
        />
      </a-col>
      <a-col class="action-col">
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            <a-dropdown :trigger="['click']">
              <a-space class="user-info">
                <a-avatar :src="loginUserStore.loginUser.userAvatar" :size="36" class="user-avatar" />
                <span class="user-name">{{ loginUserStore.loginUser.userName ?? '无名' }}</span>
              </a-space>
              <template #overlay>
                <a-menu>
                  <a-menu-item key="logout" @click="handleLogout">
                    <LogoutOutlined />
                    退出登录
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" class="login-btn" @click="goToLogin">登录</a-button>
          </div>
        </div>
        <a-button
          class="mobile-menu-btn"
          type="text"
          @click="mobileMenuVisible = true"
        >
          <MenuOutlined />
        </a-button>
      </a-col>
    </a-row>
    <a-drawer
      v-model:open="mobileMenuVisible"
      placement="right"
      title="菜单"
      class="mobile-drawer"
      :width="280"
    >
      <a-menu
        v-model:selectedKeys="selectedKeys"
        mode="inline"
        :items="menuItems"
        class="mobile-nav-menu"
        @click="handleMobileMenuClick"
      />
      <div v-if="loginUserStore.loginUser.id" class="mobile-user-section">
        <div class="mobile-user-info">
          <a-avatar :src="loginUserStore.loginUser.userAvatar" :size="48" class="mobile-avatar" />
          <div class="mobile-user-detail">
            <span class="mobile-user-name">{{ loginUserStore.loginUser.userName ?? '无名' }}</span>
            <a-button type="link" danger class="logout-link" @click="handleLogout">
              <LogoutOutlined /> 退出登录
            </a-button>
          </div>
        </div>
      </div>
    </a-drawer>
  </a-layout-header>
</template>

<script setup lang="ts">
import { computed, h, ref } from 'vue'
import { useRouter } from 'vue-router'
import type { MenuProps } from 'ant-design-vue'
import { HomeOutlined, InfoCircleOutlined, LogoutOutlined, UserOutlined, MenuOutlined } from '@ant-design/icons-vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { userLogout } from '@/api/userController.ts'
import { message } from 'ant-design-vue'

const router = useRouter()
const loginUserStore = useLoginUserStore()
const selectedKeys = ref<string[]>(['/'])
const mobileMenuVisible = ref(false)

router.afterEach((to) => {
  selectedKeys.value = [to.path]
})

const originItems: MenuProps['items'] = [
  {
    key: '/',
    icon: () => h(HomeOutlined),
    label: '主页',
    title: '主页',
  },
  {
    key: '/about',
    icon: () => h(InfoCircleOutlined),
    label: '关于',
    title: '关于我们',
  },
  {
    key: '/admin/userManage',
    icon: () => h(UserOutlined),
    label: '用户管理',
    title: '用户管理',
  },
]

const filterMenus = (menus: MenuProps['items'] = []): MenuProps['items'] => {
  return menus?.filter((menu) => {
    const menuKey = menu?.key as string
    if (menuKey?.startsWith('/admin')) {
      const loginUser = loginUserStore.loginUser
      if (!loginUser || loginUser.userRole !== 'admin') {
        return false
      }
    }
    return true
  })
}

const menuItems = computed<MenuProps['items']>(() => filterMenus(originItems))

const handleMenuClick: MenuProps['onClick'] = (e) => {
  const key = e.key as string
  selectedKeys.value = [key]
  if (key.startsWith('/')) {
    router.push(key)
  }
}

const handleMobileMenuClick: MenuProps['onClick'] = (e) => {
  const key = e.key as string
  selectedKeys.value = [key]
  mobileMenuVisible.value = false
  if (key.startsWith('/')) {
    router.push(key)
  }
}

const goToLogin = () => {
  mobileMenuVisible.value = false
  router.push('/user/login')
}

const handleLogout = async () => {
  const res = await userLogout()
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({})
    mobileMenuVisible.value = false
    message.success('退出登录成功')
    router.push({
      path: '/',
      replace: true,
    })
  } else {
    message.error('退出登录失败，' + res.data.message)
  }
}
</script>

<style scoped>
.header {
  background: var(--color-bg-primary);
  padding: 0 var(--spacing-lg);
  box-shadow: var(--shadow-sm);
  position: sticky;
  top: 0;
  z-index: var(--z-index-sticky);
  height: 64px;
  line-height: 64px;
  border-bottom: 1px solid var(--color-border-light);
}

.header-row {
  height: 100%;
  max-width: 1600px;
  margin: 0 auto;
}

.logo-col {
  flex-shrink: 0;
}

.header-left {
  display: flex;
  align-items: center;
  gap: var(--spacing-md);
  text-decoration: none;
  min-width: 0;
  overflow: hidden;
  transition: opacity var(--transition-fast);
}

.header-left:hover {
  opacity: 0.85;
}

.logo {
  height: 40px;
  width: 40px;
  object-fit: contain;
  flex-shrink: 0;
  transition: transform var(--transition-normal);
}

.header-left:hover .logo {
  transform: scale(1.05);
}

.site-title {
  margin: 0;
  font-size: var(--font-size-lg);
  font-weight: var(--font-weight-semibold);
  color: var(--color-primary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  min-width: 0;
}

.title-short {
  display: none;
}

.nav-col {
  min-width: 0;
  overflow: hidden;
}

.nav-menu {
  border-bottom: none !important;
  line-height: 62px;
  justify-content: flex-start;
  padding-left: var(--spacing-lg);
}

.nav-menu :deep(.ant-menu-item) {
  color: var(--color-text-primary);
  font-weight: var(--font-weight-medium);
  transition: color var(--transition-fast);
}

.nav-menu :deep(.ant-menu-item::after) {
  border-bottom: none !important;
}

.nav-menu :deep(.ant-menu-item-selected) {
  color: var(--color-primary);
}

.nav-menu :deep(.ant-menu-item-selected::after) {
  border-bottom: 2px solid var(--color-primary) !important;
}

.nav-menu :deep(.ant-menu-item:hover) {
  color: var(--color-primary-light);
}

.nav-menu :deep(.ant-menu-item:hover::after) {
  border-bottom: 2px solid var(--color-primary-light) !important;
}

.action-col {
  flex-shrink: 0;
  display: flex;
  align-items: center;
  gap: var(--spacing-sm);
  margin-left: auto;
}

.user-login-status {
  display: flex;
  align-items: center;
}

.user-info {
  cursor: pointer;
  padding: var(--spacing-xs) var(--spacing-sm);
  border-radius: var(--radius-md);
  transition: background-color var(--transition-fast);
  display: flex;
  align-items: center;
  gap: var(--spacing-xs);
}

.user-info:hover {
  background-color: var(--color-bg-tertiary);
}

.user-avatar {
  border: 2px solid var(--color-primary);
  flex-shrink: 0;
}

.user-name {
  color: var(--color-text-primary);
  font-weight: var(--font-weight-medium);
  white-space: nowrap;
}

.login-btn {
  background: var(--color-primary);
  border-color: var(--color-primary);
  font-weight: var(--font-weight-medium);
  border-radius: var(--radius-md);
  transition: all var(--transition-fast);
  min-width: 72px;
}

.login-btn:hover {
  background: var(--color-primary-dark);
  border-color: var(--color-primary-dark);
  transform: translateY(-1px);
  box-shadow: var(--shadow-md);
}

.login-btn:active {
  transform: translateY(0);
}

.mobile-menu-btn {
  display: none;
  font-size: 20px;
  color: var(--color-text-primary);
}

.mobile-nav-menu {
  border-inline-end: none !important;
}

.mobile-nav-menu :deep(.ant-menu-item) {
  color: var(--color-text-primary);
  font-weight: var(--font-weight-medium);
  border-radius: var(--radius-md);
  margin: 4px 0;
}

.mobile-nav-menu :deep(.ant-menu-item-selected) {
  background-color: var(--color-bg-secondary);
  color: var(--color-primary);
}

.mobile-nav-menu :deep(.ant-menu-item:hover) {
  color: var(--color-primary-light);
}

.mobile-user-section {
  margin-top: var(--spacing-lg);
  padding: var(--spacing-lg);
  background: var(--color-bg-secondary);
  border-radius: var(--radius-lg);
  border: 1px solid var(--color-border-light);
}

.mobile-user-info {
  display: flex;
  align-items: center;
  gap: var(--spacing-md);
}

.mobile-avatar {
  border: 2px solid var(--color-primary);
}

.mobile-user-detail {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-xs);
}

.mobile-user-name {
  font-weight: var(--font-weight-semibold);
  color: var(--color-text-primary);
  font-size: var(--font-size-base);
}

.logout-link {
  padding: 0;
  height: auto;
  font-size: var(--font-size-sm);
}

@media (max-width: 1200px) {
  .site-title {
    font-size: var(--font-size-base);
  }
  
  .header-left {
    gap: var(--spacing-sm);
  }
  
  .nav-menu {
    padding-left: var(--spacing-md);
  }
}

@media (max-width: 992px) {
  .header {
    padding: 0 var(--spacing-md);
  }
  
  .site-title {
    font-size: var(--font-size-sm);
  }
  
  .logo {
    height: 36px;
    width: 36px;
  }
  
  .nav-menu {
    gap: 0;
    padding-left: var(--spacing-sm);
  }
  
  .nav-menu :deep(.ant-menu-item) {
    padding: 0 12px;
    font-size: var(--font-size-sm);
  }
  
  .user-name {
    display: none;
  }
  
  .user-info {
    padding: var(--spacing-xs);
  }
}

@media (max-width: 768px) {
  .header {
    padding: 0 var(--spacing-md);
  }
  
  .header-left {
    gap: var(--spacing-sm);
  }
  
  .title-full {
    display: none;
  }
  
  .title-short {
    display: inline;
  }
  
  .site-title {
    font-size: var(--font-size-base);
  }
  
  .logo {
    height: 32px;
    width: 32px;
  }
  
  .nav-col {
    display: none;
  }
  
  .user-login-status {
    display: flex;
  }
  
  .user-name {
    display: none;
  }
  
  .user-avatar {
    width: 36px;
    height: 36px;
  }
  
  .login-btn {
    min-width: 60px;
    padding: 4px 12px;
  }
  
  .mobile-menu-btn {
    display: flex;
    align-items: center;
    justify-content: center;
  }
}

@media (max-width: 480px) {
  .header {
    padding: 0 var(--spacing-sm);
  }
  
  .site-title {
    font-size: var(--font-size-sm);
  }
  
  .logo {
    height: 28px;
    width: 28px;
  }
  
  .user-avatar {
    width: 32px;
    height: 32px;
  }
  
  .login-btn {
    min-width: 50px;
    padding: 4px 10px;
    font-size: var(--font-size-sm);
  }
  
  .mobile-menu-btn {
    font-size: 18px;
  }
}
</style>
