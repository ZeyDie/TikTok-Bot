import com.zeydie.telegram.bot.tiktok.api.TikTokAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonParserTest {
    private final String nickname = "@tiktok";

    @Test
    public void user() {
        @Nullable var userData = TikTokAPI.getTikTokUserData(this.nickname);

        System.out.println(userData);

        Assertions.assertNotNull(userData.getUser());
    }
}