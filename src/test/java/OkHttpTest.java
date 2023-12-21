import com.zeydie.telegram.bot.tiktok.api.TikTokRequestAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OkHttpTest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(
                    "https://www.tiktok.com/api/post/item_list/?WebIdLastTime=1702967399&aid=1988&app_language=ru-RU&app_name=tiktok_web&browser_language=ru&browser_name=Mozilla&browser_online=true&browser_platform=Win32&browser_version=5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F120.0.0.0%20Safari%2F537.36%20Edg%2F120.0.0.0&channel=tiktok_web&cookie_enabled=true&count=35&coverFormat=1&cursor=0&device_id=7314188950556493345&device_platform=web_pc&focus_state=true&from_page=user&history_len=2&is_fullscreen=false&is_page_visible=true&language=ru-RU&os=windows&priority_region=&referer=&region=FR&root_referer=https%3A%2F%2Fwww.tiktok.com%2F%40olga_mylife2&screen_height=1080&screen_width=1920&secUid=MS4wLjABAAAAv7iSuuXDJGDvJkmH_vz1qkDZYo1apxgzaxdBSeIuPiM&tz_name=Asia%2FYekaterinburg&verifyFp=verify_li1bfnqe_BL5Ylox6_WM74_4Qb5_91UA_JTuYNO3nvGLQ&webcast_language=ru-RU&msToken=Hca159wfnWqr7TmKivLeitIM_5NuDOWyPNBI-qfB5EPL211SKfXOHyymm3bBP0Z76EIX1WqzD9k72ggcRCk-UVIZCOFfhzEPeUwsakuPuiP2ubZPNw1xgK0ia6fSbGZagY6Tg4SdlMzjdd5R&X-Bogus=DFSzswVOjmJANcd9tNUVTz9WcBje&_signature=_02B4Z6wo00001RxBF.wAAIDBHEEX.QhNrCkcQRNAACKW6e"
            );
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            System.out.println( buffer.toString());
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    @Test
    public void html() throws IOException {
        var client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        var request = new Request.Builder()
                .url("https://www.tiktok.com/api/post/item_list/?WebIdLastTime=1702479323&aid=1988&app_language=ru-RU&app_name=tiktok_web&browser_language=ru&browser_name=Mozilla&browser_online=true&browser_platform=Win32&browser_version=5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F120.0.0.0%20Safari%2F537.36%20Edg%2F120.0.0.0&channel=tiktok_web&cookie_enabled=true&count=35&coverFormat=1&cursor=0&device_id=7312071981028427270&device_platform=web_pc&focus_state=true&from_page=user&history_len=2&is_fullscreen=false&is_page_visible=true&language=ru-RU&os=windows&priority_region=&referer=&region=FR&screen_height=600&screen_width=800&secUid=MS4wLjABAAAAv7iSuuXDJGDvJkmH_vz1qkDZYo1apxgzaxdBSeIuPiM&tz_name=Asia%2FYekaterinburg&verifyFp=verify_lpza5vb4_269byymi_mdK6_4RN6_8JMf_Eq6auckGBgbI&webcast_language=ru-RU&msToken=EYq2XOCNFglZlbZzvr3-8hHytRoIKs18d_8aRKHkDcBUeNNtakbAyHxsv8TgnjYvsb-0MqQU9lWbLUYnckIGRpVeJmbF8Qa7AaK6fb8Q5__RgrbMOyClKID9jjgpYBED2_tmSux3LYKYUdBQHA==&X-Bogus=DFSzswVOKVbANcd9tNl6Nt9WcBjC&_signature=_02B4Z6wo00001FRh2YgAAIDAVGHZi2RVVERUYd0AAHCi63")
                .build();

        var call = client.newCall(request);
        var response = call.execute();

        System.out.println(response);
    }
}