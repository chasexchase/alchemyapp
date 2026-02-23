package tests;

import driver.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;
import managers.appactions.AppActions;

import java.time.Duration;
import java.util.Arrays;

public class BaseTest {

    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected AppActions app;

    @BeforeEach
    public void setUp() throws Exception {

        driver = AppiumDriver.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        app = new AppActions(driver, wait);

        driver.activateApp("com.ilyin.alchemy");
    }

    @AfterEach
    public void tearDown() {

        try {
            driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
            Thread.sleep(2000);

            // Создаем свайп вверх с помощью W3C Actions
            int centerX = 540; // ширина экрана 1080 / 2
            int startY = 1500;  // нижняя часть
            int endY = 500;     // верхняя часть

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), centerX, startY));

            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                    PointerInput.Origin.viewport(), centerX, endY));

            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(swipe));
            Thread.sleep(1000);

            Runtime.getRuntime().exec("adb shell am force-stop com.ilyin.alchemy");
            Thread.sleep(1000);

            driver.pressKey(new KeyEvent(AndroidKey.HOME));

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Тест завершён");
        }
    }
}