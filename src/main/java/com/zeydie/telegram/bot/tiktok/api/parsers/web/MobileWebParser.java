package com.zeydie.telegram.bot.tiktok.api.parsers.web;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public final class MobileWebParser extends AbstractWebParser {
    public MobileWebParser(@NonNull final String url) {
        super(url);
    }

    @Override
    public @NotNull String getUserAgent() {
        return "Mozilla/5.0 (iPhone; CPU iPhone OS 16_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.6 Mobile/15E148 Safari/604.1 Edg/120.0.0.0";
    }
}