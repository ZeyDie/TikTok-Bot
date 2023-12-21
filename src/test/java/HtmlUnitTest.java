import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
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
    public void html() throws IOException, InterruptedException {
        var webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);

        //webClient.getCache().setMaxSize(0);

        var options = webClient.getOptions();

        options.setJavaScriptEnabled(false);
        options.setCssEnabled(false);
        options.setThrowExceptionOnScriptError(false);
        options.setThrowExceptionOnFailingStatusCode(false);
        options.setTimeout(Duration.of(10, ChronoUnit.SECONDS).toMillisPart());

        webClient.waitForBackgroundJavaScript(Duration.of(30, ChronoUnit.SECONDS).toMillisPart());
        webClient.waitForBackgroundJavaScriptStartingBefore(Duration.of(30, ChronoUnit.SECONDS).toMillisPart());

        System.out.println(
               new String( ((UnexpectedPage) webClient.getPage("https://www.tiktok.com/api/post/item_list/?WebIdLastTime=1702479323&aid=1988&app_language=ru-RU&app_name=tiktok_web&browser_language=ru&browser_name=Mozilla&browser_online=true&browser_platform=Win32&browser_version=5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F120.0.0.0%20Safari%2F537.36%20Edg%2F120.0.0.0&channel=tiktok_web&cookie_enabled=true&count=35&coverFormat=1&cursor=0&device_id=7312071981028427270&device_platform=web_pc&focus_state=true&from_page=user&history_len=2&is_fullscreen=false&is_page_visible=true&language=ru-RU&os=windows&priority_region=&referer=&region=FR&screen_height=600&screen_width=800&secUid=MS4wLjABAAAAv7iSuuXDJGDvJkmH_vz1qkDZYo1apxgzaxdBSeIuPiM&tz_name=Asia%2FYekaterinburg&verifyFp=verify_lpza5vb4_269byymi_mdK6_4RN6_8JMf_Eq6auckGBgbI&webcast_language=ru-RU&msToken=EYq2XOCNFglZlbZzvr3-8hHytRoIKs18d_8aRKHkDcBUeNNtakbAyHxsv8TgnjYvsb-0MqQU9lWbLUYnckIGRpVeJmbF8Qa7AaK6fb8Q5__RgrbMOyClKID9jjgpYBED2_tmSux3LYKYUdBQHA==&X-Bogus=DFSzswVOKVbANcd9tNl6Nt9WcBjC&_signature=_02B4Z6wo00001FRh2YgAAIDAVGHZi2RVVERUYd0AAHCi63")
               ).getInputStream()
                       .readAllBytes())
        );
    }
}
