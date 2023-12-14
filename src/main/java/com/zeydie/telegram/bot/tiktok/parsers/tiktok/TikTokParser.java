package com.zeydie.telegram.bot.tiktok.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.api.JSoupAPI;
import com.zeydie.telegram.bot.tiktok.api.parsers.tiktok.ITikTokParser;
import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

    @Override
    public @NotNull Document getDocumentURL(@NonNull final String url) {
        return JSoupAPI.getDocumentURL(url);
    }

    @Override
    public @NotNull String getFollowers(@NonNull final Document document) {
        return JSoupAPI.getElementsByAttributeValue(document, "data-e2e", "followers-count").text();
    }

    @Override
    public @NotNull String getFollowing(@NonNull final Document document) {
        return JSoupAPI.getElementsByAttributeValue(document, "data-e2e", "following-count").text();
    }

    @Override
    public @NotNull String getLikes(@NonNull final Document document) {
        return JSoupAPI.getElementsByAttributeValue(document, "data-e2e", "likes-count").text();
    }

    @Override
    public @NotNull String getUserBio(@NonNull final Document document) {
        return JSoupAPI.getElementsByAttributeValue(document, "data-e2e", "user-bio").text();
    }

    @Override
    public @NotNull String getUserTitle(@NonNull final Document document) {
        return JSoupAPI.getElementsByAttributeValue(document, "data-e2e", "user-title").text();
    }

    @Override
    public @NotNull String getUserLink(@NonNull final Document document) {
        return JSoupAPI.getElementsByAttributeValue(document, "data-e2e", "user-link").text();
    }

    @Override
    public @Nullable Element getTikTokSigi(@NonNull final Document document) {
        @NonNull val elements = document.getElementsByTag("script");

        elements.removeIf(element -> !element.id().equalsIgnoreCase("__UNIVERSAL_DATA_FOR_REHYDRATION__"));

        return elements.first();
    }

    @Override
    public @Nullable String getTikTokSigiJson(@NonNull final Document document) {
        return this.getTikTokSigi(document).data();
    }

    @Override
    public boolean isExistSigiJson(@NonNull final Document document) {
        return this.getTikTokSigi(document) != null;
    }
}