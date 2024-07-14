package dev.mrjb.loginreminders.loginreminders;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import reactor.core.publisher.Mono;

public final class LoginReminders extends JavaPlugin {

    public String token = "";
    public List<String> channels = null;
    private File configFile;
    private FileConfiguration config;

    public GatewayDiscordClient client;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("LoginReminders is enabled!");
        createConfigFile();
        loadConfig();
        if (token.isEmpty() || channels == null || channels.isEmpty()) {
            getLogger().severe("Token or channels not specified in config.yml. Disabling plugin...");
            getPluginLoader().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this); // 註冊事件監聽器
        client = DiscordClient.create(token).login().block();
        for(String ch : channels){
            Snowflake channelId = Snowflake.of(ch);
            Mono<MessageChannel> channelMono = client.getChannelById(channelId).cast(MessageChannel.class);
            channelMono.flatMap(channel -> channel.createMessage("Server is online!")).subscribe();
        }
        client.on(MessageCreateEvent.class).subscribe(event -> {
            String content = event.getMessage().getContent();
            if (content.startsWith("%player")) {
                String playerList = Bukkit.getOnlinePlayers().stream()
                        .map(player -> player.getName())
                        .collect(Collectors.joining(", "));
                event.getMessage().getChannel().block().createMessage("Online players: " + playerList).block();
            }
        });
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("LoginReminders is disabled!");
        saveConfig();
        try {
            for(String ch : channels){
                Snowflake channelId = Snowflake.of(ch);
                Mono<MessageChannel> channelMono = client.getChannelById(channelId).cast(MessageChannel.class);
                channelMono.flatMap(channel -> channel.createMessage("Server is offline!")).subscribe();
            }
            client.logout().block();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createConfigFile() {
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public void loadConfig() {
        try{
            token = config.getString("token");
            channels = config.getStringList("channels");
            getLogger().info("Token: " + token);
            for(String ch:channels){
                getLogger().info("Channel: " + ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            config.set("token", token);
            config.set("channels", channels);
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
