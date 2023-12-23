package com.zeydie.telegram.bot.tiktok.api;

import com.zeydie.telegram.bot.tiktok.api.parsers.web.IWebParser;
import com.zeydie.telegram.bot.tiktok.api.parsers.web.MobileWebParser;
import com.zeydie.telegram.bot.tiktok.api.parsers.web.PCWebParser;
import lombok.NonNull;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

import java.io.IOException;

public final class JsoupAPI {
    public static @Nullable Element postPC(@NonNull final String url) throws IOException {
        return getWebParser(url, false).post().getDocument();
    }

    public static @Nullable Element postMobile(@NonNull final String url) throws IOException {
        return getWebParser(url, true).post().getDocument();
    }

    public static @Nullable Element getPC(@NonNull final String url) throws IOException {
        return getWebParser(url, false).get().getDocument();
    }

    public static @Nullable Element getMobile(@NonNull final String url) throws IOException {
        return getWebParser(url, true).get().getDocument();
    }

    public static @NotNull IWebParser getWebParser(
            @NonNull final String url,
            final boolean mobile
    ) {
        @NonNull val webParser = mobile ? new MobileWebParser(url) : new PCWebParser(url);

        return webParser.connection();
    }
}