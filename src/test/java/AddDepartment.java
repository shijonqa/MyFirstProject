
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AddDepartment {
    WebDriver driver;
    static Logger log = Logger.getLogger(AddDepartment.class);
    SampleExcelWrite sampleExcelWrite;
    static String URL,UName,Passwrd;



    @BeforeTest
    public void Login() throws InterruptedException, FileNotFoundException, IOException
    {
        try
        {
            InputStream file = new FileInputStream(new File("/home/shijon/Documents/comrevitsonev2/src/main/resources/Login.properties"));
            Properties loginprops = new Properties();
            loginprops.load(file);
            URL = loginprops.getProperty("Url");
            UName  = loginprops.getProperty("UserName");
            Passwrd  = loginprops.getProperty("Password");


        }
        catch (Exception e)
        {
            log.info("Login File not found ");
        }
        Properties props = new Properties();
        FileReader LoginRead=new FileReader("/home/shijon/Documents/comrevitsonev2/src/main/resources/Login.properties");
        props.load(new FileInputStream("/home/shijon/Documents/comrevitsonev2/src/main/resources/log4j.properties"));
        PropertyConfigurator.configure(props);
        //PropertyConfigurator.configure("log4j.properties");
        System.setProperty("webdriver.chrome.driver", "/home/shijon/Downloads/chromedriver_linux64(1)/chromedriver");
        log.info("***************************************************************");
        log.info("******************Test Begins at this point********************");
        log.info("***************************************************************");
        driver = new ChromeDriver();
        log.info("Browser opens");
        driver.manage().window().maximize();
        log.info("Maximizied the window");
        driver.get(URL);
        // driver.get("https://qa-frontend.revitsone.com/vehicle-list");
        log.info("Url got from login property file"+URL);
        driver.manage().deleteAllCookies();
        WebElement UserName=((ChromeDriver) driver).findElementByXPath("//input[@type='text']");
        //UserName.sendKeys("qa-manager@iinerds.com");
        log.info("User Name "+UName);
        UserName.sendKeys(UName);
        log.info("User name entered");
        WebElement Password=((ChromeDriver) driver).findElementByXPath("//input[@type='password']");
        // Password.sendKeys("revit123");
        Password.sendKeys(Passwrd);
        log.info("Password "+Passwrd);
        WebElement LoginButton=((ChromeDriver) driver).findElementByXPath("//button[@type='button']");
        LoginButton.click();
        log.info("Login button clicked");
        Thread.sleep(6000);

        WebDriverWait wait=new WebDriverWait(driver, 20);

        WebElement MainMenu=((ChromeDriver) driver).findElementByXPath("//img[@alt='Menu' and @class='rone-menu-show']");
        MainMenu.click();
        //  wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='rone-mnicn-3']/parent::*")))).click();
        log.info("menu is clicked");
        Thread.sleep(1000);

        WebElement ContactsUserMenu=((ChromeDriver) driver).findElementByXPath("//img[@alt='Drivers']//following-sibling::span");
        ContactsUserMenu.click();
        log.info("ContactsUser is clicked.");
        Thread.sleep(1000);

        WebElement Department=((ChromeDriver) driver).findElementByXPath("//a[text()='Department']");
        Department.click();
        log.info("Department is clicked.");


        Thread.sleep(7000);

    }


    @Test(dataProvider = "getDeptData")
    public void ContactRegistration_data(String cName,String uDept,String DeptName,String CDate,String EDate,String PNo,String MNo,String E_mail)throws  Exception{



        WebElement AddDepartmentBtn=((ChromeDriver) driver).findElementByXPath("//span[text()='Add Department']//parent::button");
        AddDepartmentBtn.click();
        log.info("Add Department is clicked.");
        Thread.sleep(2000);



        Thread.sleep(1000);
        WebElement CompanyName=((ChromeDriver) driver).findElementByXPath("//label[text()='Select the Company Name']//following-sibling::div[@class='ant-select ant-select-enabled']/child::div[@role='combobox']");
        CompanyName.click();
        log.info("Company is clicked");

        Thread.sleep(1000);
        WebElement chooseCompany=((ChromeDriver) driver).findElementByXPath("//li[contains(.,'"+cName+"')]");
        chooseCompany.click();
        log.info("company is choosen.");

        Thread.sleep(1000);
        WebElement Upperdeptclick=((ChromeDriver) driver).findElementByXPath("//label[text()='Upper Department']//following-sibling::div[@class='ant-select ant-select-enabled']/child::div[@role='combobox']");
        Upperdeptclick.click();
        log.info("Upperdeptclick is clicked");
        Thread.sleep(1000);
        if(!uDept.toLowerCase().contains("na")) {
            log.info("chooseUpperDept is choosen." + uDept);
            WebElement chooseUpperDept = ((ChromeDriver) driver).findElementByXPath("(//li[@role = 'option' and text()='" +uDept+ "'])[1]");
            chooseUpperDept.click();
            log.info("chooseUpperDept is choosen." + uDept);
        }
        Thread.sleep(1000);
        WebElement DepartmentName=((ChromeDriver) driver).findElementByXPath("//label[text()='Department ']/following-sibling::input");
        DepartmentName.clear();
        DepartmentName.sendKeys(DeptName);
        log.info("department is clicked");

        Thread.sleep(1000);
        WebElement CreateDateclick=((ChromeDriver) driver).findElementByXPath("(//input[contains(@placeholder,'Eg.2019-03-05')])[1]");
        CreateDateclick.click();
        log.info("CreateDateclick is clicked");

        Thread.sleep(1000);
        WebElement createDate=((ChromeDriver) driver).findElementByXPath("//input[contains(@class,'ant-calendar-input ')]");
        createDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        createDate.sendKeys(CDate);
        createDate.sendKeys(Keys.ENTER);
        log.info("entered the create date");

      /*  Thread.sleep(1000);
        WebElement ExpDateclick=((ChromeDriver) driver).findElementByXPath("(//input[contains(@class,'ant-calendar-picker-input ant-input')])[2]");
        CreateDateclick.click();
     //   CreateDateclick.sendKeys(Keys.TAB);
      //  CreateDateclick.sendKeys(Keys.ENTER);
        log.info("Expdateclick is clicked");

        Thread.sleep(1000);
        WebElement ExpDate=((ChromeDriver) driver).findElementByXPath("//input[contains(@class,'ant-calendar-input ')]");
        ExpDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        ExpDate.sendKeys("2019-08-20");
        log.info("entered the expired date");

        */

        Thread.sleep(1000);
        WebElement PhoneNumber=((ChromeDriver) driver).findElementByXPath("//label[text()='Phone No']/following-sibling::input");
        PhoneNumber.clear();
        PhoneNumber.sendKeys(PNo);
        log.info("department is clicked");

        PhoneNumber.sendKeys(Keys.chord(Keys.SHIFT,Keys.TAB));
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        driver.switchTo().activeElement().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.switchTo().activeElement().sendKeys(EDate);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);

        Thread.sleep(1000);
        WebElement Mobnumber=((ChromeDriver) driver).findElementByXPath("//label[text()='Mobile No']/following-sibling::input");
        Mobnumber.clear();
        Mobnumber.sendKeys(MNo);
        log.info("department is clicked");

        Thread.sleep(1000);
        WebElement Email=((ChromeDriver) driver).findElementByXPath("//label[text()='Email']/following-sibling::input");
        Email.clear();
        Email.sendKeys(E_mail);
        log.info("department is clicked");

        WebElement SaveBtn=((ChromeDriver) driver).findElementByXPath("//button[@type='button'][contains(.,'Save')]");
        SaveBtn.click();
        log.info("Save button clicked successfully");


        Wait<WebDriver> fluwait = new FluentWait<WebDriver>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);


        try
        {
            WebElement message = fluwait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath("(//span[contains(.,'Department added sucessfully')])[2]"));
                }
            });

        }
        catch (Exception e)
        {
            log.info("cannot able to save");
            try
            {
                WebElement CancelBtn=((ChromeDriver) driver).findElementByXPath("//button[contains(.,'Cancel')]");
                CancelBtn.click();
                log.info("Cancel button clicked successfully");
            }catch (Exception err)
            {
                log.info("Cancel button cannot able to click");
            }
        }
        Thread.sleep(3000);


    }

    @DataProvider
    public Object[][] getDeptData(){
        Object data[][] = ExcelUtils.getTestData("Department");
        return data;
    }


    @AfterTest
    public void CloseBrowser()
    {

        driver.close();
    }
}
