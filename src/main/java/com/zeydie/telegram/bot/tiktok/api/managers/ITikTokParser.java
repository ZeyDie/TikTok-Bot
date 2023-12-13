package com.zeydie.telegram.bot.tiktok.api.managers;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;

public interface ITikTokParser {
    @NotNull String getUserURL(@NonNull final String nickname);

    @NotNull String getPostURL(
            @NonNull final String nickname,
            @NonNull final String postId
    );

    @NotNull Document getDocumentURL(@NonNull final String url);

    @NotNull String getFollowers(@NonNull final Document document);

    @NotNull String getFollowing(@NonNull final Document document);

    @NotNull String getLikes(@NonNull final Document document);

    @NotNull String getUserBio(@NonNull final Document document);

    @NotNull String getUserTitle(@NonNull final Document document);

    @NotNull String getUserLink(@NonNull final Document document);


    @Nullable String getTikTokSigiJson(@NonNull final Document document);
}