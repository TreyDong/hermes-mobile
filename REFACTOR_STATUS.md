# Hermes Mobile — TreyDong 个人 fork

> 这是 `Hy4ri/hermes-mobile` 的个人重构 fork,聚焦"聊天 + 会话同步"。

**Fork URL:** https://github.com/TreyDong/hermes-mobile
**Upstream:** https://github.com/Hy4ri/hermes-mobile
**重构方案:** 见 `REFACTOR_PLAN.md`(同目录)

---

## 已完成的重构(3 个 commits)

| Commit | 内容 |
|---|---|
| `5ec8db7` | 删除 18 个非核心 ui 模块(Kanban/Analytics/Billing/Channels/...)共 47 个文件 |
| `6379aba` | 删除 7 个零引用 DTO |
| `9b2bc9e` | 删除 5 套主题预设,改用 TRAE 紫蓝单主题(品牌色 `#5B5BFF`) |

## 设计主题(来自设计图)

- **主色:** `#5B5BFF`(TRAE 紫蓝渐变起点)
- **次色:** `#7B3FF2`(紫蓝渐变终点)
- **背景:** `#F5F5F5`(浅灰白,非纯白)
- **Surface:** `#FFFFFF`
- **OnSurface:** `#1C1C1E`
- **OnSurfaceVariant:** `#8E8E93`
- **Material You 动态取色:已禁用**(保持品牌一致)

## 保留的 UI 模块(9 屏)

- `chat` — 聊天主屏(53 个文件,核心)
- `sessions` — 会话列表(HistoryScreen)
- `profiles` — profile 切换
- `cron` — cron 管理
- `skills` — 技能列表
- `memory` — memory 查看
- `model` — 模型切换
- `gateway` — 网关健康
- `settings` — 设置

## 未完成的(由接手人继续)

按 `REFACTOR_PLAN.md`:

- [ ] 删 26 个 1+ 引用 DTO(需要修引用文件,工作量 1-2h)
- [ ] 简化 data/remote(PersistentCookieJar 等)
- [ ] 单服务器化(data/config/ServerStore 5 文件 → 2 文件)
- [ ] 简化 cron/skills/memory 等屏(只读 + 启停)
- [ ] 加 Session 5 秒轮询(核心需求)
- [ ] 加 Active 过滤 Tab
- [ ] 加 Setup 引导页(填连接信息)
- [ ] 联调 Hermes 网关验证

## 本地构建

```bash
git clone git@github.com:TreyDong/hermes-mobile.git
cd hermes-mobile
# 需要 JDK 21 + Android Studio Ladybug+
./gradlew assembleDebug
```

---

## 完整对话归档

本次重构的完整方案在仓库根目录的 `REFACTOR_PLAN.md` 和 `HANDOFF.md`(如果有)。
原始对话由 Hermes Agent 助手 + Treydong 协作,2026-08-07 完成。
