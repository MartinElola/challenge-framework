package base.visibility;

import base.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;

public class SeleniumVisibility {

    private SeleniumActions base;

    public SeleniumVisibility(SeleniumActions base) {
        this.base = base;
    }

    public SeleniumVisibility isDisplayed(By locator) {
        base.getDriver().findElement(locator).isDisplayed();
        return this;
    }

    public SeleniumVisibility waitForElementToAppear(By locator, long seconds) {
        WebDriverWait wait = new WebDriverWait(base.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this;
    }

    public SeleniumVisibility waitForElementToDisappear(By locator, long seconds) {
        WebDriverWait wait = new WebDriverWait(base.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return this;
    }

    public SeleniumVisibility waitForElementToBeEnabled(By locator, long seconds) {
        WebDriverWait wait = new WebDriverWait(base.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this;
    }

    public SeleniumVisibility sleepSeconds(long seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000L);
        return this;
    }
}
