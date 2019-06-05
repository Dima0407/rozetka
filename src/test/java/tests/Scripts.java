package tests;

import enums.Category;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import parent.ParentTest;

public class Scripts extends ParentTest {

    @Test
    public void scenarioOne() {
        homePage.open();
        homePage.selectLanguage("ua");
        homePage.goToCategory(Category.TELEFONY_TV_EHLECTRONIKA);
        telefonyTvEhlektronikaPage.goToCategory(Category.TELEFONY);
        telephonePage.goToCategory(Category.SMARTFONY);
        smartPhonePage.downloadYetPage(2);
        smartPhonePage.exportResultToFile("result/test1.txt", smartPhonePage.getProductNames());
        notificatorMail.sendMessage("src/main/resources/addresses.txt", "", "result/test1.txt");
    }

    @Test
    public void scenarioTwo() {
        homePage.open();
        homePage.selectLanguage("ua");
        homePage.goToCategory(Category.TOVARY_DLYA_DOMU);
        tovaryDlyaDomuPage.goToCategory(Category.POBUTOVA_HIMIYA);
        pobutovaHimiyaPage.goToCategory(Category.POROSHOK);
        poroshokPage.downloadYetPage(4);
        poroshokPage.exportResultToDB(poroshokPage.getProductByPriceRange(100L, 300L));
    }

    @Test
    public void scenarioThree() {
        homePage.open();
        homePage.selectLanguage("ua");
        homePage.goToCategory(Category.TELEFONY_TV_EHLECTRONIKA);
        telefonyTvEhlektronikaPage.goToCategory(Category.TELEFONY);
        telephonePage.goToCategory(Category.SMARTFONY);
        smartPhonePage.downloadYetPage(2);
        HSSFWorkbook workbook = smartPhonePage.createWorkbook();
        excelManager.addValuesToSheet(workbook, 0, smartPhonePage.getProductByPriceRange(3000, 6000));
        smartPhonePage.downloadYetPage(2);
        excelManager.addValuesToSheet(workbook, 1, smartPhonePage.getTopProducts());
        excelManager.exportWorkbook(workbook, "result/test3.xls");
    }
}
