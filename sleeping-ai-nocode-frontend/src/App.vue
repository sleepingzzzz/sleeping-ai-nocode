<script setup lang="ts">
import BasicLayout from '@/layouts/BasicLayout.vue'
import zhCN from 'ant-design-vue/es/locale/zh_CN'
import { ConfigProvider } from 'ant-design-vue'
</script>

<template>
  <ConfigProvider :locale="zhCN">
    <BasicLayout />
  </ConfigProvider>
</template>
