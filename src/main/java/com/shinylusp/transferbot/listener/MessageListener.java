package com.shinylusp.transferbot.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface MessageListener {
    void processEvent(MessageReceivedEvent event);
}
