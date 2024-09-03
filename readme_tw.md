# LoginReminders 插件

## 概述

**LoginReminders** 插件專為使用 Discord 與伺服器活動進行通信的 Minecraft 伺服器設計。當玩家加入或離開伺服器時，它會自動向指定的 Discord 頻道發送通知，並允許伺服器管理員直接從 Discord 查詢在線玩家。

## 功能

- **Discord 通知：** 當伺服器上線/下線以及玩家加入或退出時，向指定的 Discord 頻道發送消息。
- **玩家列表指令：** 允許玩家在 Discord 中使用 `%player` 指令來列出目前在線的 Minecraft 伺服器玩家。
- **通過 `config.yml` 進行配置：** 使用配置文件輕鬆設置 Discord 機器人令牌和要通知的頻道。

## 安裝

1. 從 [GitHub 儲存庫](https://github.com/HongMJ1315/LoginReminders) 下載最新版本的 **LoginReminders** 插件。
2. 將 `LoginReminders.jar` 文件放置到 Minecraft 伺服器的 `plugins` 目錄中。
3. 重啟伺服器以啟用插件。

## 配置

安裝插件後，將會在 `plugins/LoginReminders` 目錄下生成一個 `config.yml` 文件。您需要提供您的 Discord 機器人令牌並指定要發送消息的 Discord 頻道 ID。

### `config.yml` 範例

```yaml
token: "YOUR_DISCORD_BOT_TOKEN"
channels:
  - "DISCORD_CHANNEL_ID_1"
  - "DISCORD_CHANNEL_ID_2"
