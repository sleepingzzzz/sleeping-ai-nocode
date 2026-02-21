# Sleeping AI 零代码平台

<p align="center">
  <img src="sleeping-ai-nocode-frontend/src/assets/logo.svg" alt="Logo" width="120" height="120">
</p>

<p align="center">
  <strong>不写一行代码，生成完整应用</strong>
</p>

<p align="center">
  <a href="#功能特性">功能特性</a> •
  <a href="#技术栈">技术栈</a> •
  <a href="#快速开始">快速开始</a> •
  <a href="#配置说明">配置说明</a> •
  <a href="#项目结构">项目结构</a> •
  <a href="#贡献指南">贡献指南</a>
</p>

<p align="center">
  <a href="https://github.com/sleepingzzzz/sleeping-ai-nocode/releases">
    <img src="https://img.shields.io/github/v/release/sleepingzzzz/sleeping-ai-nocode?include_prereleases" alt="Version">
  </a>
  <a href="https://github.com/sleepingzzzz/sleeping-ai-nocode/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/sleepingzzzz/sleeping-ai-nocode" alt="License">
  </a>
</p>

---

## 功能特性

### 📱 应用管理
- **应用创建** - 基于 Prompt 创建应用
- **应用列表** - 查看我的应用和精选应用
- **应用编辑** - 更新应用名称和封面
- **权限控制** - 用户和管理员分离管理

### 🤖 AI 代码生成
- **智能代码生成** - 基于 AI 的代码生成引擎
  - HTML 单页应用生成
  - 多文件项目生成
  - 自定义 Prompt 模板支持
- **代码解析系统** - 智能解析生成的代码
  - HTML 代码解析器
  - 多文件项目解析器
- **文件保存系统** - 自动保存生成的代码文件
  - 模板方法模式实现
  - 支持自定义保存路径

### 👤 用户管理
- 用户注册与登录
- 用户信息管理
- 角色权限控制（用户/管理员）
- 安全的密码加密存储

### 🔒 安全特性
- 速率限制 - 防止暴力破解攻击
- 输入消毒 - 防止XSS攻击
- 密码复杂度验证
- 安全的会话管理

### 🎨 响应式界面
- 多断点响应式设计（xl, lg, md, sm, xs）
- 移动端抽屉菜单
- 统一的设计系统（Teal 主题色）
- 流畅的过渡动画

### 验证规则
| 字段 | 规则 |
|------|------|
| 账号 | 5-16位，字母开头，支持字母、数字、下划线、减号 |
| 密码 | 8-16位，必须包含字母和数字 |
| 昵称 | 1-24位，支持中文、英文、数字、下划线、减号 |

---

## 技术栈

### 后端
| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.5.4 | 核心框架 |
| MyBatis-Flex | - | ORM框架 |
| Sa-Token | - | 认证授权 |
| Spring Security Crypto | 6.4.5 | 密码加密 |
| MySQL | 8.0+ | 数据库 |

### 前端
| 技术 | 版本 | 说明 |
|------|------|------|
| Vue | 3.x | 前端框架 |
| TypeScript | 5.x | 类型支持 |
| Ant Design Vue | 4.x | UI组件库 |
| Vite | 6.x | 构建工具 |
| Pinia | - | 状态管理 |

---

## 快速开始

### 环境要求
- JDK 17+
- Node.js 18+
- MySQL 8.0+
- Maven 3.8+

### 安装步骤

#### 1. 克隆项目
```bash
git clone https://github.com/sleepingzzzz/sleeping-ai-nocode.git
cd sleeping-ai-nocode
```

#### 2. 创建数据库
```sql
CREATE DATABASE IF NOT EXISTS sleeping_ai_nocode;
```

执行SQL脚本：
```bash
mysql -u root -p sleeping_ai_nocode < sql/create_table.sql
```

#### 3. 配置后端
编辑 `src/main/resources/application.yml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sleeping_ai_nocode
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:123456}
```

#### 4. 启动后端
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

后端服务将在 `http://localhost:8123` 启动

#### 5. 安装前端依赖
```bash
cd sleeping-ai-nocode-frontend
npm install
```

#### 6. 启动前端
```bash
npm run dev
```

前端服务将在 `http://localhost:5173` 启动

### 默认管理员账户
首次启动时，系统会自动创建管理员账户：

| 账号 | 密码 |
|------|------|
| admin | Admin@123456 |

> ⚠️ 请在生产环境中及时修改默认密码

---

## 配置说明

### 后端配置

#### 数据库配置
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/sleeping_ai_nocode
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:123456}
```

#### 会话配置
```yaml
spring:
  session:
    timeout: 86400  # 会话超时时间（秒）
```

### 前端配置

编辑 `sleeping-ai-nocode-frontend/.env.development`：
```env
VITE_API_BASE_URL=http://localhost:8123
```

---

## 项目结构

```
sleeping-ai-nocode/
├── sql/                          # SQL脚本
│   ├── create_table.sql          # 建表脚本
│   └── truncate_user.sql         # 清空用户表
├── src/                          # 后端源码
│   └── main/
│       ├── java/com/sleeping/ainocode/
│       │   ├── ai/               # AI 代码生成
│       │   │   ├── model/        # AI 模型
│       │   │   │   ├── HtmlCodeResult.java
│       │   │   │   └── MultiFileCodeResult.java
│       │   │   ├── AiCodeGeneratorService.java
│       │   │   └── AiCodeGeneratorServiceFactory.java
│       │   ├── core/             # 核心功能
│       │   │   ├── parser/       # 代码解析器
│       │   │   │   ├── CodeParser.java
│       │   │   │   ├── CodeParserExecutor.java
│       │   │   │   ├── HtmlCodeParser.java
│       │   │   │   └── MultiFileCodeParser.java
│       │   │   ├── saver/        # 文件保存器
│       │   │   │   ├── CodeFileSaverExecutor.java
│       │   │   │   ├── CodeFileSaverTemplate.java
│       │   │   │   ├── HtmlCodeFileSaverTemplate.java
│       │   │   │   └── MultiFileCodeFileSaverTemplate.java
│       │   │   └── AiCodeGeneratorFacade.java
│       │   ├── annotation/       # 自定义注解
│       │   ├── aop/              # AOP切面
│       │   ├── common/           # 公共类
│       │   ├── config/           # 配置类
│       │   ├── constant/         # 常量
│       │   ├── controller/       # 控制器
│       │   ├── exception/        # 异常处理
│       │   ├── mapper/           # 数据访问层
│       │   ├── model/            # 模型
│       │   │   ├── dto/          # 数据传输对象
│       │   │   ├── entity/       # 实体类
│       │   │   ├── enums/        # 枚举
│       │   │   └── vo/           # 视图对象
│       │   └── service/          # 服务层
│       └── resources/
│           ├── prompt/           # AI Prompt 模板
│           │   ├── codegen-html-system-prompt.txt
│           │   └── codegen-multi-file-system-prompt.txt
│           └── application.yml   # 配置文件
├── sleeping-ai-nocode-frontend/  # 前端项目
│   ├── src/
│   │   ├── api/                  # API接口
│   │   ├── assets/               # 静态资源
│   │   ├── components/           # 组件
│   │   ├── layouts/              # 布局
│   │   ├── pages/                # 页面
│   │   ├── router/               # 路由
│   │   ├── stores/               # 状态管理
│   │   └── styles/               # 样式
│   │       └── variables.css     # CSS 变量（设计系统）
│   └── package.json
├── CHANGELOG.md                  # 变更日志
└── README.md                     # 项目说明
```

---

## API文档

### 用户接口

| 方法 | 路径 | 说明 | 权限 |
|------|------|------|------|
| POST | /api/user/register | 用户注册 | 公开 |
| POST | /api/user/login | 用户登录 | 公开 |
| POST | /api/user/logout | 用户登出 | 登录用户 |
| GET | /api/user/current | 获取当前用户 | 登录用户 |
| GET | /api/user/list | 获取用户列表 | 管理员 |
| POST | /api/user/add | 添加用户 | 管理员 |
| POST | /api/user/update | 更新用户 | 管理员 |
| POST | /api/user/delete | 删除用户 | 管理员 |

### 应用接口

| 方法 | 路径 | 说明 | 权限 |
|------|------|------|------|
| POST | /app/add | 创建应用 | 登录用户 |
| POST | /app/update | 更新应用 | 应用所有者 |
| POST | /app/delete | 删除应用 | 应用所有者/管理员 |
| GET | /app/get/vo | 获取应用详情 | 公开 |
| POST | /app/my/list/page/vo | 获取我的应用列表 | 登录用户 |
| POST | /app/good/list/page/vo | 获取精选应用列表 | 公开 |
| POST | /app/admin/delete | 管理员删除应用 | 管理员 |
| POST | /app/admin/update | 管理员更新应用 | 管理员 |
| POST | /app/admin/list/page/vo | 管理员获取应用列表 | 管理员 |
| GET | /app/admin/get/vo | 管理员获取应用详情 | 管理员 |

### 健康检查

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | /api/health | 服务健康检查 |

---

## 开发指南

### 后端开发

#### 添加新接口
1. 在 `controller` 包下创建控制器
2. 在 `service` 包下创建服务接口和实现
3. 在 `mapper` 包下创建数据访问接口
4. 如需要，在 `model` 包下添加 DTO/VO/Entity

#### 添加权限控制
```java
@GetMapping("/admin/data")
@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
public BaseResponse<Data> getAdminData() {
    // 仅管理员可访问
}
```

#### 添加速率限制
```java
@PostMapping("/api/action")
@RateLimit(value = 5, windowSeconds = 60, message = "请求过于频繁")
public BaseResponse<Void> doAction() {
    // 60秒内最多5次请求
}
```

#### 使用 AI 代码生成
```java
@Autowired
private AiCodeGeneratorFacade facade;

public void generateCode(String prompt) {
    MultiFileCodeResult result = facade.generateMultiFileCode(prompt);
    // 处理生成结果
}
```

### 前端开发

#### 添加新页面
1. 在 `src/pages/` 下创建 Vue 组件
2. 在 `src/router/index.ts` 添加路由配置

#### 调用后端API
```typescript
import { userController } from '@/api/userController'

const res = await userController.getUserList()
if (res.data.code === 0) {
    console.log(res.data.data)
}
```

#### 使用设计系统变量
```css
.custom-component {
  color: var(--color-primary);
  background: var(--color-bg-secondary);
  border-radius: var(--radius-md);
  padding: var(--spacing-md);
}
```

---

## 设计系统

### 颜色
| 变量 | 值 | 用途 |
|------|-----|------|
| `--color-primary` | #0d9488 | 主色调 |
| `--color-primary-light` | #14b8a6 | 主色调浅色 |
| `--color-primary-dark` | #0f766e | 主色调深色 |
| `--color-accent` | #8b5cf6 | 强调色 |
| `--color-success` | #22c55e | 成功 |
| `--color-warning` | #eab308 | 警告 |
| `--color-error` | #ef4444 | 错误 |

### 响应式断点
| 断点 | 宽度 | 说明 |
|------|------|------|
| xl | ≥1200px | 大屏幕 |
| lg | 992-1199px | 中等屏幕 |
| md | 768-991px | 平板 |
| sm | 576-767px | 大手机 |
| xs | <576px | 小手机 |

---

## 贡献指南

### 提交规范
- `feat`: 新功能
- `fix`: 修复Bug
- `docs`: 文档更新
- `style`: 代码格式调整
- `refactor`: 代码重构
- `test`: 测试相关
- `chore`: 构建/工具相关

### 分支管理
- `main`: 主分支，稳定版本
- `develop`: 开发分支
- `feature/*`: 功能分支
- `hotfix/*`: 紧急修复分支

### 开发流程
1. Fork 本仓库
2. 创建功能分支 (`git checkout -b feature/amazing-feature`)
3. 提交更改 (`git commit -m 'feat: add amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 创建 Pull Request

---

## 更新日志

查看 [CHANGELOG.md](CHANGELOG.md) 了解详细的版本更新记录。

---

## 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

---

## 联系方式

- 项目地址: [https://github.com/sleepingzzzz/sleeping-ai-nocode](https://github.com/sleepingzzzz/sleeping-ai-nocode)
- 问题反馈: [Issues](https://github.com/sleepingzzzz/sleeping-ai-nocode/issues)

---

<p align="center">
  Made with ❤️ by Sleeping AI Team
</p>
