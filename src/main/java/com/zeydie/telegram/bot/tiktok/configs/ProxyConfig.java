package com.zeydie.telegram.bot.tiktok.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

@Data
public final class ProxyConfig {
    private boolean enable = true;
    private List<ProxyData> proxies = new ArrayList<>();

    public ProxyConfig() {
        if (this.proxies.isEmpty())
            this.proxies.add(
                    new ProxyData(
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
        private boolean auth;
        private String user;
        private String password;
        private int timeout = 100000;

        public @NotNull Proxy getProxy() {
            if (this.type == Proxy.Type.HTTP) {
                log.info("Uses proxy {}:{} for {}", this.ip, this.port, this.user);

                System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
                System.setProperty("http.proxyHost", this.ip);
                System.setProperty("http.proxyPort", String.valueOf(this.port));
                System.setProperty("https.proxyHost", this.ip);
                System.setProperty("https.proxyPort", String.valueOf(this.port));
            }

            if (this.auth) {
                if (this.type == Proxy.Type.HTTP)
                    Authenticator.setDefault(
                            new Authenticator() {
                                @Override
                                public PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(user, password.toCharArray());
                                }
                            }
                    );
            }

            return new Proxy(this.type, new InetSocketAddress(this.ip, this.port));
        }

        @SneakyThrows
        public boolean isAviable() {
            return true;
        }
    }
}