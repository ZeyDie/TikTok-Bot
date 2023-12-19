package com.zeydie.telegram.bot.tiktok.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.api.JsoupAPI;
import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import com.zeydie.telegram.bot.tiktok.api.parsers.tiktok.ITikTokWebParser;
import lombok.NonNull;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public final class TikTokWebParser implements ITikTokWebParser {
    @Override
    public @NotNull String getUserURL(@NonNull final String nickname) {
        if (nickname.startsWith(TikTokAPI.tiktokURL))
            return nickname;

        if (nickname.startsWith("@"))
            return TikTokAPI.tiktokURL + nickname;

        return TikTokAPI.tiktokURL + "@" + nickname;
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
        return JsoupAPI.getDocumentURL(url);
    }

    @Override
    public @Nullable Element getTikTokData(@NonNull final Document document) {
        @NonNull val elements = document.getElementsByTag("script");

        elements.removeIf(element -> !element.id().equalsIgnoreCase("__UNIVERSAL_DATA_FOR_REHYDRATION__"));

        return elements.first();
    }

    @Override
    public @Nullable String getTikTokDataJson(@NonNull final Document document) {
        return this.getTikTokData(document).data();
    }

    @Override
    public boolean isExistDataJson(@NonNull final Document document) {
        return this.getTikTokData(document) != null;
    }
}