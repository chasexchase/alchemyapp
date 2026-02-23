package managers.adwatcher;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdWatcher {

    private final AndroidDriver driver;

    private final WebDriverWait wait;

    public AdWatcher(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

    }

    /**
     * Ждем появления "Reward granted" до 120 секунд
     * Если не появляется - после таймаута просто нажимаем BackButton
     */

    public void watchAd() throws InterruptedException {
        System.out.println("Ожидание завершения рекламы (макс 120 сек)");

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(120));
            shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().textContains(\"Reward granted\")"
                    )
            ));
            System.out.println("Награда за просмотр полуена, нажимаем BACK");

        } catch (Exception e) {
            System.out.println("'Reward granted' не появился за 120 сек, нажимаем BACK");
        }

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(1000); // пауза для UI


        wait.until(d ->
                d.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.view.View\").instance(9)"
                ))
        );
        System.out.println("Возврат в окно Your Hints после рекламы");
    }
}