import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokItemListWebParser;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokItemVideoParser;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokUserWebParser;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DataTest {
    @Test
    public void testVideos() throws IOException {
        long start = System.currentTimeMillis();

        @Nullable var userData = new TikTokUserWebParser(TikTokAPI.tiktokAccount).getTikTokUserData();

        Assertions.assertNotNull(userData);

        @Nullable var tikTokItemListData = new TikTokItemListWebParser(userData.getSecUid()).getTikTokItemListData();

        if (tikTokItemListData != null)
            tikTokItemListData.getVideosData()
                    .forEach(
                            videoData -> {
                                try {
                                    new TikTokItemVideoParser(TikTokAPI.getTiktokAccountVideoUrl(TikTokAPI.tiktokAccount, videoData.getId())).getTikTokVideoData();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );

        System.out.println("Timeout: " + (System.currentTimeMillis() - start));
    }
}