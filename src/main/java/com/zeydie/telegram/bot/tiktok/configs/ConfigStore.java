package com.zeydie.telegram.bot.tiktok.configs;

import com.zeydie.telegrambot.api.events.config.ConfigSubscribe;
import com.zeydie.telegrambot.api.events.subscribes.ConfigSubscribesRegister;
import lombok.Getter;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;

@ConfigSubscribesRegister
public final class ConfigStore {
    @NonFinal
    @Getter
    @ConfigSubscribe(name = "proxy")
    public static @NotNull ProxyConfig proxyConfig = new ProxyConfig();
}