package managers.hintscounter;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Счетчик подсказок после просмотра рекламы в окне Your Hints
 */

public class AfterAdHintsCounter {

    private final AndroidDriver driver;

    public AfterAdHintsCounter(AndroidDriver driver) {
        this.driver = driver;
    }

    public int getHintsSumAfterAd() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement parent = wait.until(d -> d.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(9)")
        ));

        WebElement hintsValue = parent.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.TextView\")")
        );

        return Integer.parseInt(hintsValue.getText());
    }
}