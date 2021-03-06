package utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public void waitUntilElementHidden(String xpath, int attempts) {
        int count = 0;
        while (count <= attempts) {
            if (webDriver.findElements(By.xpath(xpath)).isEmpty()) {
                return;
            } else {
                sleep(500);
                count++;
            }
        }
        logger.warn("Did not wait while the element is hiding [" + xpath + "]");
        Assert.fail();
    }

    public WebElement waitUntilElementPresent(String xpath, int attempts) {
        int count = 0;
        while (count <= attempts) {
            List<WebElement> elements = webDriver.findElements(By.xpath(xpath));
            if (!elements.isEmpty()) {
                return elements.get(0);
            } else {
                sleep(500);
                count++;
            }
        }
        logger.warn("Did not wait while the element is displayed [" + xpath + "]");
        Assert.fail();
        return null;
    }

    public String getElementAttribute(String attribute, WebElement webElement) {
        return webElement.getAttribute(attribute);
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
