package pages.impl;

import enums.Category;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.parent.ParentPage;

import java.util.Properties;

public class TelefonyTvEhlektronikaPage extends ParentPage {

    public TelefonyTvEhlektronikaPage(WebDriver webDriver, Properties properties) {
        super(webDriver, properties);
    }

    @Step
    public void goToCategory(Category category) {
        String xpath = ".//li/*/a[contains(@href, '" + category.getValue() + "')]";
        actions.clickOnElement(actions.waitUntilElementPresent(xpath, 20));
        logger.info("Moved to category \"" + category.name() + "\" [" + webDriver.getCurrentUrl() + "]");
    }
}
