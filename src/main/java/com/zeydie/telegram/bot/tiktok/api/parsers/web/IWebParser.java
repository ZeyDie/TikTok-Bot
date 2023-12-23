package com.zeydie.telegram.bot.tiktok.api.parsers.web;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public interface IWebParser {
    @NotNull String getUserAgent();

    @NotNull IWebParser connection();

    @Nullable Connection getConnection();

    @NotNull IWebParser get() throws IOException;

    @NotNull IWebParser post() throws IOException;

    @Nullable Document getDocument();

    @Nullable Elements getElementsOfTag(@NonNull final String tag);

    @Nullable Element getElementOfId(
            @NonNull final Elements elements,
            @NotNull final String id
    );
}
