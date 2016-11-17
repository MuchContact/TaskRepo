import muco.tasks.fund.Fund;
import muco.tasks.fund.FundStatus;
import muco.tasks.fund.util.MessageHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StockTaskTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @Test
    public void should_retrieve_news_data_for_muco() throws Exception {
        crawlStockData("http://fund.eastmoney.com/000311.html");
    }

    @Test
    public void should_retreve_stock_data_for_lordjesusbaby() throws Exception {
        crawlStockData("http://fund.eastmoney.com/160615.html");
    }

    private void crawlStockData(String url) {
        driver.get(url);
        String fundName = driver.findElement(By.className("fundDetail-tit")).findElement(By.tagName("div")).getText();
        fundName += ")";
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
        String to = System.getenv("TO");
        String msg_store_path = System.getenv("MSG_STORE_PATH");
        assertNotNull(to);
        assertNotNull(msg_store_path);
        boolean result = MessageHelper.generateMessage(fund, crawlingTime, to, msg_store_path);
        assertTrue(result);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }
}
