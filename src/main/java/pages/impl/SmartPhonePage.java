package pages.impl;

import io.qameta.allure.Step;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import pages.parent.ProductsPage;

import java.util.ArrayList;
import java.util.Properties;

public class SmartPhonePage extends ProductsPage {

    public SmartPhonePage(WebDriver webDriver, Properties properties) {
        super(webDriver, properties);
    }

    @Step
    public HSSFWorkbook createWorkbook() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Name");
        headers.add("Price");
        HSSFWorkbook workbook = excelManager.createWorkbook(2);
        excelManager.addHeadersToCells(workbook, 0, headers);
        excelManager.addHeadersToCells(workbook, 1, headers);
        return workbook;
    }
}
