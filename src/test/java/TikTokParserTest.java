import com.zeydie.telegram.bot.tiktok.BotApplication;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TikTokParserTest {
    private final String nickname = "@olga_mylife2";

    @Test
    public void connection() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(document);
    }

    @Test
    public void followers() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(tiktokParser.getFollowers(document));
    }

    @Test
    public void likes() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(tiktokParser.getLikes(document));
    }

    @Test
    public void bio() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(tiktokParser.getUserBio(document));
    }

    @Test
    public void title() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(tiktokParser.getUserTitle(document));
    }

    @Test
    public void link() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(tiktokParser.getUserLink(document));
    }

    @Test
    public void sigi() {
        @NotNull var tiktokParser = BotApplication.getInstance().getTikTokParser();
        @NotNull var document = tiktokParser.getDocumentURL(tiktokParser.getUserURL(this.nickname));

        Assertions.assertNotNull(tiktokParser.getTikTokSigiJson(document));
    }
}