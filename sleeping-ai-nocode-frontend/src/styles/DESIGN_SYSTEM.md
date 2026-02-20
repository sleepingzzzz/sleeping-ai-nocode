# sleeping AI 零代码平台 - 设计系统规范文档

## 1. 色彩系统

### 1.1 主色调 (Primary)
| 变量名 | 色值 | 用途 |
|--------|------|------|
| `--color-primary` | `#0d9488` | 主要按钮、链接、选中状态 |
| `--color-primary-light` | `#14b8a6` | 悬停状态 |
| `--color-primary-lighter` | `#5eead4` | 边框高亮 |
| `--color-primary-lightest` | `#f0fdfa` | 背景色 |
| `--color-primary-dark` | `#0f766e` | 按钮按下状态 |
| `--color-primary-darker` | `#115e59` | 深色强调 |

### 1.2 强调色 (Accent)
| 变量名 | 色值 | 用途 |
|--------|------|------|
| `--color-accent` | `#f97316` | 重要操作按钮、注册按钮 |
| `--color-accent-light` | `#fb923c` | 悬停状态 |
| `--color-accent-lighter` | `#fdba74` | 边框高亮 |
| `--color-accent-dark` | `#ea580c` | 按钮按下状态 |
| `--color-accent-darker` | `#c2410c` | 深色强调 |

### 1.3 功能色
| 变量名 | 色值 | 用途 |
|--------|------|------|
| `--color-success` | `#22c55e` | 成功状态 |
| `--color-warning` | `#eab308` | 警告状态 |
| `--color-error` | `#ef4444` | 错误状态 |
| `--color-info` | `#3b82f6` | 信息提示 |

### 1.4 文字色
| 变量名 | 色值 | 用途 |
|--------|------|------|
| `--color-text-primary` | `#134e4a` | 主要文字 |
| `--color-text-secondary` | `#0f766e` | 次要文字 |
| `--color-text-muted` | `#6b7280` | 辅助文字 |
| `--color-text-light` | `#9ca3af` | 占位文字 |
| `--color-text-inverse` | `#ffffff` | 反色文字 |

### 1.5 背景色
| 变量名 | 色值 | 用途 |
|--------|------|------|
| `--color-bg-primary` | `#ffffff` | 主要背景 |
| `--color-bg-secondary` | `#f0fdfa` | 页面背景 |
| `--color-bg-tertiary` | `#ccfbf1` | 卡片悬停背景 |
| `--color-bg-muted` | `#f9fafb` | 滚动条背景 |

### 1.6 边框色
| 变量名 | 色值 | 用途 |
|--------|------|------|
| `--color-border` | `#e5e7eb` | 默认边框 |
| `--color-border-light` | `#f3f4f6` | 浅色边框 |
| `--color-border-dark` | `#d1d5db` | 深色边框 |

---

## 2. 排版系统

### 2.1 字体家族
```css
--font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans SC', sans-serif;
--font-family-mono: ui-monospace, SFMono-Regular, 'SF Mono', Menlo, Consolas, monospace;
```

### 2.2 字号层级
| 变量名 | 值 | 用途 |
|--------|-----|------|
| `--font-size-xs` | `12px` | 辅助文字、标签 |
| `--font-size-sm` | `14px` | 正文小号、表格 |
| `--font-size-base` | `16px` | 正文默认 |
| `--font-size-lg` | `18px` | 小标题 |
| `--font-size-xl` | `20px` | 四级标题 |
| `--font-size-2xl` | `24px` | 三级标题 |
| `--font-size-3xl` | `30px` | 二级标题 |
| `--font-size-4xl` | `36px` | 一级标题 |

### 2.3 字重
| 变量名 | 值 | 用途 |
|--------|-----|------|
| `--font-weight-normal` | `400` | 正文 |
| `--font-weight-medium` | `500` | 强调文字 |
| `--font-weight-semibold` | `600` | 标题 |
| `--font-weight-bold` | `700` | 大标题 |

### 2.4 行高
| 变量名 | 值 | 用途 |
|--------|-----|------|
| `--line-height-tight` | `1.25` | 标题 |
| `--line-height-normal` | `1.5` | 正文 |
| `--line-height-relaxed` | `1.625` | 长文本 |
| `--line-height-loose` | `2` | 引用 |

---

## 3. 间距系统

| 变量名 | 值 | 用途 |
|--------|-----|------|
| `--spacing-xs` | `4px` | 紧凑间距 |
| `--spacing-sm` | `8px` | 小间距 |
| `--spacing-md` | `16px` | 默认间距 |
| `--spacing-lg` | `24px` | 大间距 |
| `--spacing-xl` | `32px` | 区块间距 |
| `--spacing-2xl` | `48px` | 大区块间距 |
| `--spacing-3xl` | `64px` | 页面区块间距 |

---

## 4. 圆角系统

| 变量名 | 值 | 用途 |
|--------|-----|------|
| `--radius-sm` | `4px` | 小按钮、标签 |
| `--radius-md` | `8px` | 按钮、输入框 |
| `--radius-lg` | `12px` | 卡片 |
| `--radius-xl` | `16px` | 大卡片、模态框 |
| `--radius-full` | `9999px` | 圆形元素 |

---

## 5. 阴影系统

| 变量名 | 值 | 用途 |
|--------|-----|------|
| `--shadow-sm` | `0 1px 2px 0 rgba(0, 0, 0, 0.05)` | 轻微阴影 |
| `--shadow-md` | `0 4px 6px -1px rgba(0, 0, 0, 0.1)` | 卡片默认 |
| `--shadow-lg` | `0 10px 15px -3px rgba(0, 0, 0, 0.1)` | 悬浮状态 |
| `--shadow-xl` | `0 20px 25px -5px rgba(0, 0, 0, 0.1)` | 模态框 |

---

## 6. 动画系统

| 变量名 | 值 | 用途 |
|--------|-----|------|
| `--transition-fast` | `150ms ease` | 快速交互 |
| `--transition-normal` | `250ms ease` | 默认过渡 |
| `--transition-slow` | `350ms ease` | 复杂动画 |

---

## 7. 响应式断点

| 断点 | 宽度 | 用途 |
|------|------|------|
| 大屏 | `> 992px` | 桌面端完整布局 |
| 中屏 | `768px - 992px` | 平板端 |
| 小屏 | `480px - 768px` | 大手机 |
| 超小屏 | `< 480px` | 小手机 |

---

## 8. 组件规范

### 8.1 按钮
- **主按钮**: 背景色 `--color-primary`，悬停时上移1px并添加阴影
- **强调按钮**: 背景色 `--color-accent`，用于注册等重要操作
- **次要按钮**: 边框色 `--color-primary`，文字色 `--color-primary`

### 8.2 卡片
- 背景: `--color-bg-primary`
- 圆角: `--radius-lg`
- 阴影: `--shadow-md`
- 悬停: 上移4px，阴影加深，边框变为主色调浅色

### 8.3 表单
- 输入框圆角: `--radius-md`
- 图标颜色: `--color-text-muted`
- 标签字重: `--font-weight-medium`

---

## 9. 无障碍规范

- 所有交互元素具有 `focus-visible` 状态
- 文字对比度符合 WCAG AA 标准
- 支持键盘导航
- 语义化 HTML 结构

---

## 10. 文件结构

```
src/
├── styles/
│   ├── variables.css    # CSS 变量定义
│   └── global.css       # 全局样式
├── components/
│   ├── GlobalHeader.vue # 顶部导航
│   └── GlobalFooter.vue # 底部版权
├── layouts/
│   └── BasicLayout.vue  # 基础布局
└── pages/
    ├── HomePage.vue     # 首页
    ├── AboutPage.vue    # 关于页
    └── user/
        ├── UserLoginPage.vue    # 登录页
        └── UserRegisterPage.vue # 注册页
```

---

*文档版本: 1.0.0*
*最后更新: 2026-02-21*
