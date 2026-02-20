<template>
  <a-layout-footer class="footer">
    <div class="footer-content">
      <p class="copyright">
        sleeping AI 零代码平台 - 让创意触手可及
      </p>
    </div>
  </a-layout-footer>
</template>

<script setup lang="ts">
</script>

<style scoped>
.footer {
  background: var(--color-bg-primary);
  text-align: center;
  padding: var(--spacing-lg) var(--spacing-md);
  border-top: 1px solid var(--color-border);
  margin-top: auto;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
}

.copyright {
  margin: 0;
  color: var(--color-text-primary);
  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-medium);
}

@media (max-width: 768px) {
  .footer {
    padding: var(--spacing-md) var(--spacing-sm);
  }

  .copyright {
    font-size: var(--font-size-xs);
  }
}
</style>
