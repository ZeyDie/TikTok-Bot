package com.zeydie.telegram.bot.tiktok.api;

import org.jetbrains.annotations.NotNull;

public final class TikTokAPI {
    public static final @NotNull String tiktokUrl = "https://www.tiktok.com/";
    public static final @NotNull String tiktokUrlWWW = "https://www.tiktok.com/";
    public static final @NotNull String tiktokAccount = "@tiktok";

    public static @NotNull String getTiktokAccountUrl(@NotNull final String nickname) {
        if (nickname.startsWith(tiktokUrlWWW + "@"))
            return nickname;
        else if (nickname.startsWith("@"))
            return tiktokUrlWWW + nickname;

        return tiktokUrlWWW + "@" + nickname;
    }

    public static @NotNull String getTiktokAccountVideoUrl(
            @NotNull final String nickname,
            @NotNull final String videoId
    ) {
        return getTiktokAccountUrl(nickname) + "/video/" + videoId;
    }
}