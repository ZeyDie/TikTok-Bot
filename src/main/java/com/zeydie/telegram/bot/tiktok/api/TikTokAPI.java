package com.zeydie.telegram.bot.tiktok.api;

import org.jetbrains.annotations.NotNull;

public final class TikTokAPI {
    public static final @NotNull String tiktokUrl = "https://www.tiktok.com/";
    public static final @NotNull String tiktokAccount = "@tiktok";

    public static @NotNull String getTiktokAccountUrl(@NotNull final String nickname) {
        if (nickname.startsWith(tiktokUrl + "@"))
            return nickname;
        else if (nickname.startsWith("@"))
            return tiktokUrl + nickname;

        return tiktokUrl + "@" + nickname;
    }

    public static @NotNull String getTiktokAccountVideoUrl(
            @NotNull final String nickname,
            @NotNull final String videoId
    ) {
        return getTiktokAccountUrl(nickname) + "/video/" + videoId;
    }
}