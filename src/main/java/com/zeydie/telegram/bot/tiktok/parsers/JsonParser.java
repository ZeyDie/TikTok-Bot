package com.zeydie.telegram.bot.tiktok.parsers;

import com.google.gson.JsonObject;
import com.zeydie.sgson.SGsonBase;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokPostData;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import com.zeydie.telegram.bot.tiktok.api.parsers.IJsonParser;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokPostDataV2;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokUserDataV2;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class JsonParser implements IJsonParser {
    @Override
    public @Nullable TikTokUserData getUserData(@NotNull final String json) {
        @NonNull var userDataJson = new GsonParser(json).getUserDataJson();

        if (userDataJson != null)
            return new SGsonBase().fromJsonToObject(userDataJson, new TikTokUserDataV2());

        return null;
    }

    @Override
    public @NotNull TikTokPostData getPostData(@NotNull final String json) {
        return new SGsonBase().fromJsonToObject(json, new TikTokPostDataV2());
    }

    @RequiredArgsConstructor
    private class GsonParser {
        private final com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
        private final String json;

        public @NotNull JsonObject getDefaultScopeJson() {
            return this.jsonParser.parse(this.json).getAsJsonObject().get("__DEFAULT_SCOPE__").getAsJsonObject();
        }

        public @Nullable String getUserDataJson() {
            @NonNull val userDetail = this.getDefaultScopeJson().get("webapp.user-detail").getAsJsonObject();

            if (userDetail.has("userInfo"))
                return userDetail.get("userInfo").toString();

            return null;
        }
    }
}