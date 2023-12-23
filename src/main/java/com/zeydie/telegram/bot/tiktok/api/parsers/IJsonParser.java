package com.zeydie.telegram.bot.tiktok.api.parsers;

import com.zeydie.telegram.bot.tiktok.api.data.TikTokItemListData;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokVideoData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IJsonParser {
    @Nullable TikTokUserData parseUserData(@NotNull final String json);

    @Nullable TikTokItemListData parseItemListData(@NotNull final String json);

    @Nullable TikTokVideoData parseVideoData(@NotNull final String json);
}