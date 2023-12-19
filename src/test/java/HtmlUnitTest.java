import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zeydie.telegram.bot.tiktok.BotApplication;
import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HtmlUnitTest {
    @Test
    public void connection() throws IOException {
        var webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        var options = webClient.getOptions();

        options.setJavaScriptEnabled(true);
        options.setCssEnabled(false);
        options.setThrowExceptionOnScriptError(false);
        options.setThrowExceptionOnFailingStatusCode(false);
        options.setTimeout(Duration.of(10, ChronoUnit.SECONDS).toMillisPart());

        var htmlPage = (HtmlPage) webClient.getPage(TikTokAPI.tiktokAccountURL);

        webClient.waitForBackgroundJavaScript(Duration.of(30, ChronoUnit.SECONDS).toMillisPart());

        Assertions.assertNotNull(htmlPage.asXml());
    }

    @Test
    public void html() throws IOException {
        var webClient = new WebClient(BrowserVersion.FIREFOX);

        webClient.getCache().setMaxSize(0);

        var options = webClient.getOptions();

        options.setJavaScriptEnabled(true);
        options.setCssEnabled(true);
        options.setThrowExceptionOnScriptError(false);
        options.setThrowExceptionOnFailingStatusCode(false);
        options.setTimeout(Duration.of(10, ChronoUnit.SECONDS).toMillisPart());

        webClient.waitForBackgroundJavaScript(Duration.of(30, ChronoUnit.SECONDS).toMillisPart());
        webClient.waitForBackgroundJavaScriptStartingBefore(Duration.of(30, ChronoUnit.SECONDS).toMillisPart());

        var htmlPage = (HtmlPage) webClient.getPage(TikTokAPI.tiktokAccountURL);

        @NotNull var document = htmlPage.asXml();

        System.out.println(document);

        Assertions.assertNotNull(document);
    }
}
