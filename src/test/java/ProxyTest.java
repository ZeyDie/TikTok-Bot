import com.zeydie.sgson.SGsonFile;
import com.zeydie.telegram.bot.tiktok.configs.ProxyConfig;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class ProxyTest {
    @Test
    public void available() {
        new SGsonFile(Paths.get("configs").resolve("proxy.cfg"))
                .fromJsonToObject(new ProxyConfig())
                .getProxies()
                .forEach(
                        proxyData -> System.out.printf("Check proxy %s:%d %b%n", proxyData.getIp(), proxyData.getPort(), proxyData.isAvailable())
                );
    }
}