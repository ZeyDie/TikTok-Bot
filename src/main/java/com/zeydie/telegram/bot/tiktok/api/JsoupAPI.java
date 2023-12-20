package com.zeydie.telegram.bot.tiktok.api;

import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;

import java.io.IOException;
import java.net.Proxy;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public final class JsoupAPI {

    @SneakyThrows
    public static @NotNull Document getDocumentURL(@NonNull final String url) {
        @NonNull val proxyConfig = ConfigStore.getProxyConfig();

        return getDocumentURLProxy(
                url,
                proxyConfig.isEnable() ? proxyConfig.getProxyAviable() : Proxy.NO_PROXY,
                Duration.of(1, ChronoUnit.MINUTES).toMillisPart()
        );
    }

    public static @NotNull Document getDocumentURLProxy(
            @NonNull final String url,
            @NonNull final Proxy proxy,
            final int timeout
    ) throws IOException {
        return Jsoup.connect(url)
                .headers(HTTPApi.getHeaders())
                .proxy(proxy)
                .ignoreHttpErrors(true)
                .ignoreContentType(true)
                // .timeout(timeout)
                // .maxBodySize(0)
                .get();
    }

    public static @NotNull Connection getConnection(@NonNull final String url) {
        @NonNull val proxyConfig = ConfigStore.getProxyConfig();

        return getConnection(url, proxyConfig.isEnable() ? proxyConfig.getProxyAviable() : Proxy.NO_PROXY);
    }

    public static @NotNull Connection getConnection(
            @NonNull final String url,
            @NonNull final Proxy proxy
    ) {
        return Jsoup.connect(url)
                .headers(HTTPApi.getHeaders())
                .proxy(proxy);
    }

    public static @NotNull Elements getElementsByAttributeValue(
            @NonNull final Document document,
            @NonNull final String attribute,
            @NonNull final String value
    ) {
        return document.getElementsByAttributeValue(attribute, value)
                .filter((node, depth) -> node != null ? NodeFilter.FilterResult.CONTINUE : NodeFilter.FilterResult.REMOVE);
    }
}