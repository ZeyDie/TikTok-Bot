import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import com.zeydie.telegram.bot.tiktok.api.TikTokRequestAPI;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class JsoupTest {
    @Test
    public void connection() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(TikTokAPI.tiktokAccountURL);

        Assertions.assertNotNull(document);
    }

    @Test
    public void html() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(TikTokAPI.tiktokAccountURL);
        @NotNull var html = document.html();

        System.out.println(html);

        Assertions.assertNotNull(html);
    }

    @Test
    public void data() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(TikTokAPI.tiktokAccountURL);
        @NotNull var json = tiktokParser.getTikTokDataJson(document);

        System.out.println(json);

        Assertions.assertNotNull(json);
    }

    @Test
    public void userItemList() {
        System.out.println(TikTokRequestAPI.getUserItemList("MS4wLjABAAAAv7iSuuXDJGDvJkmH_vz1qkDZYo1apxgzaxdBSeIuPiM"));
    }
}