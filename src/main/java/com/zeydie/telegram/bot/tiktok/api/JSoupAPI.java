package com.zeydie.telegram.bot.tiktok.api;

import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;

import java.io.IOException;
import java.net.Proxy;

public final class JSoupAPI {
    public static String USER_AGENT = "Mozilla\u002F5.0 (Windows NT 10.0; Win64; x64) AppleWebKit\u002F537.36 (KHTML, like Gecko) Chrome\u002F120.0.0.0 Safari\u002F537.36 Edg\u002F120.0.0.0";

    @SneakyThrows
    public static Document getDocumentURL(@NonNull final String url) {
        @NonNull val proxyConfig = ConfigStore.getProxyConfig();

        return getDocumentURLProxy(
                url,
                proxyConfig.isEnable() ? proxyConfig.getProxyAviable() : Proxy.NO_PROXY,
                60000
        );
    }

    public static Document getDocumentURLProxy(
            @NonNull final String url,
            @NonNull final Proxy proxy,
            final int timeout
    ) throws IOException {
        return Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .proxy(proxy)
                .timeout(timeout)
                .maxBodySize(0)
                .get();
    }

    public static @NonNull Elements getElementsByAttributeValue(
            @NonNull final Document document,
            @NonNull final String attribute,
            @NonNull final String value
    ) {
        return document.getElementsByAttributeValue(attribute, value)
                .filter((node, depth) -> node != null ? NodeFilter.FilterResult.CONTINUE : NodeFilter.FilterResult.REMOVE);
    }
}