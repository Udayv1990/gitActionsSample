package com.pocabc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;


public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");

    }

    @Test(description = "This test validates error message when credentials are incorrect", priority = 0)
    public void verifyIncorrectCredentials() {

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123$$");
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).submit();

        String actualErrorMessage = driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")).getText();

        // Verify Error Message
        Assert.assertEquals(actualErrorMessage,"Invalid credentials");

    }

    @Test(description = "This test will fail", priority = 1)
    public void verifyBlankCredentials() {

        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("admin123$$");
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).submit();

        String actualErrorMessage = driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")).getText();

        // Verify Error Message
        Assert.assertEquals(actualErrorMessage,"Invalid credentials");

    }

    @Test(description = "This test validates  successful login to Home page", priority = 2)
    public void verifyLoginPage() {

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).submit();

        String homePageHeading = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[1]/h5")).getText();

        //Verify new page - HomePage
        Assert.assertEquals(homePageHeading,"Employee Information");

    }

    @AfterMethod
    public void teardown() {

        driver.quit();
    }

}
