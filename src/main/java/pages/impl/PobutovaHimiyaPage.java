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
 * on 05.06.19
 */
public class PobutovaHimiyaPage extends ParentPage {

    public PobutovaHimiyaPage(WebDriver webDriver, Properties properties) {
        super(webDriver, properties);
    }

    @Step
    public void goToCategory(Category category) {
        actions.clickOnElement(categoryItem(category));
        logger.info("Виконано перехід до категорії \"" + category.getValue() + "\" [" + webDriver.getCurrentUrl() + "]");
    }

    private WebElement categoryItem(Category subCategory) {
        String xpath = ".//a[contains(text(), '" + subCategory.getValue() + "')]";
        return webDriver.findElement(By.xpath(xpath));
    }

}
