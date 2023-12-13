package com.zeydie.telegram.bot.tiktok.configs;

import com.zeydie.telegram.bot.tiktok.BotApplication;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

@Data
public final class ProxyConfig {
    private boolean enable;
    private List<ProxyData> proxies = new ArrayList<>();

    public ProxyConfig() {
        if (this.proxies.isEmpty())
            this.proxies.add(new ProxyData(Proxy.Type.HTTP, "172.16.0.77", 3128, 10000));
    }

    public @Nullable Proxy getProxyAviable() {
        return this.proxies.stream()
                .filter(proxyData -> proxyData.isAviable())
                .findFirst()
                .map(ProxyData::getProxy)
                .orElse(Proxy.NO_PROXY);
    }

    @Log4j2
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProxyData {
        private Proxy.Type type;
        private String ip;
        private int port;
        private int timeout = 100000;

        public @NotNull Proxy getProxy() {
            return new Proxy(this.type, new InetSocketAddress(this.ip, this.port));
        }

        @SneakyThrows
        public boolean isAviable() {
            try {
                @NonNull val document = Jsoup.connect("https://www.tiktok.com/@tiktok")
                        .proxy(this.getProxy())
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .timeout(this.timeout)
                        .get();

                return BotApplication.getInstance().getTikTokParser().getTikTokSigiJson(document) != null;
            } catch (final SocketTimeoutException exception) {
                log.error("No aviable {} {}:{}", this.type, this.ip, this.port);

                return false;
            }
        }
    }
}