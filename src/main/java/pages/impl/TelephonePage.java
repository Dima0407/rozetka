package pages.impl;

import enums.Category;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.parent.ParentPage;

import java.util.Properties;

/**
 * Created by Dmytro Torlop
 * on 30.05.19
 */
public class TelephonePage extends ParentPage {

    public TelephonePage(WebDriver webDriver, Properties properties) {
        super(webDriver, properties);
    }

    @Step
    public void goToCategory(Category category) {
        String xpath = ".//li/*/a[contains(@href, '" + category.getValue() + "')]";
        actions.clickOnElement(actions.waitUntilElementPresent(xpath, 20));
        logger.info("Moved to category \"" +  category.name() + "\" [" + webDriver.getCurrentUrl() + "]");
    }
}
