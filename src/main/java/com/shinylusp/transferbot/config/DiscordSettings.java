package com.shinylusp.transferbot.config;

import lombok.Data;

import java.util.List;

@Data
public class DiscordSettings {
    private List<String> guilds;
    private List<String> channelIds;
}
