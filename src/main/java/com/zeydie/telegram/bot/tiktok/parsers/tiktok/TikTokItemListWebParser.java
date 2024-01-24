package com.zeydie.telegram.bot.tiktok.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.api.JsoupAPI;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokItemListData;
import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public final class TikTokItemListWebParser {
    private final String secUid;

    public @Nullable TikTokItemListData getTikTokItemListData() throws IOException {
        @NonNull val requestConfig = ConfigStore.getRequestConfig();
        @NonNull val webParser = JsoupAPI.getWebParser(requestConfig.getItemListUrl()).connection();
        @Nullable val connection = webParser.getConnection();

        if (connection == null) return null;

        connection.data(this.secUid, "");

        @Nullable val document = webParser.post().getDocument();

        if (ConfigStore.getProxyConfig().isDebug())
            log.debug("Document {}", document);

        if (document != null)
            return BotApplication.getInstance().getJsonParser().parseItemListData(document.text());

        return null;
    }
}