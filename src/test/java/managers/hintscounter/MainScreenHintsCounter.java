package managers.hintscounter;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

/**
 * Счетчик подсказок на главном экране
 */

public class MainScreenHintsCounter {

    private final AndroidDriver driver;

    public MainScreenHintsCounter(AndroidDriver driver) {
        this.driver = driver;
    }

    public int getMainScreenHintsCount() {

        WebElement parent = driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.view.View\").instance(3)"
                )
        );

        WebElement hintElement = parent.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.widget.TextView\")"
                )
        );

        String text = hintElement.getText();
        return Integer.parseInt(text);
        }
    }
