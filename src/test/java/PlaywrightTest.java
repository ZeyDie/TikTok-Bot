import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.zeydie.telegram.bot.tiktok.api.HTTPApi;
import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Arrays;

public class PlaywrightTest {
    @Test
    public void html() {
        try (final Playwright playwright = Playwright.create()) {
            var browserTypes = Arrays.asList(
                    //playwright.chromium()//,
                    //playwright.webkit()//,
                    playwright.firefox()
            );

            for (var browserType : browserTypes) {
                browserType.launch(
                        new BrowserType.LaunchOptions()
                        .setHeadless(false)
                );
                try (var launcher = browserType.launch()) {
                    var context = launcher.newContext(new Browser.NewContextOptions()
                            .setUserAgent(HTTPApi.getRandomUserAgent())
                            .setViewportSize(411, 731)
                            .setDeviceScaleFactor(2.625)
                            .setIsMobile(true)
                            .setHasTouch(true)
                            .setLocale("en-US")
                            .setGeolocation(41.889938, 12.492507)
                            .setPermissions(Arrays.asList("geolocation")));
                    var page = context.newPage();

                    page.setDefaultTimeout(0);
                    page.navigate(TikTokAPI.tiktokAccountURL);
                    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot-" + browserType.name() + ".png")));

                    System.out.println(page.content());

                    Assertions.assertNotNull(page.content());
                }
            }
        }
    }
}
