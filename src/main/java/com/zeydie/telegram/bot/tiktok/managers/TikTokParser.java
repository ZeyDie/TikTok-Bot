package com.zeydie.telegram.bot.tiktok.managers;

import com.zeydie.telegram.bot.tiktok.api.managers.ITikTokParser;
import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeFilter;

import java.net.Proxy;

public final class TikTokParser implements ITikTokParser {
    @Override
    public @NotNull String getUserURL(@NonNull final String nickname) {
        if (nickname.startsWith("@"))
            return "https://www.tiktok.com/" + nickname;

        return "https://www.tiktok.com/@" + nickname;
    }

    @Override
    public @NotNull String getPostURL(
            @NonNull final String nickname,
            @NonNull final String postId
    ) {
        return this.getUserURL(nickname) + "/video/" + postId;
    }

    @SneakyThrows
    @Override
    public @NotNull Document getDocumentURL(@NonNull final String url) {
        @NonNull val proxyConfig = ConfigStore.getProxyConfig();

        return Jsoup.connect(url)
                .proxy(proxyConfig.isEnable() ? proxyConfig.getProxyAviable() : Proxy.NO_PROXY)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .get();
    }

    @Override
    public @NotNull String getFollowers(@NonNull final Document document) {
        return document.getElementsByAttributeValue("data-e2e", "followers-count")
                .filter((node, depth) -> node != null ? NodeFilter.FilterResult.CONTINUE : NodeFilter.FilterResult.REMOVE)
                .text();
    }

    @Override
    public @NotNull String getFollowing(@NonNull final Document document) {
        return document.getElementsByAttributeValue("data-e2e", "following-count")
                .filter((node, depth) -> node != null ? NodeFilter.FilterResult.CONTINUE : NodeFilter.FilterResult.REMOVE)
                .text();
    }

    @Override
    public @NotNull String getLikes(@NonNull final Document document) {
        return document.getElementsByAttributeValue("data-e2e", "likes-count")
                .filter((node, depth) -> node != null ? NodeFilter.FilterResult.CONTINUE : NodeFilter.FilterResult.REMOVE)
                .text();
    }

    @Override
    public @NotNull String getUserBio(@NonNull final Document document) {
        return document.getElementsByAttributeValue("data-e2e", "user-bio")
                .filter((node, depth) -> node != null ? NodeFilter.FilterResult.CONTINUE : NodeFilter.FilterResult.REMOVE)
                .text();
    }

    @Override
    public @NotNull String getUserTitle(@NonNull final Document document) {
        return document.getElementsByAttributeValue("data-e2e", "user-title")
                .filter((node, depth) -> node != null ? NodeFilter.FilterResult.CONTINUE : NodeFilter.FilterResult.REMOVE)
                .text();
    }

    @Override
    public @NotNull String getUserLink(@NonNull final Document document) {
        return document.getElementsByAttributeValue("data-e2e", "user-link")
                .filter((node, depth) -> node != null ? NodeFilter.FilterResult.CONTINUE : NodeFilter.FilterResult.REMOVE)
                .text();
    }

    @Override
    public @Nullable String getTikTokSigiJson(@NonNull final Document document) {
        @NonNull val elements = document.getElementsByTag("script");

        elements.removeIf(element -> !element.id().equalsIgnoreCase("SIGI_STATE"));

        return elements.first().data();
    }
}