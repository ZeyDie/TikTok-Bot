package com.zeydie.telegram.bot.tiktok.api.parsers.web;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public final class PCWebParser extends AbstractWebParser {
    public PCWebParser(@NonNull final String url) {
        super(url);
    }

    @Override
    public @NotNull String getUserAgent() {
        return "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
    }
}