<template>
  <main class="user-manage-page">
    <div class="page-container">
      <div class="page-header">
        <h2 class="page-title">用户管理</h2>
        <p class="page-desc">管理系统中的所有用户</p>
      </div>

      <div class="search-section">
        <a-form layout="inline" :model="searchParams" @finish="doSearch">
          <a-form-item label="账号">
            <a-input v-model:value="searchParams.userAccount" placeholder="输入账号" allow-clear />
          </a-form-item>
          <a-form-item label="用户名">
            <a-input v-model:value="searchParams.userName" placeholder="输入用户名" allow-clear />
          </a-form-item>
          <a-form-item>
            <a-space>
              <a-button type="primary" html-type="submit">搜索</a-button>
              <a-button @click="doReset">重置</a-button>
            </a-space>
          </a-form-item>
        </a-form>
      </div>

      <a-divider />

      <div class="table-section">
        <a-table
          :columns="columns"
          :data-source="data"
          :pagination="pagination"
          :loading="loading"
          @change="doTableChange"
        >
          <template #bodyCell="{ column, record }">
            <template v-if="column.dataIndex === 'userAvatar'">
              <a-avatar :src="record.userAvatar" :size="40">
                {{ record.userName?.charAt(0)?.toUpperCase() || 'U' }}
              </a-avatar>
            </template>
            <template v-else-if="column.dataIndex === 'userRole'">
              <a-tag v-if="record.userRole === 'admin'" color="green">管理员</a-tag>
              <a-tag v-else color="blue">普通用户</a-tag>
            </template>
            <template v-else-if="column.dataIndex === 'createTime'">
              {{ formatDate(record.createTime) }}
            </template>
            <template v-else-if="column.key === 'action'">
              <a-space>
                <a-button type="link" size="small" @click="doView(record)">查看</a-button>
                <a-popconfirm
                  :title="getDeleteConfirmTitle(record)"
                  :ok-text="record.id === currentUserId ? '我知道了' : '确定'"
                  :cancel-text="record.id === currentUserId ? undefined : '取消'"
                  :show-cancel="record.id !== currentUserId"
                  @confirm="doDelete(record)"
                >
                  <a-button type="link" danger size="small">删除</a-button>
                </a-popconfirm>
              </a-space>
            </template>
          </template>
        </a-table>
      </div>
    </div>

    <a-modal
      v-model:open="viewModalVisible"
      title="用户详情"
      :footer="null"
      width="500px"
    >
      <a-descriptions :column="1" bordered v-if="currentUser">
        <a-descriptions-item label="ID">{{ currentUser.id }}</a-descriptions-item>
        <a-descriptions-item label="账号">{{ currentUser.userAccount }}</a-descriptions-item>
        <a-descriptions-item label="用户名">{{ currentUser.userName || '-' }}</a-descriptions-item>
        <a-descriptions-item label="头像">
          <a-avatar :src="currentUser.userAvatar" :size="40">
            {{ currentUser.userName?.charAt(0)?.toUpperCase() || 'U' }}
          </a-avatar>
        </a-descriptions-item>
        <a-descriptions-item label="简介">{{ currentUser.userProfile || '-' }}</a-descriptions-item>
        <a-descriptions-item label="用户角色">
          <a-tag v-if="currentUser.userRole === 'admin'" color="green">管理员</a-tag>
          <a-tag v-else color="blue">普通用户</a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="创建时间">{{ formatDate(currentUser.createTime) }}</a-descriptions-item>
      </a-descriptions>
    </a-modal>
  </main>
</template>

<script lang="ts" setup>
import { computed, onMounted, reactive, ref } from 'vue'
import { deleteUser, listUserVoByPage } from '@/api/userController.ts'
import { message } from 'ant-design-vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'

interface PaginationParams {
  current: number
  pageSize: number
}

const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    width: 80,
  },
  {
    title: '账号',
    dataIndex: 'userAccount',
    width: 120,
  },
  {
    title: '用户名',
    dataIndex: 'userName',
    width: 120,
  },
  {
    title: '头像',
    dataIndex: 'userAvatar',
    width: 80,
  },
  {
    title: '简介',
    dataIndex: 'userProfile',
    ellipsis: true,
  },
  {
    title: '用户角色',
    dataIndex: 'userRole',
    width: 100,
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    width: 180,
  },
  {
    title: '操作',
    key: 'action',
    width: 150,
  },
]

const data = ref<API.UserVO[]>([])
const total = ref(0)
const loading = ref(false)
const viewModalVisible = ref(false)
const currentUser = ref<API.UserVO | null>(null)

const loginUserStore = useLoginUserStore()
const currentUserId = computed(() => loginUserStore.loginUser.id)

const searchParams = reactive<API.UserQueryRequest>({
  pageNum: 1,
  pageSize: 10,
})

const fetchData = async () => {
  loading.value = true
  try {
    const res = await listUserVoByPage({
      ...searchParams,
    })
    if (res.data.code === 0 && res.data.data) {
      data.value = res.data.data.records ?? []
      total.value = res.data.data.totalRow ?? 0
    } else {
      message.error('获取数据失败，' + res.data.message)
    }
  } catch {
    message.error('获取数据失败')
  } finally {
    loading.value = false
  }
}

const pagination = computed(() => {
  return {
    current: searchParams.pageNum ?? 1,
    pageSize: searchParams.pageSize ?? 10,
    total: total.value,
    showSizeChanger: true,
    showTotal: (total: number) => `共 ${total} 条`,
    pageSizeOptions: ['10', '20', '50', '100'],
  }
})

const doTableChange = (page: PaginationParams) => {
  searchParams.pageNum = page.current
  searchParams.pageSize = page.pageSize
  fetchData()
}

const doSearch = () => {
  searchParams.pageNum = 1
  fetchData()
}

const doReset = () => {
  searchParams.userAccount = undefined
  searchParams.userName = undefined
  searchParams.pageNum = 1
  fetchData()
}

const getDeleteConfirmTitle = (record: API.UserVO) => {
  if (record.id === currentUserId.value) {
    return '不能删除当前登录用户！'
  }
  return `确定要删除用户 "${record.userName || record.userAccount}" 吗？`
}

const doDelete = async (record: API.UserVO) => {
  if (!record.id) {
    return
  }
  if (record.id === currentUserId.value) {
    return
  }
  const res = await deleteUser({ id: record.id })
  if (res.data.code === 0) {
    message.success('删除成功')
    fetchData()
  } else {
    message.error('删除失败，' + res.data.message)
  }
}

const doView = (record: API.UserVO) => {
  currentUser.value = record
  viewModalVisible.value = true
}

const formatDate = (dateStr?: string) => {
  if (!dateStr) return '-'
  const date = new Date(dateStr)
  return date.toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
  })
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.user-manage-page {
  width: 100%;
  padding: var(--spacing-lg);
}

.page-container {
  background: var(--color-bg-primary);
  border-radius: var(--radius-lg);
  padding: var(--spacing-xl);
  box-shadow: var(--shadow-sm);
}

.page-header {
  margin-bottom: var(--spacing-lg);
}

.page-title {
  font-size: var(--font-size-2xl);
  font-weight: var(--font-weight-bold);
  color: var(--color-text-primary);
  margin-bottom: var(--spacing-xs);
}

.page-desc {
  color: var(--color-text-muted);
  font-size: var(--font-size-sm);
}

.search-section {
  margin-bottom: var(--spacing-md);
}

.table-section {
  margin-top: var(--spacing-md);
}

:deep(.ant-table) {
  border-radius: var(--radius-md);
}

:deep(.ant-table-thead > tr > th) {
  background: var(--color-bg-tertiary);
  font-weight: var(--font-weight-semibold);
}

:deep(.ant-table-tbody > tr:hover > td) {
  background: var(--color-bg-secondary);
}

@media (max-width: 768px) {
  .user-manage-page {
    padding: var(--spacing-md);
  }

  .page-container {
    padding: var(--spacing-md);
  }
}
</style>
