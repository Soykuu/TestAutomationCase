package NouryonWebTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class SearchAndFilterVacancy {

    WebDriver driver;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.switchTo().window(driver.getWindowHandle());
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test    //Search vacancies by searchbox
    public void test01() throws InterruptedException {

        driver.get("https://www.nouryon.com/");
        driver.findElement(By.xpath("//button[@class='button button--primary']")).click();
        driver.findElement(By.xpath("//a[@data-gtm='page-header.search']")).click();


        WebElement searchBox = driver.findElement(By.xpath("//input[@id='inputsearchelement']"));
        searchBox.sendKeys("Vacancies");
        driver.findElement(By.xpath("//button[@id='search-submit']")).click();

        //Verifying
        WebElement resultPage = driver.findElement(By.xpath("//span[@class='product-search-top-pagination-container__total-results u-mb--m']"));
        String expectedResult = resultPage.getText();
        String expectedText = "Vacancies";
        Assert.assertTrue(expectedResult.contains(expectedText));

    }

    @Test    //Search a specific job by searchbox
    public void test02() throws InterruptedException {

        driver.get("https://www.nouryon.com/");
        driver.findElement(By.xpath("//button[@class='button button--primary']")).click();
        driver.findElement(By.xpath("//a[@data-gtm='page-header.search']")).click();

        //Search specific job
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='inputsearchelement']"));
        searchBox.sendKeys("Analyst");
        driver.findElement(By.xpath("//button[@id='search-submit']")).click();

        //Filter Vacancy
        WebElement vacancy = driver.findElement(By.cssSelector(".form-element-wrapper:nth-child(2) span:nth-child(2)"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", vacancy);

        //Verifying
        WebElement result = driver.findElement(By.xpath("//span[@class='product-search-top-pagination-container__total-results u-mb--m']"));
        String expectedResult = result.getText();
        String expectedText = "Analyst";
        Assert.assertTrue(expectedResult.contains(expectedText));

    }

    @Test    //Go vacancies page and use filter sections
    public void test03() throws InterruptedException {

        driver.get("https://www.nouryon.com/");
        driver.findElement(By.xpath("//button[@class='button button--primary']")).click();
        driver.findElement(By.xpath("//a[@data-gtm='page-header.search']")).click();

        //Go vacancies page
        driver.findElement(By.xpath("//a[contains(text(),'Vacancies')]")).click();

        //Filter Country and Job Level
        WebElement filter1 = driver.findElement(By.xpath("//div[@id='VacancySearchPage']/div[2]/div/nav/section/form/fieldset[2]/ul/li[8]/span/label/span"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", filter1);

        WebElement filter2 = driver.findElement(By.xpath("//div[@id='VacancySearchPage']/div[2]/div/nav/section/form/fieldset[3]/ul/li/span/label/span"));
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("arguments[0].click();", filter2);
        Thread.sleep(1000);

        //On this page filter function does not work automatically.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submit = driver.findElement(By.cssSelector(".button:nth-child(6)"));
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        submit.click();

        //Verifying
        WebElement filterCountry = driver.findElement(By.xpath("//button[contains(.,'United States')]"));
        String expectedFilter = filterCountry.getText();
        String expectedFilterName = "United States";
        Assert.assertTrue(expectedFilter.contains(expectedFilterName));

        WebElement filterLevel = driver.findElement(By.xpath("//button[contains(.,'Entry Level')]"));
        String expectedFilter2 = filterLevel.getText();
        String expectedFilterName2 = "Entry Level";
        Assert.assertTrue(expectedFilter2.contains(expectedFilterName2));

        //Remove Filter Section
        WebElement remove = driver.findElement(By.xpath("//button[contains(.,'United States')]"));
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();", remove);


    }
}


