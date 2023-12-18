import com.zeydie.telegram.bot.tiktok.BotApplication;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

public class TikTokParserTest {
    private final String nickname = "@tiktok";

    @Test
    public void connection() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(document);
    }

    @Test
    public void followers() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));
        @NotNull var followers = tiktokParser.getFollowers(document);

        System.out.println("Followers: " + followers);

        Assertions.assertNotNull(followers);
    }

    @Test
    public void likes() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));
        @NotNull var likes = tiktokParser.getLikes(document);

        System.out.println("Likes: " + likes);

        Assertions.assertNotNull(likes);
    }

    @Test
    public void bio() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));
        @NotNull var bio = tiktokParser.getUserBio(document);

        System.out.println("Bio: " + bio);

        Assertions.assertNotNull(bio);
    }

    @Test
    public void title() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));
        @NotNull var title = tiktokParser.getUserTitle(document);

        System.out.println("Title: " + title);

        Assertions.assertNotNull(title);
    }

    @Test
    public void link() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));
        @NotNull var link = tiktokParser.getUserLink(document);

        System.out.println("Link: " + link);

        Assertions.assertNotNull(link);
    }

    @Test
    public void sigi() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(tiktokParser.getTikTokSigiJson(document));
    }
}