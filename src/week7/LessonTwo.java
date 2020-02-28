package week7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class LessonTwo {
    public WebDriver driver;
    public WebElement cookieAccept,sexRadioButton;


    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito","--disable-gpu",
                "--whitelisted-ips");
        driver = new ChromeDriver(options);
//        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void openSite(){
        driver.get("http://toolsqa.com/automation-practice-form/");
        cookieAccept = driver.findElement(By.id("cookie_action_close_header"));

        if(cookieAccept.isDisplayed()) {
            cookieAccept.click();
        }
    }
    /*
    Practice Exercise
    Launch new Browser
    Open “http://toolsqa.com/automation-practice-form/“
    Challenge One – Select the deselected Radio button (female) for category Sex (Use IsSelected method)
    Challenge Two – Select the Third radio button for category ‘Years of Exp’ (Use Id attribute to select Radio button)
    Challenge Three – Check the Check Box ‘Automation Tester’ for category ‘Profession'( Use Value attribute to match the selection)
    Challenge Four – Check the Check Box ‘Selenium IDE’ for category ‘Automation Tool’ (Use cssSelector)
    */
    @Test(priority = 0)
    public void challengeOne() throws InterruptedException{

        Actions actions = new Actions(driver);
         List<WebElement> sexRadioButtons = (List<WebElement>) driver.findElements(By.name("sex"));
        boolean boolvalue = false;
        if(boolvalue == true){
            actions.moveToElement(sexRadioButtons.get(1));
            sexRadioButtons.get(1).click();
        }
        else{
            actions.moveToElement(sexRadioButtons.get(0));
            sexRadioButtons.get(0).click();
        }
        System.out.println("Challenge 1 Complete");
    }

    @Test(priority = 1)
    public void challengeTwo(){
        List<WebElement> yearExp = (List<WebElement>) driver.findElements(By.name("exp"));
        Actions actions = new Actions(driver);
        actions.moveToElement(yearExp.get(2));
        yearExp.get(2).click();
        System.out.println("Challenge 2 Complete");
    }

    @Test(priority = 2)
    public void challengeThree(){
        Actions actions = new Actions(driver);
        List<WebElement> professionCheckbox = (List<WebElement>) driver.findElements(By.name("profession"));
        actions.moveToElement(professionCheckbox.get(1));

        int jobSize = professionCheckbox.size();

        for(int i = 0; i < jobSize; i++){
            String jobValue = professionCheckbox.get(i).getAttribute("value");
            if(jobValue.equalsIgnoreCase("Automation Tester")){
                professionCheckbox.get(i).click();
                System.out.println("Challenge 3 Complete");
            }
        }

    }

    @Test(priority = 3)
    public void challengeFour(){
        Actions actions = new Actions(driver);
        List<WebElement> autoTools = (List<WebElement>) driver.findElements(By.cssSelector("input[name='tool']"));
        actions.moveToElement(autoTools.get(1));
        int toolsSize = autoTools.size();
        for(int i = 0; i < toolsSize; i++){
            String toolValue = autoTools.get(i).getAttribute("value");
            if(toolValue.equalsIgnoreCase("Selenium IDE")){
                autoTools.get(i).click();
                System.out.println("Challenge 4 Complete");
            }
        }


    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
