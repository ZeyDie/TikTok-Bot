package com.zeydie.telegram.bot.tiktok.api.parsers;

import com.zeydie.telegram.bot.tiktok.data.v1.TikTokPostDataV1;
import com.zeydie.telegram.bot.tiktok.data.v1.TikTokUserDataV1;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokPostDataV2;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokUserDataV2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IJsonParser {
    @Nullable TikTokUserDataV2 getUserData(@NotNull final String json);

    @NotNull TikTokPostDataV2 getPostData(@NotNull final String json);
}