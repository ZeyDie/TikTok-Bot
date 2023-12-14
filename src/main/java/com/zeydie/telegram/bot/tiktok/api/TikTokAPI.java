package com.zeydie.telegram.bot.tiktok.api;

import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokUserDataV2;
import com.zeydie.telegram.bot.tiktok.parsers.JsonParser;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokParser;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;

public final class TikTokAPI {
    private static final @NotNull TikTokParser tikTokParser = BotApplication.getInstance().getTikTokParser();
    private static final @NotNull JsonParser jsonParser = BotApplication.getInstance().getJsonParser();

    public static @NotNull String getUserUrl(@NonNull final String nickname) {
        return tikTokParser.getUserURL(nickname);
    }

    public static @NotNull Document getDocumentUser(@NonNull final String nickname) {
        return getDocumentUrl(getUserUrl(nickname));
    }

    public static @NotNull Document getDocumentUrl(@NonNull final String url) {
        return tikTokParser.getDocumentURL(url);
    }

    public static @Nullable TikTokUserDataV2 getTikTokUserData(@NonNull final String nickname) {
        return getTikTokUserData(getDocumentUser(nickname));
    }

    public static @Nullable TikTokUserDataV2 getTikTokUserData(@NonNull final Document document) {
        return jsonParser.getUserData(tikTokParser.getTikTokSigiJson(document));
    }

    /*public static @NotNull TikTokPostData getTikTokPostData(
            @NonNull final String nickname,
            @NonNull final String postId
    ) {
        return jsonParser.getPostData(tikTokParser.getTikTokSigiJson(getDocumentUrl(postId)));
    }*/

    public static @NotNull String getFollowers(@NonNull final String nickname) {
        return getFollowers(getDocumentUser(nickname));
    }

    public static @NotNull String getFollowers(@NonNull final Document document) {
        return tikTokParser.getFollowers(document);
    }

    public static @NotNull String getFollowing(@NonNull final String nickname) {
        return getFollowing(getDocumentUser(nickname));
    }

    public static @NotNull String getFollowing(@NonNull final Document document) {
        return tikTokParser.getFollowing(document);
    }

    public static @NotNull String getLikes(@NonNull final String nickname) {
        return getLikes(getDocumentUser(nickname));
    }

    public static @NotNull String getLikes(@NonNull final Document document) {
        return tikTokParser.getLikes(document);
    }

    public static @NotNull String getUserBio(@NonNull final String nickname) {
        return getUserBio(getDocumentUser(nickname));
    }

    public static @NotNull String getUserBio(@NonNull final Document document) {
        return tikTokParser.getUserBio(document);
    }

    public static @NotNull String getUserTitle(@NonNull final String nickname) {
        return getUserTitle(getDocumentUser(nickname));
    }

    public static @NotNull String getUserTitle(@NonNull final Document document) {
        return tikTokParser.getUserTitle(document);
    }

    public static @NotNull String getUserLink(@NonNull final String nickname) {
        return getUserLink(getDocumentUser(nickname));
    }

    public static @NotNull String getUserLink(@NonNull final Document document) {
        return tikTokParser.getUserLink(document);
    }
}