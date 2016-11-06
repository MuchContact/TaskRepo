import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.google.common.collect.ImmutableMap;
import muco.tasks.fund.Fund;
import muco.tasks.fund.FundStatus;
import muco.tasks.fund.util.MessageHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StockTaskTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();

    }

    @Test
    public void should_retrieve_news_data() throws Exception {
        driver.get("http://fund.eastmoney.com/000311.html");
        String fundName = driver.findElement(By.className("fundDetail-tit")).findElement(By.tagName("div")).getText();
        assertNotNull(fundName);

        String time = driver.findElement(By.id("gz_gztime")).getText();
        assertNotNull(time);

        String netValue = driver.findElement(By.id("gz_gsz")).getText();
        assertNotNull(netValue);

        String diffInNumber = driver.findElement(By.id("gz_gszze")).getText();
        assertNotNull(diffInNumber);

        String diffInPercentage = driver.findElement(By.id("gz_gszzl")).getText();
        assertNotNull(diffInPercentage);

        String crawlingTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Fund fund = new Fund(fundName);
        fund.updateStatus(new FundStatus(netValue, diffInNumber, diffInPercentage, time));
        boolean result = MessageHelper.generateMessage(fund, crawlingTime);
        assertTrue(result);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }
}
