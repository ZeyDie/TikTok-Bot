import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonParserTest {
    private final String nickname = "@olga_mylife2";

    @Test
    public void user() {
        Assertions.assertNotNull(TikTokAPI.getTikTokUserData(this.nickname));
    }
}