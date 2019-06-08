package pages.parent;

import excel.ExcelManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Actions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Properties;

public abstract class ParentPage {

    public Properties properties;
    public Logger logger;
    public WebDriver webDriver;
    public Actions actions;
    public ExcelManager excelManager;

    public ParentPage(WebDriver webDriver, Properties properties) {
        this.logger = Logger.getLogger(getClass().getName());
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        this.excelManager = new ExcelManager();
        this.properties = properties;
        PageFactory.initElements(webDriver, this);
    }

    public void exportResultToFile(String path, ArrayList<String> values) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(path), "UTF-8"));
                    new FileOutputStream(path)));
            for (String item : values) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();
            logger.info("Results are exported [" + path + "]");
        } catch (IOException e) {
            logger.error("Error exporting results [" + path + "]");
            e.printStackTrace();
        }
    }
}
