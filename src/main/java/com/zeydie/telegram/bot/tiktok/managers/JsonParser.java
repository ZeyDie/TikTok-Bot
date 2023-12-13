package com.zeydie.telegram.bot.tiktok.managers;

import com.zeydie.sgson.SGsonBase;
import com.zeydie.telegram.bot.tiktok.api.managers.IJsonParser;
import com.zeydie.telegram.bot.tiktok.data.TikTokPostData;
import com.zeydie.telegram.bot.tiktok.data.TikTokUserData;
import org.jetbrains.annotations.NotNull;

public final class JsonParser implements IJsonParser {
    @Override
    public @NotNull TikTokUserData getUserData(@NotNull final String json) {
        return new SGsonBase().fromJsonToObject(json, new TikTokUserData());
    }

    @Override
    public @NotNull TikTokPostData getPostData(@NotNull final String json) {
        return new SGsonBase().fromJsonToObject(json, new TikTokPostData());
    }
}