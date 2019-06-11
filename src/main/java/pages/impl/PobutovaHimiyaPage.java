package pages.impl;

import enums.Category;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.parent.ParentPage;

import java.util.Properties;

public class PobutovaHimiyaPage extends ParentPage {

    public PobutovaHimiyaPage(WebDriver webDriver, Properties properties) {
        super(webDriver, properties);
    }

    @Step
    public void goToCategory(Category category) {
        actions.clickOnElement(actions.waitUntilElementPresent(category.getValue(), 20));
        logger.info("Moved to category \"" + category.name() + "\" [" + webDriver.getCurrentUrl() + "]");
    }
}
