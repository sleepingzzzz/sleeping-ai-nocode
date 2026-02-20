<script setup lang="ts">
import { onMounted } from 'vue'
import { healthCheck } from '@/api/healthController.ts'

onMounted(() => {
  healthCheck().then((res) => {
    console.log('健康检查响应:', res)
  })
})
</script>

<template>
  <main class="home-page">
    <div class="home-container">
      <div class="hero-section">
        <h1 class="welcome-title">欢迎使用 sleeping AI 零代码平台</h1>
        <p class="welcome-desc">不写一行代码，生成完整应用</p>
        <div class="action-buttons">
          <RouterLink to="/user/register">
            <a-button type="primary" size="large" class="primary-btn">
              立即开始
            </a-button>
          </RouterLink>
          <RouterLink to="/about">
            <a-button size="large" class="secondary-btn">
              了解更多
            </a-button>
          </RouterLink>
        </div>
      </div>
      <div class="feature-cards">
        <RouterLink to="/about" class="feature-card-link">
          <div class="feature-card about-card">
            <div class="feature-icon">💡</div>
            <h3 class="feature-title">关于我们</h3>
            <p class="feature-desc">了解 sleeping AI 的使命与愿景</p>
          </div>
        </RouterLink>
        <div class="feature-card">
          <div class="feature-icon">⚡</div>
          <h3 class="feature-title">快速开发</h3>
          <p class="feature-desc">通过可视化界面快速构建应用，无需编写代码</p>
        </div>
        <div class="feature-card">
          <div class="feature-icon">🎨</div>
          <h3 class="feature-title">精美设计</h3>
          <p class="feature-desc">内置丰富的UI组件和模板，打造专业级界面</p>
        </div>
        <div class="feature-card">
          <div class="feature-icon">🚀</div>
          <h3 class="feature-title">一键部署</h3>
          <p class="feature-desc">支持多种部署方式，轻松发布您的应用</p>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>
.home-page {
  width: 100%;
}

.home-container {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-3xl);
}

.hero-section {
  text-align: center;
  padding: var(--spacing-3xl) var(--spacing-md);
  background: linear-gradient(135deg, var(--color-bg-primary) 0%, var(--color-bg-tertiary) 100%);
  border-radius: var(--radius-xl);
  box-shadow: var(--shadow-lg);
}

.welcome-title {
  font-size: var(--font-size-4xl);
  font-weight: var(--font-weight-bold);
  color: var(--color-text-primary);
  margin-bottom: var(--spacing-md);
  line-height: var(--line-height-tight);
}

.welcome-desc {
  font-size: var(--font-size-xl);
  color: var(--color-text-muted);
  margin-bottom: var(--spacing-xl);
}

.action-buttons {
  display: flex;
  gap: var(--spacing-md);
  justify-content: center;
  flex-wrap: wrap;
}

.primary-btn {
  background: var(--color-primary);
  border-color: var(--color-primary);
  font-weight: var(--font-weight-medium);
  border-radius: var(--radius-md);
  padding: var(--spacing-sm) var(--spacing-xl);
  height: auto;
  transition: all var(--transition-fast);
}

.primary-btn:hover {
  background: var(--color-primary-dark);
  border-color: var(--color-primary-dark);
  transform: translateY(-2px);
  box-shadow: var(--shadow-lg);
}

.secondary-btn {
  border-color: var(--color-primary);
  color: var(--color-primary);
  font-weight: var(--font-weight-medium);
  border-radius: var(--radius-md);
  padding: var(--spacing-sm) var(--spacing-xl);
  height: auto;
  transition: all var(--transition-fast);
}

.secondary-btn:hover {
  background: var(--color-primary-lightest);
  border-color: var(--color-primary-light);
  color: var(--color-primary-dark);
  transform: translateY(-2px);
}

.feature-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: var(--spacing-lg);
}

.feature-card-link {
  text-decoration: none;
}

.feature-card {
  background: var(--color-bg-primary);
  padding: var(--spacing-xl);
  border-radius: var(--radius-lg);
  box-shadow: var(--shadow-md);
  text-align: center;
  transition: all var(--transition-normal);
  border: 1px solid var(--color-border-light);
}

.feature-card-link .feature-card:hover,
.feature-card:hover {
  transform: translateY(-4px);
  box-shadow: var(--shadow-xl);
  border-color: var(--color-primary-lighter);
}

.about-card {
  background: linear-gradient(135deg, var(--color-bg-primary) 0%, var(--color-bg-tertiary) 100%);
  border-color: var(--color-primary-lighter);
}

.about-card:hover {
  border-color: var(--color-accent-lighter);
}

.feature-icon {
  font-size: 48px;
  margin-bottom: var(--spacing-md);
}

.feature-title {
  font-size: var(--font-size-xl);
  font-weight: var(--font-weight-semibold);
  color: var(--color-text-primary);
  margin-bottom: var(--spacing-sm);
}

.feature-desc {
  font-size: var(--font-size-sm);
  color: var(--color-text-muted);
  line-height: var(--line-height-relaxed);
}

@media (max-width: 768px) {
  .hero-section {
    padding: var(--spacing-xl) var(--spacing-md);
  }

  .welcome-title {
    font-size: var(--font-size-2xl);
  }

  .welcome-desc {
    font-size: var(--font-size-base);
  }

  .feature-cards {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 480px) {
  .welcome-title {
    font-size: var(--font-size-xl);
  }

  .action-buttons {
    flex-direction: column;
  }

  .action-buttons .ant-btn {
    width: 100%;
  }
}
</style>
