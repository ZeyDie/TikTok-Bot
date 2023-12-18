import com.zeydie.telegram.bot.tiktok.api.JSoupAPI;
import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

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
    public void silenium() throws IOException {
        if (this.file.exists()) this.file.delete();

        var options = new EdgeOptions();

        //options.useWebView(true);

        options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
        options.addArguments(
                "--user-data-dir=C:\\Users\\Daniel\\AppData\\Local\\Microsoft\\Edge\\User Data",
                "--enable-automation",
                "--use-fake-device-for-media-stream",
                "--use-fake-ui-for-media-stream",
                "--aggressive-cache-discard",
                "--disable-client-side-phishing-detection",
                "--no-first-run",
                "--load-images=true",
                "--disk-cache=true",
                "--incognito"
        );
        //options.

        var service = new EdgeDriverService.Builder()
                .withBuildCheckDisabled(true)
                .withLogOutput(System.out)
                .build();


        var driver = new EdgeDriver(service, options);
        //var manager = driver.manage();

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