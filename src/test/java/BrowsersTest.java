import com.frogking.chromedriver.ChromeDriverBuilder;
import com.zeydie.telegram.bot.tiktok.api.JSoupAPI;
import com.zeydie.telegram.bot.tiktok.configs.ConfigStore;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

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
    public void silenium() {
        var chromeOptions = new ChromeOptions()
                //.addArguments("--headless=new")
                //.addArguments("--no-sandbox")
                //.addArguments("--disable-dev-shm-usage")
                ;
        var chromeDriver = new ChromeDriverBuilder().build(
                chromeOptions,
                "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"
        );
        var manage = chromeDriver.manage();

        try {
            if (this.file.exists()) this.file.delete();
            else this.file.createNewFile();

            manage.window().maximize();
            manage.timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));

            chromeDriver.get(this.url);

            Thread.sleep(5000);

            chromeDriver.navigate().refresh();

            //Thread.sleep(1000);

            var fluentWait = new FluentWait<WebDriver>(chromeDriver);

            fluentWait.withTimeout(Duration.of(5, ChronoUnit.SECONDS));
            fluentWait.pollingEvery(Duration.of(200, ChronoUnit.MILLIS));
            fluentWait.ignoring(NoSuchElementException.class);
            fluentWait.ignoring(TimeoutException.class);
            fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.ByXPath.xpath("//div[@data-e2e='user-post-item-list']/div")));

            Files.write(this.file.toPath(), Collections.singleton(chromeDriver.getPageSource()), StandardCharsets.UTF_8);

        } catch (Exception exception) {
            chromeDriver.close();
            chromeDriver.quit();
        }
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