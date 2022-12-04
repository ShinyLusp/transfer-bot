package com.shinylusp.transferbot.config;

import com.shinylusp.transferbot.listener.MessageListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class JDAListener extends ListenerAdapter {

    private final List<MessageListener> listeners;
    private final DiscordSettings discordSettings;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!isValidMessage(event)) {
            return;
        }

        try {
            listeners.forEach(listener -> listener.processEvent(event));
        } catch (Throwable e) {
            log.error("Unknown error: " + e.getLocalizedMessage(), e);
        }
    }

    private boolean isValidMessage(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return false;
        }
        if (!event.isFromType(ChannelType.TEXT)) {
            return false;
        }
        boolean validGuild = discordSettings.getGuilds().contains(event.getGuild().getId());
        boolean validChannel = discordSettings.getChannelIds().contains(event.getChannel().getId());
        return validGuild && validChannel;
    }
}
