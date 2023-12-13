package com.zeydie.telegram.bot.tiktok.chat.commands;


import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegrambot.api.telegram.events.CommandEventSubscribe;
import com.zeydie.telegrambot.api.telegram.events.subscribes.EventSubscribesRegister;
import com.zeydie.telegrambot.modules.keyboard.impl.UserKeyboardImpl;
import com.zeydie.telegrambot.telegram.events.CommandEvent;
import lombok.NonNull;
import lombok.val;

@EventSubscribesRegister
public final class StartCommand {
    @CommandEventSubscribe(commands = "/start")
    public void start(@NonNull final CommandEvent event) {
        @NonNull val instance = BotApplication.getInstance();

        @NonNull val message = event.getMessage();
        val userId = message.from().id();

        @NonNull val userKeyboard = new UserKeyboardImpl();

        userKeyboard.minimizeButtons(true);

        userKeyboard.sendKeyboard(userId, "messages.start");
    }
}