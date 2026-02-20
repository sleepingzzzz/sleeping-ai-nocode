<template>
  <main class="about-page">
    <div class="page-container">
      <div class="page-header">
        <h2 class="page-title">关于我们</h2>
        <p class="page-desc">了解 sleeping AI 零代码平台</p>
      </div>

      <a-card class="info-card">
        <template #title>
          <span class="card-title">sleeping AI 零代码平台</span>
        </template>
        <div class="card-content">
          <p class="intro-text">
            sleeping AI 零代码平台是一款基于人工智能技术的应用开发平台，致力于帮助用户在不编写代码的情况下快速构建完整的应用程序。
          </p>
          <a-divider />
          <h3 class="section-title">核心功能</h3>
          <a-row :gutter="[16, 16]">
            <a-col :xs="24" :sm="12" :md="8">
              <a-card hoverable class="feature-card">
                <template #cover>
                  <div class="feature-icon">
                    <AppstoreOutlined />
                  </div>
                </template>
                <a-card-meta title="可视化开发" description="拖拽式界面设计，所见即所得" />
              </a-card>
            </a-col>
            <a-col :xs="24" :sm="12" :md="8">
              <a-card hoverable class="feature-card">
                <template #cover>
                  <div class="feature-icon">
                    <RobotOutlined />
                  </div>
                </template>
                <a-card-meta title="AI 辅助" description="智能代码生成，自动优化建议" />
              </a-card>
            </a-col>
            <a-col :xs="24" :sm="12" :md="8">
              <a-card hoverable class="feature-card">
                <template #cover>
                  <div class="feature-icon">
                    <CloudOutlined />
                  </div>
                </template>
                <a-card-meta title="云端部署" description="一键部署，自动扩缩容" />
              </a-card>
            </a-col>
          </a-row>
          <a-divider />
          <h3 class="section-title">技术栈</h3>
          <a-space wrap>
            <a-tag color="blue">Vue 3</a-tag>
            <a-tag color="green">Spring Boot</a-tag>
            <a-tag color="purple">TypeScript</a-tag>
            <a-tag color="orange">Ant Design Vue</a-tag>
            <a-tag color="cyan">Pinia</a-tag>
            <a-tag color="magenta">MySQL</a-tag>
          </a-space>
        </div>
      </a-card>
    </div>
  </main>
</template>

<script setup lang="ts">
import { AppstoreOutlined, RobotOutlined, CloudOutlined } from '@ant-design/icons-vue'
</script>

<style scoped>
.about-page {
  width: 100%;
  padding: var(--spacing-lg);
}

.page-container {
  max-width: 1200px;
  margin: 0 auto;
}

.page-header {
  margin-bottom: var(--spacing-xl);
  text-align: center;
}

.page-title {
  font-size: var(--font-size-2xl);
  font-weight: var(--font-weight-bold);
  color: var(--color-text-primary);
  margin-bottom: var(--spacing-xs);
}

.page-desc {
  color: var(--color-text-muted);
  font-size: var(--font-size-base);
}

.info-card {
  border-radius: var(--radius-lg);
  box-shadow: var(--shadow-sm);
}

.card-title {
  font-size: var(--font-size-lg);
  font-weight: var(--font-weight-semibold);
}

.card-content {
  padding: var(--spacing-md) 0;
}

.intro-text {
  font-size: var(--font-size-base);
  line-height: 1.8;
  color: var(--color-text-secondary);
}

.section-title {
  font-size: var(--font-size-lg);
  font-weight: var(--font-weight-semibold);
  color: var(--color-text-primary);
  margin-bottom: var(--spacing-md);
}

.feature-card {
  text-align: center;
  border-radius: var(--radius-md);
}

.feature-icon {
  font-size: 48px;
  padding: var(--spacing-xl);
  background: linear-gradient(135deg, var(--color-primary-light), var(--color-primary));
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
}

:deep(.ant-card-meta-title) {
  font-weight: var(--font-weight-semibold);
}

:deep(.ant-card-meta-description) {
  color: var(--color-text-muted);
}

@media (max-width: 768px) {
  .about-page {
    padding: var(--spacing-md);
  }
}
</style>
