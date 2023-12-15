import com.zeydie.telegram.bot.tiktok.api.TikTokRequestAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TikTokPostRequest {
    @Test
    public void ClientAccessTokenManagement() throws IOException {
        Assertions.assertNotNull(TikTokRequestAPI.getAccessToken());
    }
}