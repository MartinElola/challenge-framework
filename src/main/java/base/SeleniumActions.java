package base;

import base.asserts.SeleniumAsserts;
import base.buttons.SeleniumButtons;
import base.driver.DriverActions;
import base.props.SeleniumProps;
import base.texts.SeleniumTexts;
import base.utils.Substep;
import base.visibility.SeleniumVisibility;
import base.visit.SeleniumVisits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SeleniumActions {

    public WebDriver driver;
    public List<Substep> substeps = new ArrayList<>();

    private final DriverActions driverActions;
    private final SeleniumVisibility visibilityActions;
    private final SeleniumButtons buttonsActions;
    private final SeleniumAsserts assertsActions;
    private final SeleniumTexts textsActions;
    private final SeleniumVisits visitsActions;

    public SeleniumActions() {
        this.driver = DriverActions.getDriver();
        this.driverActions = new DriverActions();
        this.textsActions = new SeleniumTexts(this);
        this.visibilityActions = new SeleniumVisibility(this);
        this.buttonsActions = new SeleniumButtons(this);
        this.assertsActions = new SeleniumAsserts(this);
        this.visitsActions = new SeleniumVisits(this);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    /* Por cada uno de los métodos implementados en las clases, los agrego en forma de substeps a la lista, para luego
    * ir ejecutandolos en orden... */


    //////////////////////////////////////////////////////////
    /////////////////// Framework Methods ////////////////////
    //////////////////////////////////////////////////////////

    public void perform() {
        substeps.forEach(substep -> {
            try {
                substep.runnable.run();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }

    //////////////////////////////////////////////////////////
    //////////////////////// Buttons /////////////////////////
    //////////////////////////////////////////////////////////

    public SeleniumActions click(By locator) {
        substeps.add(new Substep(() -> {
            buttonsActions.click(locator);
        }));
        return this;
    }

    public SeleniumActions waitClick(By locator, long seconds) {
        substeps.add(new Substep(() -> {
            buttonsActions.waitClick(locator, seconds);
        }));
        return this;
    }

    //////////////////////////////////////////////////////////
    ///////////////////////// Type ///////////////////////////
    //////////////////////////////////////////////////////////

    public SeleniumActions typeKeys(By locator, String text) {
        substeps.add(new Substep(() -> {
            textsActions.typeKeys(locator, text);
        }));
        return this;
    }

    public SeleniumActions waitType(By locator, String text, long seconds) {
        substeps.add(new Substep(() -> {
            textsActions.waitType(locator, text, seconds);
        }));
        return this;
    }

    //////////////////////////////////////////////////////////
    //////////////////////// Asserts /////////////////////////
    //////////////////////////////////////////////////////////

    public SeleniumActions assertText(By locator, String text) {
        substeps.add(new Substep(() -> {
            assertsActions.assertText(locator, text);
        }));
        return this;
    }

    //////////////////////////////////////////////////////////
    ////////////////////// Visibility ////////////////////////
    //////////////////////////////////////////////////////////

    public SeleniumActions isDisplayed(By locator) {
        substeps.add(new Substep(() -> {
            visibilityActions.isDisplayed(locator);
        }));
        return this;
    }

    public SeleniumActions waitForElementToAppear(By locator, long seconds) {
        substeps.add(new Substep(() -> {
            visibilityActions.waitForElementToAppear(locator, seconds);
        }));
        return this;
    }

    public SeleniumActions waitForElementToDisappear(By locator, long seconds) {
        substeps.add(new Substep(() -> {
            visibilityActions.waitForElementToDisappear(locator, seconds);
        }));
        return this;
    }

    public SeleniumActions waitForElementToBeEnabled(By locator, long seconds) {
        substeps.add(new Substep(() -> {
            visibilityActions.waitForElementToBeEnabled(locator, seconds);
        }));
        return this;
    }

    public SeleniumActions sleepSeconds(long seconds) {
        substeps.add(new Substep(() -> {
            visibilityActions.sleepSeconds(seconds);
        }));
        return this;
    }

    //////////////////////////////////////////////////////////
    //////////////////////// Visits //////////////////////////
    //////////////////////////////////////////////////////////

    public SeleniumActions visit(String url) {
        substeps.add(new Substep(() -> {
            visitsActions.visit(url);
        }));
        return this;
    }

    public SeleniumActions checkUrl(String url) {
        substeps.add(new Substep(() -> {
            visitsActions.checkUrl(url);
        }));
        return this;
    }

}
