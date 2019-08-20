import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LiveTrack  {
    WebDriver driver;
    String UName,Passwrd;
    @BeforeTest
    public void LaunchUrl() throws InterruptedException
    {
        try
        {
            InputStream file = new FileInputStream(new File("/home/shijon/Documents/comrevitsonev2/src/main/resources/Login.properties"));
            Properties loginprops = new Properties();
            loginprops.load(file);

            UName  = loginprops.getProperty("UserName");
            Passwrd  = loginprops.getProperty("Password");


        }
        catch (Exception e)
        {
            System.out.println("cannot able to fetch the url..");
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-frontend.revitsone.com");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        Thread.sleep(2000);
        WebElement UserName=((ChromeDriver) driver).findElementByXPath("//input[@type='text']");
        //UserName.sendKeys("qa-manager@iinerds.com");

        UserName.sendKeys(UName);

        WebElement Password=((ChromeDriver) driver).findElementByXPath("//input[@type='password']");
        // Password.sendKeys("revit123");
        Password.sendKeys(Passwrd);

        WebElement LoginButton=((ChromeDriver) driver).findElementByXPath("//button[@type='button']");
        LoginButton.click();

        Thread.sleep(4000);
    }
    @AfterTest
    public void CloseBrowser()
    {
        driver.quit();
    }

    @Test
    public void VerifyTrackTitle() throws InterruptedException
    {
        //click the menu
        WebElement MainMenu=((ChromeDriver) driver).findElementByXPath("//img[@alt='Menu' and @class='rone-menu-show']");
        MainMenu.click();

        Thread.sleep(4000);
        //click the live track menu
        WebElement LiveTrackMenu=((ChromeDriver) driver).findElementByXPath("//a[@href='/live-track']/span");
        LiveTrackMenu.click();

        //click the running vehicle

        Thread.sleep(2000);
        WebElement RunningStatus=((ChromeDriver) driver).findElementByXPath("//div[@class='rone-track-stats-single']/child::img[@alt='Running']");
        RunningStatus.click();


        Thread.sleep(2000);
        WebElement ParkingStatus=((ChromeDriver) driver).findElementByXPath("//div[@class='rone-track-stats-single']/child::img[@alt='Parking']");
        ParkingStatus.click();

        Thread.sleep(2000);
        WebElement IdleStatus=((ChromeDriver) driver).findElementByXPath("//div[@class='rone-track-stats-single']/child::img[@alt='Idle']");
        IdleStatus.click();

        Thread.sleep(2000);
        WebElement OfflineStatus=((ChromeDriver) driver).findElementByXPath("//div[@class='rone-track-stats-single']/child::img[@alt='Offline']");
        OfflineStatus.click();

        Thread.sleep(2000);
        WebElement ListView=((ChromeDriver) driver).findElementByXPath("//span[text()=' List']");
        ListView.click();

        Thread.sleep(2000);
        RunningStatus.click();

        Thread.sleep(2000);



        Thread.sleep(4000);



    }

}
