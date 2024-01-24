package com.zeydie.telegram.bot.tiktok.api;

import com.zeydie.telegram.bot.tiktok.api.parsers.web.IWebParser;
import com.zeydie.telegram.bot.tiktok.api.parsers.web.MobileWebParser;
import com.zeydie.telegram.bot.tiktok.api.parsers.web.PCWebParser;
import lombok.NonNull;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public final class JsoupAPI {
    public static @NotNull IWebParser getWebParser(@NonNull final String url) {
        return getWebParser(url, false);
    }

    public static @NotNull IWebParser getWebParser(
            @NonNull final String url,
            final boolean mobile
    ) {
        @NonNull val webParser = mobile ? new MobileWebParser(url) : new PCWebParser(url);

        return webParser.connection();
    }

    public static @NotNull IWebParser getWebParserProxy(@NonNull final String url) {
        return getWebParserProxy(url, false);
    }

    public static @NotNull IWebParser getWebParserProxy(
            @NonNull final String url,
            final boolean mobile
    ) {
        @NonNull val webParser = mobile ? new MobileWebParser(url) : new PCWebParser(url);

        return webParser.enableProxy().connection();
    }
}