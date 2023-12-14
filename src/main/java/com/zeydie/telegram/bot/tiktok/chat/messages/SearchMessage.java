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
import org.jetbrains.annotations.Nullable;

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
        @NonNull val nickname = url.split("@")[1];
        @NonNull val formatter = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        @NonNull val formatterExecute = new SimpleDateFormat("mm:ss");

        @NonNull val localize = TelegramBotCore.getInstance().getLanguage();
        @NonNull val tiktokDataParser = BotApplication.getInstance().getTikTokDataParser();

        @NonNull val document = TikTokAPI.getDocumentUrl(url);
        @Nullable val tikTokUserData = TikTokAPI.getTikTokUserData(document);

        if (tikTokUserData == null) {
            BotApplication.getTelegramBotCore().sendMessage(userId, "messages.no_user");

            return;
        }
        @NonNull val builder = new StringBuilder();

        builder.append(localize.localize("messages.user.stats.title")).append(tiktokDataParser.getUserTitle(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.id")).append(tiktokDataParser.getUserId(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.desc")).append(tiktokDataParser.getUserBio(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.subscribers")).append(tiktokDataParser.getFollowers(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.following")).append(tiktokDataParser.getFollowing(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.friends")).append(tiktokDataParser.getFriends(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.likes")).append(tiktokDataParser.getLikes(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.videos")).append(tiktokDataParser.getVideos(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.comments")).append("\n")
                .append(localize.localize("messages.user.stats.shares")).append("\n")
                .append(localize.localize("messages.user.stats.region")).append(tiktokDataParser.getRegion(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.language")).append(tiktokDataParser.getLanguage(tikTokUserData)).append("\n")
                .append(localize.localize("messages.user.stats.private")).append(tiktokDataParser.isPrivate(tikTokUserData) ? localize.localize("messages.yes") : localize.localize("messages.no")).append("\n")
                .append(localize.localize("messages.user.stats.business")).append(tiktokDataParser.isCommerce(tikTokUserData) ? localize.localize("messages.yes") : localize.localize("messages.no")).append("\n");

        val createTime = tiktokDataParser.getCreateTime(tikTokUserData);
        val nicknameModifyTime = tiktokDataParser.getNicknameModifyTime(tikTokUserData);

        if (createTime != 0)
            builder.append(localize.localize("messages.user.stats.date_registration")).append(formatter.format(new Date(createTime * 1000))).append("\n");
        if (nicknameModifyTime != 0)
            builder.append(localize.localize("messages.user.stats.date_nick_change")).append(formatter.format(new Date(nicknameModifyTime * 1000))).append("\n");

        @Nullable val userLink = tiktokDataParser.getUserLink(tikTokUserData);

        if (userLink != null)
            builder.append(localize.localize("messages.user.stats.links")).append(userLink).append("\n");

        builder.append("\n").append(localize.localize("messages.successful")).append(formatterExecute.format(new Date(System.currentTimeMillis() - start)));

        BotApplication.getTelegramBotCore().sendMessage(userId, builder.toString());
    }
}