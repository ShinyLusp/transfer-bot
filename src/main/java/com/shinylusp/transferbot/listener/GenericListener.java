package com.shinylusp.transferbot.listener;

import com.shinylusp.transferbot.service.ChannelMessageService;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GenericListener implements MessageListener {

    private final ChannelMessageService messageService;

    @Override
    public void processEvent(MessageReceivedEvent event) {
        MessageType type = event.getMessage().getType();
        String messageText = event.getMessage().getContentDisplay();
        User author = event.getAuthor();
        MessageChannelUnion channel = event.getChannel();

        messageService.sendMessageToChanel("Получил сообщение: " + messageText + ". От автора: " + author.getName(), channel);
    }
}
