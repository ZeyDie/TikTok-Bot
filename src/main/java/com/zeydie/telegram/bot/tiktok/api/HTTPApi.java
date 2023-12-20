package com.zeydie.telegram.bot.tiktok.api;

import lombok.NonNull;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class HTTPApi {
    private static final @NotNull Random random = new Random();

    public static @NotNull Map<String, String> getHeaders() {
        @NonNull val headers = new HashMap<String, String>();

        headers.put("User-Agent", getRandomUserAgent());
        headers.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        headers.put("Keep-Alive", String.valueOf(random.nextInt(110, 120)));
        headers.put("Connection", "keep-alive");
        headers.put("Referer", getRandomReferer());

        return headers;
    }

    public static @NotNull String getRandomUserAgent() {
        @NonNull val agents = new String[]{
                "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.1.3) Gecko/20090913 Firefox/3.5.3",
                "Mozilla/5.0 (Windows; U; Windows NT 6.1; en; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3 (.NET CLR 3.5.30729)",
                "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3 (.NET CLR 3.5.30729)",
                "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.1) Gecko/20090718 Firefox/3.5.1",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/532.1 (KHTML, like Gecko) Chrome/4.0.219.6 Safari/532.1",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; InfoPath.2)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 1.1.4322; .NET CLR 3.5.30729; .NET CLR 3.0.30729)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Win64; x64; Trident/4.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; SV1; .NET CLR 2.0.50727; InfoPath.2)",
                "Mozilla/5.0 (Windows; U; MSIE 7.0; Windows NT 6.0; en-US)",
                "Mozilla/4.0 (compatible; MSIE 6.1; Windows XP)",
                "Opera/9.80 (Windows NT 5.2; U; ru) Presto/2.5.22 Version/10.51"
        };

        return agents[random.nextInt(agents.length)];
    }

    public static @NotNull String getRandomReferer() {
        @NonNull val string = new StringBuilder();
        val length = random.nextInt(5, 10);

        for (int i = 0; i < length; i++)
            string.append((char) random.nextInt(65, 90));

        return "https://www.google.com/?q=" + string;
    }
}
