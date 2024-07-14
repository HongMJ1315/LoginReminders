package dev.mrjb.loginreminders.loginreminders;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.channel.MessageChannel;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import reactor.core.publisher.Mono;

public class PlayerEventListener implements Listener {
    private LoginReminders plugin;

    public PlayerEventListener(LoginReminders plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().spigot().sendMessage(new TextComponent("Welcome to the server!"));
        // sent message to discord channel
        for(String ch : plugin.channels){
            String playerName = event.getPlayer().getPlayerListName();
            Snowflake channelId = Snowflake.of(ch);
            Mono<MessageChannel> channelMono = plugin.client.getChannelById(channelId).cast(MessageChannel.class);
            channelMono.flatMap(channel -> channel.createMessage(playerName + " has joined the server!")).subscribe();
        }
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        // sent message to discord channel
        for(String ch : plugin.channels){
            String playerName = event.getPlayer().getPlayerListName();
            Snowflake channelId = Snowflake.of(ch);
            Mono<MessageChannel> channelMono = plugin.client.getChannelById(channelId).cast(MessageChannel.class);
            channelMono.flatMap(channel -> channel.createMessage(playerName + " has quited the server!")).subscribe();
        }
    }
}
