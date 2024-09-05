# LoginReminders Plugin

## Overview

The **LoginReminders** plugin is designed for Minecraft servers that use Discord to communicate server activities. It sends automatic notifications to specified Discord channels when players join or leave the server and allows server administrators to check online players directly from Discord.

## Features

- **Discord Notifications:** Sends messages to specified Discord channels when the server goes online/offline and when players join or quit.
- **Player List Command:** Allows players to use the `%player` command in Discord to list currently online players on the Minecraft server.
- **Configurable via `config.yml`:** Easy setup using a configuration file to define the Discord bot token and channels to notify.

## Installation

1. Download the latest release of the **LoginReminders** plugin from the [GitHub repository](https://github.com/HongMJ1315/LoginReminders).
2. Place the `LoginReminders.jar` file in the `plugins` directory of your Minecraft server.
3. Restart the server to enable the plugin.

## Configuration

After installing the plugin, a `config.yml` file will be created in the `plugins/LoginReminders` directory. You need to provide your Discord bot token and specify the Discord channel IDs where you want the messages to be sent.

### Example `config.yml`

```yaml
token: "YOUR_DISCORD_BOT_TOKEN"
channels:
  - "DISCORD_CHANNEL_ID_1"
  - "DISCORD_CHANNEL_ID_2"
