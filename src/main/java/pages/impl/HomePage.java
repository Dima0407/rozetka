package pages.impl;

import enums.Category;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.parent.ParentPage;

import java.util.Properties;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//span[@class = 'header-topline__language-item_state_active']")
    private WebElement currentLang;
    @FindBy(xpath = ".//li[@class = 'header-topline__language-item']/a")
    private WebElement switcherLang;

    private String url;

    public HomePage(WebDriver webDriver, Properties properties) {
        super(webDriver, properties);
        this.url = properties.getProperty("base.url");
    }

    @Step
    public void open() {
        actions.navigateToURL(url);
        logger.info("Starting page is open [" + webDriver.getCurrentUrl() + "]");
    }

    @Step
    public void selectLanguage(String lang) {
        String currentLang = actions.getElementText(this.currentLang);
        if (!currentLang.toLowerCase().equals(lang.toLowerCase())) {
            actions.clickOnElement(switcherLang);
        }
        String selectedLangMessage;
        if (lang.toLowerCase().equals("ua")) {
            selectedLangMessage = "Ukrainian language is selected";
        } else {
            selectedLangMessage = "Russian language is selected";
        }
        logger.info(selectedLangMessage);
    }

    @Step
    public void goToCategory(Category category) {
        String xpath = ".//ul[@class = 'menu-categories menu-categories_type_main']/li[@class = 'menu-categories__item']/a[@class = 'menu-categories__link' and contains(@href,  '" + category.getValue() + "')]";
        actions.clickOnElement(actions.waitUntilElementPresent(xpath, 50));
        logger.info("Moved to category \"" + category.name() + "\" [" + webDriver.getCurrentUrl() + "]");
    }
}
