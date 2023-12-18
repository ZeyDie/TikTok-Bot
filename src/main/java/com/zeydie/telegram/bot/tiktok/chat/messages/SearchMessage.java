package com.zeydie.telegram.bot.tiktok.chat.messages;

import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import com.zeydie.telegrambot.TelegramBotCore;
import com.zeydie.telegrambot.api.telegram.events.MessageEventSubscribe;
import com.zeydie.telegrambot.api.telegram.events.subscribes.EventSubscribesRegister;
import com.zeydie.telegrambot.telegram.events.MessageEvent;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@EventSubscribesRegister
public final class SearchMessage {
    @MessageEventSubscribe(messages = "@", startWith = true)
    public void search(@NonNull final MessageEvent event) {
        @NonNull val message = event.getMessage();
        @NonNull val userId = event.getSender().id();

        @NonNull val datas = message.text().split(" ");
        @NonNull val nickname = datas[0];

        this.send(userId, TikTokAPI.getUserUrl(nickname));
    }

    @MessageEventSubscribe(messages = "https://tiktok.com/@", startWith = true)
    public void searchUrl(@NonNull final MessageEvent event) {
        @NonNull val message = event.getMessage();
        @NonNull val userId = event.getSender().id();

        this.send(userId, message.text());
    }

    @MessageEventSubscribe(messages = "https://www.tiktok.com/@", startWith = true)
    public void searchUrlWWW(@NonNull final MessageEvent event) {
        this.searchUrl(event);
    }

    @SneakyThrows
    private void send(
            final long userId,
            final String url
    ) {
        BotApplication.getTelegramBotCore().sendMessage(userId, "messages.searching");

        val start = System.currentTimeMillis();

        this.execute(start, userId, url);
    }

    @SneakyThrows
    private void execute(
            final long start,
            final long userId,
            final String url
    ) {
        try {
            @NonNull val nickname = url.split("@")[1];
            @NonNull val formatter = new SimpleDateFormat("HH:mm dd.MM.yyyy");
            @NonNull val formatterExecute = new SimpleDateFormat("mm:ss");

            @NonNull val localize = TelegramBotCore.getInstance().getLanguage();
            @NonNull val document = TikTokAPI.getDocumentUrl(url);
            @NonNull val tikTokUserData = TikTokAPI.getTikTokUserData(document);

            /*@NonNull val userModule = tikTokUserData.getUserModule();
            @NonNull val userData = userModule.getUsers().get(nickname);
            @NonNull val statsData = userModule.getStats().get(nickname);*/

            @NonNull val builder = new StringBuilder();

            /*builder.append(localize.localize("messages.user.stats.title")).append(TikTokAPI.getUserTitle(document)).append("\n")
                    .append(localize.localize("messages.user.stats.id")).append(userData.getId()).append("\n")
                    .append(localize.localize("messages.user.stats.desc")).append(TikTokAPI.getUserBio(document)).append("\n")
                    .append(localize.localize("messages.user.stats.subscribers")).append(TikTokAPI.getFollowers(document)).append("\n")
                    .append(localize.localize("messages.user.stats.following")).append(TikTokAPI.getFollowing(document)).append("\n")
                    .append(localize.localize("messages.user.stats.friends")).append(statsData.getFriendCount()).append("\n")
                    .append(localize.localize("messages.user.stats.likes")).append(TikTokAPI.getLikes(document)).append("\n")
                    .append(localize.localize("messages.user.stats.comments")).append("\n")
                    .append(localize.localize("messages.user.stats.shares")).append("\n")
                    .append(localize.localize("messages.user.stats.region")).append(userData.getRegion()).append("\n")
                    .append(localize.localize("messages.user.stats.language")).append(userData.getLanguage()).append("\n")
                    .append(localize.localize("messages.user.stats.private")).append(userData.isPrivateAccount() ? localize.localize("messages.yes") : localize.localize("messages.no")).append("\n")
                    .append(localize.localize("messages.user.stats.business")).append(userData.getCommerceUserInfo().isCommerceUser() ? localize.localize("messages.yes") : localize.localize("messages.no")).append("\n");

            if (userData.getCreateTime() != 0)
                builder.append(localize.localize("messages.user.stats.date_registration")).append(formatter.format(new Date(userData.getCreateTime() * 1000))).append("\n");
            if (userData.getNickNameModifyTime() != 0)
                builder.append(localize.localize("messages.user.stats.date_nick_change")).append(formatter.format(new Date(userData.getNickNameModifyTime() * 1000))).append("\n");

            builder.append(localize.localize("messages.user.stats.links")).append(TikTokAPI.getUserLink(document)).append("\n");
            builder.append("\n").append(localize.localize("messages.successful")).append(formatterExecute.format(new Date(System.currentTimeMillis() - start)));*/

            BotApplication.getTelegramBotCore().sendMessage(userId, builder.toString());
        } catch (final NullPointerException exception) {
            BotApplication.getTelegramBotCore().sendMessage(userId, "messages.no_connection");

            this.execute(start, userId, url);
        }
    }
}