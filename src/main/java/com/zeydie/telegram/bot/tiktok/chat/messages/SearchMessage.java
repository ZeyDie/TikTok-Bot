package com.zeydie.telegram.bot.tiktok.chat.messages;

import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokVideoData;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokItemListWebParser;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokItemVideoParser;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokUserWebParser;
import com.zeydie.telegrambot.TelegramBotCore;
import com.zeydie.telegrambot.api.telegram.events.MessageEventSubscribe;
import com.zeydie.telegrambot.api.telegram.events.subscribes.EventSubscribesRegister;
import com.zeydie.telegrambot.exceptions.LanguageNotRegisteredException;
import com.zeydie.telegrambot.telegram.events.MessageEvent;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@EventSubscribesRegister
public final class SearchMessage {
    @MessageEventSubscribe(messages = "@", startWith = true)
    public void search(@NonNull final MessageEvent event) {
        @NonNull val message = event.getMessage();
        @NonNull val userId = event.getSender().id();

        @NonNull val datas = message.text().split(" ");
        @NonNull val nickname = datas[0];

        this.send(userId, nickname);
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

        @NonNull val list = new ArrayList<String>();

        Arrays.stream(url.split("\n")).toList()
                .forEach(
                        string -> {
                            string = string
                                    .replaceAll(TikTokAPI.tiktokUrl, "")
                                    .replaceAll("@", "");

                            if (string.contains("?"))
                                string = string.split("\\?")[0];

                            list.add(string);
                        }
                );

        this.execute(userId, list);
    }

    @SneakyThrows
    private void execute(
            final long userId,
            @NonNull final List<String> list
    ) {
        list.forEach(
                nickname -> {
                    @NonNull val thread = new Thread(
                            () -> {
                                try {
                                    val start = System.currentTimeMillis();

                                    @NonNull val formatter = new SimpleDateFormat("HH:mm dd.MM.yyyy");
                                    @NonNull val formatterExecute = new SimpleDateFormat("mm:ss");

                                    @NonNull val localize = TelegramBotCore.getInstance().getLanguage();

                                    @Nullable val tikTokUserData = new TikTokUserWebParser(nickname).getTikTokUserData();

                                    log.debug("[{}] TikTokUserData: {}", nickname, (System.currentTimeMillis() - start));

                                    if (tikTokUserData == null) {
                                        BotApplication.getTelegramBotCore().sendMessage(userId, "messages.no_user");

                                        return;
                                    }

                                    @Nullable val tiktokItemListData = new TikTokItemListWebParser(tikTokUserData.getSecUid()).getTikTokItemListData();

                                    log.debug("[{}] TikTokItemListData: {}", nickname, (System.currentTimeMillis() - start));

                                    @NonNull val builder = new StringBuilder();

                                    builder.append(localize.localize("messages.user.stats.title")).append(tikTokUserData.getNickname()).append("\n")
                                            .append(localize.localize("messages.user.stats.id")).append(tikTokUserData.getId()).append("\n")
                                            .append(localize.localize("messages.desc")).append(tikTokUserData.getUserBio()).append("\n")
                                            .append(localize.localize("messages.user.stats.subscribers")).append(tikTokUserData.getFollowers()).append("\n")
                                            .append(localize.localize("messages.user.stats.following")).append(tikTokUserData.getFollowing()).append("\n")
                                            .append(localize.localize("messages.user.stats.friends")).append(tikTokUserData.getFriends()).append("\n")
                                            .append(localize.localize("messages.likes")).append(tikTokUserData.getLikes()).append("\n")
                                            .append(localize.localize("messages.user.stats.videos")).append(tikTokUserData.getVideos()).append("\n")
                                            .append(localize.localize("messages.comments")).append(tiktokItemListData.getCommentCounts()).append("\n")
                                            .append(localize.localize("messages.shares")).append(tiktokItemListData.getShareCounts()).append("\n")
                                            .append(localize.localize("messages.region")).append(localize.localize(tikTokUserData.getRegion())).append("\n")
                                            .append(localize.localize("messages.user.stats.language")).append(localize.localize(tikTokUserData.getLanguage())).append("\n")
                                            .append(localize.localize("messages.user.stats.private")).append(tikTokUserData.isPrivate() ? localize.localize("messages.yes") : localize.localize("messages.no")).append("\n")
                                            .append(getCommerceInfo(tikTokUserData)).append("\n");

                                    val createTime = tikTokUserData.getCreateTime();
                                    val nicknameModifyTime = tikTokUserData.getNicknameModifyTime();

                                    if (createTime != 0)
                                        builder.append(localize.localize("messages.user.stats.date_registration")).append(formatter.format(new Date(createTime * 1000L))).append("\n");
                                    if (nicknameModifyTime != 0)
                                        builder.append(localize.localize("messages.user.stats.date_nick_change")).append(formatter.format(new Date(nicknameModifyTime * 1000L))).append("\n");

                                    @Nullable val userLink = tikTokUserData.getUserLink();

                                    if (userLink != null)
                                        builder.append(localize.localize("messages.user.stats.links")).append(userLink).append("\n");

                                    builder.append("\n");

                                    @NonNull val videosData = tiktokItemListData.getVideosData();
                                    @NonNull val tikTokItemParsed = new ArrayList<TikTokVideoData>();
                                    @NonNull val index = new AtomicInteger(0);

                                    videosData.forEach(videoData -> tikTokItemParsed.add(null));
                                    videosData.forEach(videoData -> {
                                        val indexThread = index.getAndAdd(1);

                                        @NonNull val thread1 = new Thread(() -> {
                                            try {
                                                tikTokItemParsed.set(indexThread, new TikTokItemVideoParser(TikTokAPI.getTiktokAccountVideoUrl(nickname, videoData.getId())).getTikTokVideoData());
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        });

                                        thread1.start();
                                    });

                                    boolean fullParsed = false;

                                    while (!fullParsed)
                                        fullParsed = tikTokItemParsed.stream().noneMatch(Objects::isNull);

                                    index.set(1);

                                    tikTokItemParsed.forEach(tikTokVideoData -> {
                                        try {
                                            if (tikTokVideoData != null)
                                                builder.append(localize.localize("messages.video.number")).append(index.getAndAdd(1)).append("\n")
                                                        .append(localize.localize("messages.desc")).append(tikTokVideoData.getDesc()).append("\n")
                                                        .append(localize.localize("messages.video.date_published")).append(formatter.format(new Date(tikTokVideoData.getCreateTime() * 1000L))).append("\n")
                                                        .append(localize.localize("messages.region")).append(tikTokVideoData.getLocationCreated()).append("\n")
                                                        .append(localize.localize("messages.video.indexed")).append(!tikTokVideoData.isIndexEnabled() ? localize.localize("messages.yes") : localize.localize("messages.no")).append("\n")
                                                        .append(localize.localize("messages.viewers")).append(tikTokVideoData.getStats().getPlayCount()).append("\n")
                                                        .append(localize.localize("messages.comments")).append(tikTokVideoData.getStats().getCommentCount()).append("\n")
                                                        .append(localize.localize("messages.likes")).append(tikTokVideoData.getStats().getDiggCount()).append("\n")
                                                        .append(localize.localize("messages.shares")).append(tikTokVideoData.getStats().getShareCount()).append("\n")
                                                        .append(localize.localize("messages.video.music")).append(tikTokVideoData.getMusic().getAuthorName()).append("\n\n")
                                                        ;
                                        } catch (LanguageNotRegisteredException exception) {
                                            exception.printStackTrace();
                                        }
                                    });

                                    log.debug("[{}] TikTokVideoData: {}", nickname, (System.currentTimeMillis() - start));

                                    builder.append(localize.localize("messages.successful")).append(formatterExecute.format(new Date(System.currentTimeMillis() - start)));

                                    BotApplication.getTelegramBotCore().sendMessage(
                                            userId,
                                            builder.toString()
                                                    .replaceAll("_", " ")
                                    );
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                    );

                    thread.start();
                }
        );
    }

    @SneakyThrows
    private @NotNull String getCommerceInfo(@NonNull final TikTokUserData tikTokUserData) {
        @NonNull val localize = TelegramBotCore.getInstance().getLanguage();

        @NonNull val builder = new StringBuilder();

        builder.append(localize.localize("messages.user.stats.business"));

        if (tikTokUserData.isCommerce()) {
            builder.append(localize.localize("messages.yes")).append("\n");
            builder.append(localize.localize("messages.user.stats.business.category")).append(tikTokUserData.getCommerceCategory());
        } else builder.append(localize.localize("messages.no"));

        return builder.toString();
    }
}