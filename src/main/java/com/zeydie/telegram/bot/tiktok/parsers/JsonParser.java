package com.zeydie.telegram.bot.tiktok.parsers;

import com.google.gson.JsonObject;
import com.zeydie.sgson.SGsonBase;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokItemListData;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokVideoData;
import com.zeydie.telegram.bot.tiktok.api.parsers.IJsonParser;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokItemListDataV2;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokUserDataV2;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokVideoDataV2;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Log4j2
public final class JsonParser implements IJsonParser {
    @Override
    public @Nullable TikTokUserData parseUserData(@NotNull final String json) {
        @Nullable val userDataJson = new GsonParser(json).getUserDataJson();

        if (userDataJson != null)
            return new SGsonBase().fromJsonToObject(userDataJson, new TikTokUserDataV2());

        return null;
    }

    @Override
    public @Nullable TikTokItemListData parseItemListData(@NotNull final String json) {
        @Nullable val itemListDataJson = new GsonParser(json).getItemListDataJson();

        if (itemListDataJson != null)
            try {
                return new SGsonBase().fromJsonToObject(itemListDataJson, new TikTokItemListDataV2());
            } catch (Exception exception) {
                exception.printStackTrace();

                log.debug(itemListDataJson);
            }

        return null;
    }

    @Override
    public @Nullable TikTokVideoData parseVideoData(@NotNull String json) {
        @Nullable val videosDataJson = new GsonParser(json).getVideoDataJson();

        if (videosDataJson != null)
            return new SGsonBase().fromJsonToObject(videosDataJson, new TikTokVideoDataV2());

        return null;
    }

    @RequiredArgsConstructor
    private static class GsonParser {
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

        public @Nullable String getItemListDataJson() {
            return this.json;
        }

        public @Nullable String getVideoDataJson() {
            @NonNull val sharingVideoModule = this.jsonParser.parse(this.json).getAsJsonObject().get("SharingVideoModule").getAsJsonObject();

            if (sharingVideoModule.has("videoData")) {
                @NonNull val videoData = sharingVideoModule.get("videoData").getAsJsonObject();

                if (videoData.has("itemInfo"))
                    return videoData.get("itemInfo").getAsJsonObject().get("itemStruct").toString();
            }

            return null;
        }
    }
}