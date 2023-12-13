package com.zeydie.telegram.bot.tiktok;

import com.zeydie.telegram.bot.tiktok.managers.JsonParser;
import com.zeydie.telegram.bot.tiktok.managers.TikTokParser;
import com.zeydie.telegrambot.TelegramBotCore;
import com.zeydie.telegrambot.api.modules.interfaces.ISubcore;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Log4j2
public final class BotApplication implements ISubcore {
    @Getter
    private static final @NotNull BotApplication instance = new BotApplication();
    @Getter
    private static final @NotNull TelegramBotCore telegramBotCore = TelegramBotCore.getInstance();

    @Getter
    private final @NotNull TikTokParser tikTokParser = new TikTokParser();
    @Getter
    private final @NotNull JsonParser jsonParser = new JsonParser();

    @SneakyThrows
    public static void main(@Nullable final String[] args) {
        log.info("Starting {}...", BotApplication.class.getName());

        log.info("Register subcore {}...", instance.getName());
        telegramBotCore.registerSubcore(instance);

        log.info("Launch {}...", telegramBotCore.getName());
        telegramBotCore.launch(args);
    }

    @Override
    public @NotNull String getName() {
        return this.getClass().getName();
    }

    @Override
    public void launch(@Nullable final String[] args) {
    }

    @Override
    public void preInit() {
    }

    @Override
    public void init() {
    }

    @Override
    public void postInit() {
    }

    @Override
    public void stop() {
    }
}