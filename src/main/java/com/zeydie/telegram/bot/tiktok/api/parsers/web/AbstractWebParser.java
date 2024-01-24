package com.zeydie.telegram.bot.tiktok.api.parsers.web;

import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.Proxy;

@Log4j2
@RequiredArgsConstructor
public abstract class AbstractWebParser implements IWebParser {
    private final String url;
    private boolean enableProxy;
    private @Nullable Connection connection;
    private @Nullable Document document;

    @Override
    public @NotNull IWebParser enableProxy() {
        this.enableProxy = true;

        return this;
    }

    @Override
    public @NotNull IWebParser connection() {
        this.connection = Jsoup.connect(this.url)
                .userAgent(this.getUserAgent())
                .proxy(this.enableProxy ? ConfigStore.getProxyConfig().getProxyAvailable() : Proxy.NO_PROXY)
                .ignoreHttpErrors(true)
                .ignoreContentType(true);

        return this;
    }

    @Override
    public @Nullable Connection getConnection() {
        return this.connection;
    }

    @Override
    public @NotNull IWebParser get() throws IOException {
        //TODO exceptions that connection is not executed
        log.debug("GET: {}", this.url);

        this.document = this.connection.get();

        return this;
    }

    @Override
    public @NotNull IWebParser post() throws IOException {
        //TODO exceptions that connection is not executed
        log.debug("POST: {}", this.url);

        this.document = this.connection.post();

        return this;
    }

    @Override
    public @Nullable Document getDocument() {
        return this.document;
    }

    @Override
    public @Nullable Elements getElementsOfTag(@NonNull final String tag) {
        if (this.document == null)
            return null;

        return this.document.getElementsByTag(tag);
    }

    @Override
    public @Nullable Element getElementOfId(
            @NonNull final Elements elements,
            @NotNull final String id
    ) {
        elements.removeIf(element -> !element.id().equalsIgnoreCase(id));

        return elements.first();
    }
}