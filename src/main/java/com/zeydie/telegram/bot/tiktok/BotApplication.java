package com.zeydie.telegram.bot.tiktok;

import com.frogking.chromedriver.ChromeDriverBuilder;
import com.zeydie.telegram.bot.tiktok.parsers.JsonParser;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokDataParser;
import com.zeydie.telegram.bot.tiktok.parsers.tiktok.TikTokParser;
import com.zeydie.telegrambot.TelegramBotCore;
import com.zeydie.telegrambot.api.modules.interfaces.ISubcore;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Log4j2
public final class BotApplication implements ISubcore {
    @Getter
    private static final @NotNull BotApplication instance = new BotApplication();
    @Getter
    private static final @NotNull TelegramBotCore telegramBotCore = TelegramBotCore.getInstance();

    @Getter
    private final @NotNull JsonParser jsonParser = new JsonParser();
    @Getter
    private final @NotNull TikTokParser tikTokParser = new TikTokParser();
    @Getter
    private final @NotNull TikTokDataParser tikTokDataParser = new TikTokDataParser();


    @Deprecated
    private final static String url = "https://www.tiktok.com/@tiktok";
    @Deprecated
    private final static File file = Paths.get("logs").resolve("parsed.html").toFile();

    @Deprecated
    private static void install(){
        var chromeDriver = new ChromeDriver();

        chromeDriver.get(url);

        chromeDriver.close();
        chromeDriver.quit();
    }

    @Deprecated
    private static void test() {
        install();

        var chromeOptions = new ChromeOptions()
                //.addArguments("--headless=new")
                //.addArguments("--no-sandbox")
                //.addArguments("--disable-dev-shm-usage")
                ;
        var chromeDriver = new ChromeDriverBuilder().build(
                chromeOptions,
                "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"
        );
        var manage = chromeDriver.manage();

        try {
            if (file.exists()) file.delete();
            else file.createNewFile();

            manage.window().maximize();
            manage.timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));

            chromeDriver.get(url);

            Thread.sleep(5000);

            chromeDriver.navigate().refresh();

            //Thread.sleep(1000);

            var fluentWait = new FluentWait<WebDriver>(chromeDriver);

            fluentWait.withTimeout(Duration.of(5, ChronoUnit.SECONDS));
            fluentWait.pollingEvery(Duration.of(200, ChronoUnit.MILLIS));
            fluentWait.ignoring(NoSuchElementException.class);
            fluentWait.ignoring(TimeoutException.class);
            fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.ByXPath.xpath("//div[@data-e2e='user-post-item-list']/div")));

            Files.write(file.toPath(), Collections.singleton(chromeDriver.getPageSource()), StandardCharsets.UTF_8);

            chromeDriver.close();
            chromeDriver.quit();
        } catch (Exception exception) {
            chromeDriver.close();
            chromeDriver.quit();
        }
    }

    @SneakyThrows
    public static void main(@Nullable final String[] args) {
        log.info("Starting {}...", BotApplication.class.getName());

        log.info("Register subcore {}...", instance.getName());
        telegramBotCore.registerSubcore(instance);

        log.info("Launch {}...", telegramBotCore.getName());
        telegramBotCore.launch(args);

        test();
    }

    @Override
    public @NotNull String getName() {
        return this.getClass().getName();
    }

    @Override
    public void launch(@Nullable final String[] args) {
    }

    @Override
    public void preInit() {
    }

    @Override
    public void init() {
    }

    @Override
    public void postInit() {
    }

    @Override
    public void stop() {
    }
}