package com.zeydie.telegram.bot.tiktok.api.managers;

import com.zeydie.telegram.bot.tiktok.data.TikTokPostData;
import com.zeydie.telegram.bot.tiktok.data.TikTokUserData;
import org.jetbrains.annotations.NotNull;

public interface IJsonParser {
    @NotNull TikTokUserData getUserData(@NotNull final String json);

    @NotNull TikTokPostData getPostData(@NotNull final String json);
}