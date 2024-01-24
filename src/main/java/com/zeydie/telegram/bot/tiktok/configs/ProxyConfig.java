package com.zeydie.telegram.bot.tiktok.configs;

import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import com.zeydie.telegram.bot.tiktok.api.parsers.web.PCWebParser;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public final class ProxyConfig {
    private boolean debug;
    private boolean enable = true;
    private List<ProxyData> proxies;

    public ProxyConfig() {
        if (this.proxies == null) {
            this.proxies = new ArrayList<>();
            this.proxies.add(
                    new ProxyData(
                            true,
                            Proxy.Type.HTTP,
                            "38.180.114.56",
                            3128,
                            true,
                            "tecmint",
                            "123456789",
                            10000
                    )
            );
        }
    }

    public @NotNull Proxy getProxyAvailable() {
        if (!this.enable)
            return Proxy.NO_PROXY;

        @NonNull val proxyList = this.proxies.stream()
                .filter(ProxyData::isAvailable)
                .toList();

        if (proxyList.isEmpty()) return Proxy.NO_PROXY;

        return proxyList.get(new Random().nextInt(proxyList.size())).getProxy();
    }

    @Log4j2
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProxyData {
        private boolean enable;
        private Proxy.Type type;
        private String ip;
        private int port;
        private boolean auth;
        private String user;
        private String password;
        private int timeout = Duration.of(10, ChronoUnit.SECONDS).toMillisPart();

        public @NotNull Proxy getProxy() {
            switch (this.type) {
                case HTTP -> {
                    System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
                    System.setProperty("http.proxyHost", this.ip);
                    System.setProperty("http.proxyPort", String.valueOf(this.port));
                    System.setProperty("https.proxyHost", this.ip);
                    System.setProperty("https.proxyPort", String.valueOf(this.port));

                    if (this.auth) {
                        log.info("HTTP proxy {}:{} for {}", this.ip, this.port, this.user);

                        Authenticator.setDefault(
                                new Authenticator() {
                                    @Override
                                    public PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(user, password.toCharArray());
                                    }
                                }
                        );
                    } else log.info("HTTP proxy {}:{}", this.ip, this.port);
                }
                case SOCKS -> {
                    if (this.auth) {
                        log.info("SOCKS proxy {}:{} for {}", this.ip, this.port, this.user);
                    } else log.info("SOCKS proxy {}:{}", this.ip, this.port);
                }
            }

            return new Proxy(this.type, new InetSocketAddress(this.ip, this.port));
        }

        public boolean isAvailable() {
            if (!this.enable) return false;

            try {
                @NonNull val url = TikTokAPI.tiktokUrlWWW + TikTokAPI.tiktokAccount;
                @NonNull val webParser = new PCWebParser(url);

                Jsoup.connect(url)
                        .userAgent(webParser.getUserAgent())
                        .proxy(this.getProxy())
                        .ignoreHttpErrors(true)
                        .ignoreContentType(true)
                        .get();

                return true;
            } catch (IOException exception) {
                return false;
            }
        }
    }
}