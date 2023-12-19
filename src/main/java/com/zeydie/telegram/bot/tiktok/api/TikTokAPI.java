package com.zeydie.telegram.bot.tiktok.api;

import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import com.zeydie.telegram.bot.tiktok.parsers.JsonParser;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokWebParser;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;

public final class TikTokAPI {
    public static final String tiktokURL = "https://www.tiktok.com/";
    public static final String tiktokAccountURL = "https://www.tiktok.com/@tiktok";

    public static final @NotNull TikTokWebParser tikTokParser = BotApplication.getInstance().getTikTokParser();
    public static final @NotNull JsonParser jsonParser = BotApplication.getInstance().getJsonParser();

    public static @NotNull String getUserUrl(@NonNull final String nickname) {
        return tikTokParser.getUserURL(nickname);
    }

    public static @NotNull Document getDocumentUser(@NonNull final String nickname) {
        return getDocumentUrl(getUserUrl(nickname));
    }

    public static @NotNull Document getDocumentUrl(@NonNull final String url) {
        return tikTokParser.getDocumentURL(url);
    }

    public static @Nullable TikTokUserData getTikTokUserData(@NonNull final String nickname) {
        return getTikTokUserData(getDocumentUser(nickname));
    }

    public static @Nullable TikTokUserData getTikTokUserData(@NonNull final Document document) {
        return jsonParser.getUserData(tikTokParser.getTikTokDataJson(document));
    }
}