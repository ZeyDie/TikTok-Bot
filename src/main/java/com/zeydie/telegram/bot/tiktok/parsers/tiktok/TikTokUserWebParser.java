package com.zeydie.telegram.bot.tiktok.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.api.JsoupAPI;
import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Log4j2
public final class TikTokUserWebParser {
    private final String url;

    public TikTokUserWebParser(@NonNull final String url) {
        this.url = TikTokAPI.getTiktokAccountUrl(url);
    }

    public @Nullable TikTokUserData getTikTokUserData() throws IOException {
        @NonNull val webParser = JsoupAPI.getWebParserProxy(this.url).get();
        @Nullable val elements = webParser.getElementsOfTag("script");

        if (elements != null) {
            @Nullable val document = webParser.getElementOfId(elements, "__UNIVERSAL_DATA_FOR_REHYDRATION__");

            if (ConfigStore.getProxyConfig().isDebug())
                log.debug("Document {}", document);

            if (document != null)
                return BotApplication.getInstance().getJsonParser().parseUserData(document.data());
        }

        return null;
    }
}