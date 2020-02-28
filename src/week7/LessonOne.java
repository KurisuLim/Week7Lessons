package week7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class LessonOne {
    public WebDriver driver;
    public WebElement firstName, lastName,
            submitButton, partialLinkTest,
            linkText;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu",
                "--whitelisted-ips");
        driver = new ChromeDriver(options);
//        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void openSite(){
        driver.get("http://toolsqa.com/automation-practice-form/");
    }
    /*
     Practice Exercise 1
     Launch new Browser
     Open URL http://toolsqa.com/automation-practice-form/
     Type Name & Last Name (Use Name locator)
     Click on Submit button (Use ID locator)
    */
    @Test(priority = 0)
    public void practiceExercise1(){
        firstName = driver.findElement(By.name("firstname"));
        lastName = driver.findElement(By.id("lastname"));
        submitButton = driver.findElement(By.id("buttonwithclass"));

        String url = driver.getCurrentUrl();

        firstName.sendKeys("Chris");
        lastName.sendKeys("Lim");
        submitButton.click();
        System.out.println("Submission Success");

    }
    /*
        Practice Exercise 2
        Launch new Browser
        Open URL http://toolsqa.com/automation-practice-form/
        Click on the Link “Partial Link Test” (Use ‘patialLinkTest’ locator and search by ‘Partial’ word)
        Identify Submit button with ‘tagName’, convert it into a string and print it on the console
        Click on the Link “Link Test” (Use ‘linkTest’ locator)
    */
    @Test(priority = 1)
    public void practiceExercise2(){
        partialLinkTest = driver.findElement(By.partialLinkText("Partial"));
        partialLinkTest.click();
        System.out.println("Partial Link Pass");
        submitButton = driver.findElement(By.tagName("button"));
        String buttonText = submitButton.toString();
        System.out.println(buttonText);
        linkText = driver.findElement(By.linkText("Link Test"));
        linkText.click();
        System.out.println("Link Text Pass");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
