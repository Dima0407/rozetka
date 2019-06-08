package parent;

import db.MysqlManager;
import excel.ExcelManager;
import io.qameta.allure.Attachment;
import notificator.NotificatorMail;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.impl.*;
import utils.PropertiesLoader;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ParentTest {

    private final String PATH_TO_PROPERTIES = "src/main/resources/app.properties";

    private Properties properties;
    private WebDriver webDriver;
    protected NotificatorMail notificatorMail;

    //PAGES
    protected HomePage homePage;
    protected TelefonyTvEhlektronikaPage telefonyTvEhlektronikaPage;
    protected TelephonePage telephonePage;
    protected SmartPhonePage smartPhonePage;
    protected TovaryDlyaDomuPage tovaryDlyaDomuPage;
    protected PobutovaHimiyaPage pobutovaHimiyaPage;
    protected PoroshokPage poroshokPage;
    protected ExcelManager excelManager;
    protected MysqlManager mysqlManager;

    @Before
    public void setUp() {
        this.properties = PropertiesLoader.loadProperties(PATH_TO_PROPERTIES);

        System.setProperty(properties.getProperty("driver.name"),
                new File(properties.getProperty("driver.path")).getAbsolutePath());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Long.valueOf(properties.getProperty("wait.element")), TimeUnit.MILLISECONDS);
        notificatorMail = new NotificatorMail();
        excelManager = new ExcelManager();
        mysqlManager = new MysqlManager();

        //INIT PAGES
        homePage = new HomePage(webDriver, properties);
        telefonyTvEhlektronikaPage = new TelefonyTvEhlektronikaPage(webDriver, properties);
        telephonePage = new TelephonePage(webDriver, properties);
        smartPhonePage = new SmartPhonePage(webDriver, properties);
        tovaryDlyaDomuPage = new TovaryDlyaDomuPage(webDriver, properties);
        pobutovaHimiyaPage = new PobutovaHimiyaPage(webDriver, properties);
        poroshokPage = new PoroshokPage(webDriver, properties);
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        @Attachment(value = "Page screenshot", type = "image/png")
        public byte[] saveScreenshot(byte[] screenShot) {
            return screenShot;
        }

        public void screenshot() {
            if (webDriver == null) {
                return;
            }
            saveScreenshot(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        }

        @Override
        protected void finished(Description description) {
            webDriver.quit();
        }
    };

}
