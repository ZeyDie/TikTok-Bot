package com.zeydie.telegram.bot.tiktok.parsers;

import com.google.gson.JsonObject;
import com.zeydie.sgson.SGsonBase;
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
    public @Nullable TikTokUserDataV2 getUserData(@NotNull final String json) {
        @NonNull var userDataJson = new GsonParse(json).getUserDataJson();

        if (userDataJson != null)
            return new SGsonBase().fromJsonToObject(userDataJson, new TikTokUserDataV2());

        return null;
    }

    @Override
    public @NotNull TikTokPostDataV2 getPostData(@NotNull final String json) {
        return new SGsonBase().fromJsonToObject(json, new TikTokPostDataV2());
    }

    @RequiredArgsConstructor
    private class GsonParse {
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