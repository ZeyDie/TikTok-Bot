package com.zeydie.telegram.bot.tiktok.api;

import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiConsumer;

public final class TikTokRequestAPI {
    /*
    WebIdLastTime: 1702479323
aid: 1988
app_language: ru-RU
app_name: tiktok_web
browser_language: ru
browser_name: Mozilla
browser_online: true
browser_platform: Win32
browser_version: 5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0
channel: tiktok_web
cookie_enabled: true
count: 35
coverFormat: 1
cursor: 0
device_id: 7312071981028427270
device_platform: web_pc
focus_state: true
from_page: user
history_len: 2
is_fullscreen: false
is_page_visible: true
language: ru-RU
os: windows
priority_region:
referer:
region: FR
screen_height: 1080
screen_width: 1920
secUid: MS4wLjABAAAAv7iSuuXDJGDvJkmH_vz1qkDZYo1apxgzaxdBSeIuPiM
tz_name: Asia/Yekaterinburg
verifyFp: verify_lpza5vb4_269byymi_mdK6_4RN6_8JMf_Eq6auckGBgbI
webcast_language: ru-RU
msToken: EYq2XOCNFglZlbZzvr3-8hHytRoIKs18d_8aRKHkDcBUeNNtakbAyHxsv8TgnjYvsb-0MqQU9lWbLUYnckIGRpVeJmbF8Qa7AaK6fb8Q5__RgrbMOyClKID9jjgpYBED2_tmSux3LYKYUdBQHA==
X-Bogus: DFSzswVOKVbANcd9tNl6Nt9WcBjC
_signature: _02B4Z6wo00001FRh2YgAAIDAVGHZi2RVVERUYd0AAHCi63
     */
    private static @NotNull List<String> defaultUserItemListResponseDataList = List.of(
            "WebIdLastTime=1702479323",
            "aid=1988",
            "app_language=ru-RU",
            "app_name=tiktok_web",
            "browser_language=ru",
            "browser_name=Mozilla",
            "browser_online=true",
            "browser_platform=Win32",
            //"browser_version=5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0",
            "channel=tiktok_web",
            "cookie_enabled=true",
            "count=35",
            "coverFormat=1",
            "cursor=0",
            "device_id=7312071981028427270",
            "device_platform=web_pc",
            "focus_state=true",
            "from_page=user",
            "history_len=2",
            "is_fullscreen=false",
            "is_page_visible=true",
            "language=ru-RU",
            "os=windows",
            "priority_region=",
            "referer=",
            "region=FR",
            "screen_height=600",
            "screen_width=800",
            "tz_name=Asia/Yekaterinburg",
            "verifyFp=verify_lpza5vb4_269byymi_mdK6_4RN6_8JMf_Eq6auckGBgbI",
            "webcast_language=ru-RU",
            "X-Bogus=DFSzswVOKVbANcd9tNl6Nt9WcBjC",
            "_signature=_02B4Z6wo00001FRh2YgAAIDAVGHZi2RVVERUYd0AAHCi63"
    );

    public static final @NotNull String userItemList = TikTokAPI.tiktokURL + "api/post/item_list/?";

    @SneakyThrows
    public static @NotNull String getUserItemList(@NonNull final String secUid) {
        @NonNull val builder = new StringBuilder(userItemList);
        @NonNull val map = defaultUserItemListResponseDataList;

        for (int i = 0; i < map.size(); i++) {
            @NonNull val strings = map.get(i).split("=");

            System.out.println(Arrays.toString(strings));

            @NonNull val key = strings[0];
            @NonNull val value = strings.length > 1 ? strings[1] : "";

            if (key.equals("tz_name"))
                builder.append("secUid=").append(secUid).append("&");
            builder.append(key).append("=").append(URLEncoder.encode(value, StandardCharsets.UTF_8));

            if (key.equals("browser_platform"))
                builder.append("&").append("browser_version").append("=").append("5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F120.0.0.0%20Safari%2F537.36%20Edg%2F120.0.0.0");
            if (key.equals("webcast_language"))
                builder.append("&").append("msToken").append("=").append("EYq2XOCNFglZlbZzvr3-8hHytRoIKs18d_8aRKHkDcBUeNNtakbAyHxsv8TgnjYvsb-0MqQU9lWbLUYnckIGRpVeJmbF8Qa7AaK6fb8Q5__RgrbMOyClKID9jjgpYBED2_tmSux3LYKYUdBQHA==");

            if (i != map.size() - 1)
                builder.append("&");

           //@NonNull val entry = map.entrySet().stream().toList().get(i);
            //@NonNull val key = entry.getKey();
            //@NonNull val value = entry.getValue();

            //builder.append(String.format("%s=%s", key, value)).append("&");
        }

        @NonNull val url = builder.toString();
        System.out.println("url " + url);

        @NonNull val get = get(
                url,
                //defaultUserItemListResponseData,
                ContentType.URLENCODED,
                ContentType.JSON
        );

        Thread.sleep(5000);

        System.out.println("parse " + get.parse());
        System.out.println("url " + get.url());
        System.out.println("body " + get.body());
        System.out.println("contentType " + get.contentType());
        System.out.println("charset " + get.charset());

        return get.body();
    }


    @SneakyThrows
    public static Connection.Response get(
            @NonNull final String url,
            //@NonNull final Map<String, String> datas,
            @NonNull final ContentType contentType,
            @NonNull final ContentType contentTypeAccept
    ) {
        @NonNull val connection = JsoupAPI.getConnection(url);

        //datas.forEach(connection::data);

        return connection
                .userAgent("5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0")
                .header("Content-Type", "application/" + contentType.getType())
                .header("Accept", "application/" + contentTypeAccept.getType())
                .followRedirects(true)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .method(Connection.Method.GET)
                .execute();
    }

    public enum ContentType {
        URLENCODED("x-www-form-urlencoded"),
        HTML("html"),
        XML("xml"),
        TEXT("plain"),
        JSON("json");

        @Getter
        private final @NonNull String type;

        ContentType(@NonNull final String type) {
            this.type = type;
        }
    }
}