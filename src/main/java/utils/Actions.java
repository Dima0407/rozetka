package utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Actions {

    private Logger logger = Logger.getLogger(getClass());
    private JavascriptExecutor executor;
    private WebDriver webDriver;

    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
        executor = (JavascriptExecutor) webDriver;
    }

    public void clickOnElement(WebElement element) {
        try {
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            logger.error("Can not click on [" + element + "]\n" + e);
            Assert.fail("Can not click on [" + element + "]\n" + e);
        }
    }

    public void navigateToURL(String URL) {
        try {
            webDriver.navigate().to(URL);
        } catch (Exception e) {
            logger.error("Can not go to [" + URL + "]\n" + e);
            Assert.fail("Can not go to [" + URL + "]\n" + e);
        }
    }

    public String getElementText(WebElement element) {
        String text = element.getText();
        return text;
    }

    public boolean isElementPresent(WebElement element) {
        if (element.isDisplayed())
            return true;
        return false;
    }

    public void waitUntilElementHidden(String xpath, int attempts) {
        int count = 0;

        try {
            while (count <= attempts) {
                if (webDriver.findElements(By.xpath(xpath)).isEmpty()) {
                    return;
                } else {
                    Thread.sleep(500);
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.warn("Did not wait while the element is hiding [" + xpath + "]");
    }

    public String getElementAttribute(String attribute, WebElement webElement) {
        return webElement.getAttribute(attribute);
    }
}
