import com.microsoft.playwright.*;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.Proxy;
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
                    playwright.chromium()//,
                    //playwright.webkit()//,
                    //playwright.firefox()
            );

            for (var browserType : browserTypes) {
                try (var launcher = browserType.launch
                        (
                                new BrowserType.LaunchOptions()
                                        .setHeadless(false)
                        )
                ) {
                    var context = launcher.newContext(new Browser.NewContextOptions()
                            //.setProxy(new Proxy("http://38.180.114.56:3128"))
                            .setUserAgent(HTTPApi.getRandomUserAgent())
                            .setViewportSize(411, 731)
                            .setDeviceScaleFactor(2.625)
                            //.setIsMobile(true)
                            //.setHasTouch(true)
                            .setLocale("en-US")
                            .setGeolocation(41.889938, 12.492507)
                            .setPermissions(Arrays.asList("geolocation")));
                    var page = context.newPage();

                    //page.setDefaultTimeout(0);
                    page.navigate(TikTokAPI.tiktokAccountURL);
                    //page.locator("css-txolmk-DivGuestModeContainer").click();//.click(new Locator.ClickOptions().setButton(MouseButton.LEFT).setClickCount(1));

                    Thread.sleep(30000);
                    page.screenshot(new Page.ScreenshotOptions()
                                    .setPath(Paths.get("screenshot-" + browserType.name() + ".png"))
                                    //.setFullPage(true)
                                    .setTimeout(10000)
                            //.setQuality(100)
                    );

                    if (page.isVisible(".css-txolmk-DivGuestModeContainer .exd0a435")) {
                        System.out.println(".css-txolmk-DivGuestModeContainer .exd0a435 visible!!!");
                        page.click(".css-txolmk-DivGuestModeContainer .exd0a435");
                    }

                    System.out.println(page.content());

                    Assertions.assertNotNull(page.content());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
