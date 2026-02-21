# 变更日志

本文件记录了项目的所有重要变更。

格式基于 [Keep a Changelog](https://keepachangelog.com/zh-CN/1.0.0/)，
版本号遵循 [语义化版本](https://semver.org/lang/zh-CN/) 规范。

## [1.0.0] - 2026-02-21

### 新增功能

#### AI 代码生成
- **AI 代码生成服务** - 核心 AI 驱动的代码生成功能
  - `AiCodeGeneratorService` - 代码生成抽象服务接口
  - `AiCodeGeneratorServiceFactory` - 工厂模式创建生成器实例
  - 支持 HTML 和多文件代码生成

- **代码生成模型**
  - `HtmlCodeResult` - HTML 代码生成结果模型
  - `MultiFileCodeResult` - 多文件代码生成结果模型
  - `CodeGenTypeEnum` - 代码生成类型枚举

- **代码解析系统**
  - `CodeParser` - 抽象解析器接口
  - `CodeParserExecutor` - 解析器执行器
  - `HtmlCodeParser` - HTML 代码解析器
  - `MultiFileCodeParser` - 多文件项目解析器

- **代码文件保存系统**
  - `CodeFileSaverTemplate` - 模板方法模式的文件保存
  - `CodeFileSaverExecutor` - 文件保存执行器
  - `HtmlCodeFileSaverTemplate` - HTML 代码保存器
  - `MultiFileCodeFileSaverTemplate` - 多文件项目保存器

- **AI 代码生成门面**
  - `AiCodeGeneratorFacade` - 代码生成工作流的统一接口

- **提示词模板**
  - `codegen-html-system-prompt.txt` - HTML 生成的系统提示词
  - `codegen-multi-file-system-prompt.txt` - 多文件生成的系统提示词

#### 用户界面
- **响应式头部组件**
  - 多断点响应式设计（xl, lg, md, sm, xs）
  - 移动端抽屉导航菜单
  - 自适应标题显示（完整/简短版本）
  - 固定头部与平滑过渡动画

- **设计系统**
  - 使用 CSS 变量的统一配色方案
  - 主色调：Teal 青色（#0d9488）
  - 统一的圆角、阴影和间距
  - 自定义 CSS 变量支持主题定制

### 变更

#### UI/UX 改进
- **登录按钮**
  - 颜色从紫色（#8b5cf6）更改为主题青色（#0d9488）
  - 固定显示在头部右侧
  - 改进悬停和激活状态样式

- **用户头像**
  - 添加青色边框以保持视觉一致性
  - 修复所有屏幕尺寸下的显示问题
  - 保持下拉菜单的点击交互

- **导航菜单**
  - 对齐方式从居中改为左对齐
  - 添加响应式内边距调整
  - 改进移动端菜单样式

- **移动端抽屉**
  - 移除重复的登录按钮
  - 为已登录用户添加样式化的用户信息区域
  - 统一菜单项样式与主题配色

### 修复

- Spring Security 依赖版本冲突
- 时间字段的 SQL 完整性约束违规
- 窄屏幕下标题溢出问题
- 移动设备上登录按钮可见性

### 安全

- 使用 Spring Security Crypto 进行密码加密
- API 端点速率限制
- XSS 防护的输入消毒
- 可配置超时的会话管理

---

## 版本历史

| 版本 | 日期 | 描述 |
|------|------|------|
| 1.0.0 | 2026-02-21 | 首次发布，包含 AI 代码生成功能 |
| 0.1.0 | 2026-02-20 | 项目初始化 |
