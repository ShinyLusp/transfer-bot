package com.shinylusp.transferbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@Configuration
public class JDAConfig {
    @Value("#{environment.BOT_TOKEN}")
    private String token;

    @Bean
    public JDA jda(JDAListener listener) throws InterruptedException {
        JDA jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(listener)
                .build();

        jda.awaitReady();
        return jda;
    }

    @Bean
    public DiscordSettings discordSettings() throws IOException {
        File file = ResourceUtils.getFile("classpath:discord-settings.json");
        return new ObjectMapper().readValue(file, DiscordSettings.class);
    }
}
