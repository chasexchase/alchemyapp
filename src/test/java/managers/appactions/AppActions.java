package managers.appactions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import managers.adwatcher.AdWatcher;
import managers.hintscounter.AfterAdHintsCounter;
import managers.hintscounter.MainScreenHintsCounter;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppActions {

    private final WebDriverWait wait;
    private final MainScreenHintsCounter mainHints;
    private final AfterAdHintsCounter adHints;
    public final AdWatcher adWatcher;


    public AppActions(AndroidDriver driver, WebDriverWait wait) {
        this.wait = wait;
        this.mainHints = new MainScreenHintsCounter(driver);
        this.adHints = new AfterAdHintsCounter(driver);
        this.adWatcher = new AdWatcher(driver, wait);
    }

    public MainScreenHintsCounter mainScreenHints() {
        return mainHints;
    }

    public AfterAdHintsCounter adWindowHints() {
        return adHints;
    }

    public void playGame() {
        wait.until(d -> d.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Play\")")
        )).click();
    }

    public void addHintOnMainScreen() {

        wait.until(d -> d.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")
        )).click();

    }

    public void watchAdAndWait() throws InterruptedException {

        wait.until(d -> d.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Watch\")")))
                .click();

        adWatcher.watchAd();
    }

}