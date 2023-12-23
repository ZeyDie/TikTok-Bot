package com.zeydie.telegram.bot.tiktok.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.api.JsoupAPI;
import com.zeydie.telegram.bot.tiktok.api.data.TikTokVideoData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public final class TikTokItemVideoParser {
    private final String url;

    public @Nullable TikTokVideoData getTikTokVideoData() throws IOException {
        @NonNull val webParser = JsoupAPI.getWebParser(this.url, true).get();
        @Nullable val elements = webParser.getElementsOfTag("script");

        if (elements != null) {
            @Nullable val document = webParser.getElementOfId(elements, "SIGI_STATE");

            if (document != null) {
                @NonNull val json = document.data();

                return BotApplication.getInstance().getJsonParser().parseVideoData(json);
            }
        }

        return null;
    }
}
