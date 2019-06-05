package pages.impl;

import enums.Category;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        actions.clickOnElement(categoryItem(category));
        logger.info("Виконано перехід до категорії \"" + category.getValue() + "\" [" + webDriver.getCurrentUrl() + "]");
    }

    private WebElement categoryItem(Category category) {
        String xpath = ".//li/*/a[contains(text(), '" + category.getValue() + "')]";
        return webDriver.findElement(By.xpath(xpath));
    }
}
