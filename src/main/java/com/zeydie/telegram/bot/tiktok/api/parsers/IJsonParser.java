package com.zeydie.telegram.bot.tiktok.api.parsers;

import com.zeydie.telegram.bot.tiktok.api.data.TikTokPostData;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IJsonParser {
    @Nullable TikTokUserData getUserData(@NotNull final String json);

    @NotNull TikTokPostData getPostData(@NotNull final String json);
}