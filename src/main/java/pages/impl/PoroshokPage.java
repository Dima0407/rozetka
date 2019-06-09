package pages.impl;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.parent.ProductsPage;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Dmytro Torlop
 * on 05.06.19
 */
public class PoroshokPage extends ProductsPage {

    public PoroshokPage(WebDriver webDriver, Properties properties) {
        super(webDriver, properties);
    }
}
