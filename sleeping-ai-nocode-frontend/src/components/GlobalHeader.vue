<template>
  <a-layout-header class="header">
    <a-row :wrap="false" align="middle">
      <a-col flex="200px">
        <RouterLink to="/">
          <div class="header-left">
            <img class="logo" src="@/assets/logo.svg" alt="Logo" />
            <h1 class="site-title">sleeping AI 零代码</h1>
          </div>
        </RouterLink>
      </a-col>
      <a-col flex="auto">
        <a-menu
          v-model:selectedKeys="selectedKeys"
          mode="horizontal"
          :items="menuItems"
          class="nav-menu"
          @click="handleMenuClick"
        />
      </a-col>
      <a-col>
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            <a-dropdown>
              <a-space class="user-info">
                <a-avatar :src="loginUserStore.loginUser.userAvatar" />
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
      </a-col>
    </a-row>
  </a-layout-header>
</template>

<script setup lang="ts">
import { computed, h, ref } from 'vue'
import { useRouter } from 'vue-router'
import type { MenuProps } from 'ant-design-vue'
import { HomeOutlined, InfoCircleOutlined, LogoutOutlined, UserOutlined } from '@ant-design/icons-vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { userLogout } from '@/api/userController.ts'
import { message } from 'ant-design-vue'

const router = useRouter()
const loginUserStore = useLoginUserStore()
const selectedKeys = ref<string[]>(['/'])

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

const goToLogin = () => {
  router.push('/user/login')
}

const handleLogout = async () => {
  const res = await userLogout()
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({})
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

.nav-menu {
  border-bottom: none !important;
  line-height: 62px;
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

.user-login-status {
  display: flex;
  align-items: center;
  flex-shrink: 0;
}

.user-info {
  cursor: pointer;
  padding: var(--spacing-xs) var(--spacing-sm);
  border-radius: var(--radius-md);
  transition: background-color var(--transition-fast);
}

.user-info:hover {
  background-color: var(--color-bg-tertiary);
}

.user-name {
  color: var(--color-text-primary);
  font-weight: var(--font-weight-medium);
}

.login-btn {
  background: var(--color-accent);
  border-color: var(--color-accent);
  font-weight: var(--font-weight-medium);
  border-radius: var(--radius-md);
  transition: all var(--transition-fast);
}

.login-btn:hover {
  background: var(--color-accent-dark);
  border-color: var(--color-accent-dark);
  transform: translateY(-1px);
  box-shadow: var(--shadow-md);
}

.login-btn:active {
  transform: translateY(0);
}

@media (max-width: 992px) {
  .site-title {
    font-size: var(--font-size-base);
  }
}

@media (max-width: 768px) {
  .header {
    padding: 0 var(--spacing-md);
  }

  .header-left {
    gap: var(--spacing-sm);
  }

  .site-title {
    font-size: var(--font-size-sm);
  }

  .logo {
    height: 32px;
    width: 32px;
  }
}
</style>
