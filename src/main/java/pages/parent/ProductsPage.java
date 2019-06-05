package pages.parent;

import io.qameta.allure.Step;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ComparatorMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by Dmytro Torlop
 * on 30.05.19
 */
public abstract class ProductsPage extends ParentPage {

    @FindBy(xpath = ".//a[@class = 'novisited g-i-more-link']")
    private WebElement downloadYetPage;

    public ProductsPage(WebDriver webDriver, Properties properties) {
        super(webDriver, properties);
    }

    @Step
    public void downloadYetPage() {
        String spinnerXpath = ".//div[@class = 'g-i-tile g-i-tile-catalog preloader-trigger run-animation']";
        actions.clickOnElement(downloadYetPage);
        actions.waitUntilElementHidden(spinnerXpath, 50);
        logger.info("Завантажено ще одну сторінку з товарами");
    }

    @Step
    public void downloadYetPage(int countPages) {
        for (int i = 0; i < countPages; i++) {
            downloadYetPage();
        }
    }

    @Step
    public ArrayList<Product> getProductsFromPage() {
        ArrayList<Product> products = createProductsById(getProductIdsFromPage());
        addNameToProduct(products);
        addPriseToProduct(products);
        addGTagToProduct(products);
        return products;
    }

    @Step
    public ArrayList<String> getProductIdsFromPage() {
        String xpath = ".//input[@type = 'hidden' and contains(@name, 'position_number')]";
        List<WebElement> productElements = webDriver.findElements(By.xpath(xpath));
        ArrayList<String> ids = new ArrayList<>();
        for (WebElement e : productElements) {
            ids.add(actions.getElementAttribute("name", e));
        }
        return ids;
    }

    @Step
    public HashMap<String, Long> getProductByPriceRange(long priceFrom, long priceTo) {
        HashMap<String, Long> result = new HashMap<>();
        for (Product p : getProductsFromPage()) {
            if (p.getPrice() >= priceFrom && p.getPrice() <= priceTo) {
                result.put(p.getName(), p.getPrice());
            }
        }
        return ComparatorMap.sortByValuesDesc(result);
    }

    @Step
    private void addGTagToProduct(ArrayList<Product> products) {
        String xpath = ".//i[@class = 'g-tag-icon-small-popularity g-tag-i sprite']/ancestor::div[@class = 'g-i-tile-i-box']/input";
        for (WebElement element : webDriver.findElements(By.xpath(xpath))) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(element.getAttribute("name"))) {
                    products.get(i).setGTag("TOP");
                }
            }
        }
    }

    @Step
    private void addNameToProduct(ArrayList<Product> products) {
        for (Product p : products) {
            String xpath = ".//input[@name = '" + p.getId() + "']/../div/div/a";
            p.setName(actions.getElementText(webDriver.findElement(By.xpath(xpath))));
        }
    }

    @Step
    private void addPriseToProduct(ArrayList<Product> products) {
        for (Product p : products) {
            String xpath = ".//input[@name ='" + p.getId() + "']/..//div[@name = 'price']/div[@class = 'g-price-uah']";
            String priceFull = actions.getElementText(webDriver.findElement(By.xpath(xpath)));
            p.setPrice(Long.valueOf(priceFull.substring(0, priceFull.length() - 4)
                    .replace(" ", "")));
        }
    }

    @Step
    private ArrayList<Product> createProductsById(ArrayList<String> ids) {
        ArrayList<Product> products = new ArrayList<Product>();
        for (String id : ids) {
            products.add(new Product(id));
        }
        return products;
    }

    @Step
    public ArrayList<String> getProductNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (WebElement e : webDriver.findElements(By.xpath(".//div[@class = 'g-i-tile-i-title clearfix']/a"))) {
            names.add(actions.getElementText(e));
        }
        return names;
    }

    @Step
    public HashMap<String, Long> getTopProducts() {
        HashMap<String, Long> result = new HashMap<>();
        for (Product p : getProductsFromPage()) {
            if (p.getGTag() != null && p.getGTag().equals("TOP")) {
                result.put(p.getName(), p.getPrice());
            }
        }
        return ComparatorMap.sortByValuesDesc(result);
    }
}
