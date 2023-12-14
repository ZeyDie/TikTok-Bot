import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.zeydie.telegram.bot.tiktok.api.JSoupAPI;
import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

public class BrowsersTest {
    private final String url = "https://www.tiktok.com/@tiktok";
    private final File file = Paths.get("logs").resolve("parsed.html").toFile();

    @Test
    public void playwright() {
        if (this.file.exists()) this.file.delete();

        try (final Playwright playwright = Playwright.create()) {
            final BrowserType chromium = playwright.webkit();
            final Browser browser = chromium.launch();
            final Page page = browser.newPage();

            page.navigate(this.url);

            System.out.println(page.content());
            Files.write(this.file.toPath(), Collections.singleton(page.content()), StandardCharsets.UTF_8);

            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void silenium() throws IOException {
        if (this.file.exists()) this.file.delete();

        var driver = new EdgeDriver();
        var manager = driver.manage();

        manager.window().maximize();
        manager.deleteAllCookies();
        manager.timeouts().pageLoadTimeout(Duration.of(1, ChronoUnit.MINUTES));
        manager.timeouts().implicitlyWait(Duration.of(1, ChronoUnit.MINUTES));

        driver.get(this.url);

        Files.write(this.file.toPath(), Collections.singleton(driver.getPageSource()), StandardCharsets.UTF_8);

        //driver.quit();
    }

    @Test
    public void jsoup() throws IOException {
        if (this.file.exists()) this.file.delete();

        Files.write(
                this.file.toPath(),
                Collections.singleton(
                        Jsoup.connect(this.url)
                                .userAgent(JSoupAPI.USER_AGENT)
                                .proxy(ConfigStore.getProxyConfig().getProxyAviable())
                                .timeout(60000)
                                .get()
                                .html()
                ),
                StandardCharsets.UTF_8);
    }
}