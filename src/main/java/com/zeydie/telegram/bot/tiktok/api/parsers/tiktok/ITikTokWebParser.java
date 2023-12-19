package com.zeydie.telegram.bot.tiktok.api.parsers.tiktok;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface ITikTokWebParser {
    @NotNull String getUserURL(@NonNull final String nickname);

    @NotNull String getPostURL(
            @NonNull final String nickname,
            @NonNull final String postId
    );

    @Nullable Element getTikTokData(@NonNull final Document document);

    @Nullable String getTikTokDataJson(@NonNull final Document document);

    boolean isExistDataJson(@NonNull final Document documentURLProxy);

    @NotNull Document getDocumentURL(@NonNull final String url);
}