package NouryonWebTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class LoginFail {
    WebDriver driver;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver= new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.switchTo().window(driver.getWindowHandle());
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void test01() throws InterruptedException {

        driver.get( "https://salessupportportal.nouryon.com/");

        //Enter info
        WebElement email = driver.findElement(By.cssSelector("input#signInName"));
        email.sendKeys("demireloyku@gmail.com");
        driver.findElement(By.cssSelector("input#rememberMe")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

        //Verify
        WebElement warn = driver.findElement(By.xpath("//p[contains(text(),'Please enter your password')]"));
        String expectedWarn = warn.getText();
        String expectedText = "Please enter your password";
        Assert.assertTrue(expectedWarn.contains(expectedText));


    }
    @Test
    public void test02() throws InterruptedException {

        driver.get( "https://salessupportportal.nouryon.com/");

        //Enter info
        WebElement password = driver.findElement(By.cssSelector("input#password"));
        password.sendKeys("Password123");
        driver.findElement(By.cssSelector("input#rememberMe")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

        //Verify
        WebElement warn = driver.findElement(By.xpath("//p[contains(text(),'Please enter your E-mail Address')]"));
        String expectedWarn = warn.getText();
        String expectedText = "Please enter your E-mail Address";
        Assert.assertTrue(expectedWarn.contains(expectedText));


    }
    @Test
    public void test03() throws InterruptedException {

        driver.get( "https://salessupportportal.nouryon.com/");

        //Enter info
        WebElement email = driver.findElement(By.cssSelector("input#signInName"));
        email.sendKeys("demireloyku@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("input#password"));
        password.sendKeys("Password123");
        driver.findElement(By.cssSelector("input#rememberMe")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

        //Verify
        WebElement warn = driver.findElement(By.xpath("//p[contains(text(),'Sorry - we are having trouble logging you in. Please see our troubleshooting tips: ')]"));
        String expectedWarn = warn.getText();
        String expectedText = "Sorry - we are having trouble logging you in.";
        Assert.assertTrue(expectedWarn.contains(expectedText));


    }

}
