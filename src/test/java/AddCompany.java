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

public class AddCompany {
    WebDriver driver;
    static Logger log = Logger.getLogger(AddVehicle.class);
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


        WebDriverWait wait=new WebDriverWait(driver, 1000);

        WebElement MainMenu=((ChromeDriver) driver).findElementByXPath("//img[@alt='Menu' and @class='rone-menu-show']");
        MainMenu.click();
        //  wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='rone-mnicn-3']/parent::*")))).click();
        log.info("menu is clicked");
        Thread.sleep(1000);

        WebElement ContactsUserMenu=((ChromeDriver) driver).findElementByXPath("//img[@alt='Drivers']//following-sibling::span");
        ContactsUserMenu.click();
        log.info("ContactsUser is clicked.");
        Thread.sleep(1000);

        WebElement Company=((ChromeDriver) driver).findElementByXPath("//a[text()='Company']");
        Company.click();
        log.info("ContactsUser is clicked.");


        Thread.sleep(10000);


    }


    @Test(dataProvider = "getCompanyData")
    public void ContactRegistration_data(String Cname,String Cemail,String CPhone,String CMob,String CAddress,String CStreet,String CCity,
    String Cstate,String CZip,String CCreatedDate,String CExpirydate,String Clog,String Clat)throws  Exception{



        WebElement AddCompany=((ChromeDriver) driver).findElementByXPath("//span[text()='Add Company']//parent::button");
        AddCompany.click();
        log.info("AddButton is clicked.");
        Thread.sleep(2000);



        Thread.sleep(1000);
        WebElement CompanyName=((ChromeDriver) driver).findElementByXPath("//label[text()='Company Name']/following-sibling::input");
        CompanyName.clear();
        CompanyName.sendKeys(Cname);
        log.info("Company is entered");

        WebElement Email=((ChromeDriver) driver).findElementByXPath("//label[text()='Email']/following-sibling::input");
        Email.clear();
        Email.sendKeys(Cemail);
        log.info("Email entered successfully");
        Thread.sleep(1000);

        WebElement phone=((ChromeDriver) driver).findElementByXPath("//label[text()='Phone']/following-sibling::input");
        phone.clear();
        phone.sendKeys(CPhone);
        log.info("LastName entered successfully");
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1000);
//        js.executeScript("window.scrollBy(0,100)");
//        log.info("scroll happens...");


        WebElement Mobile=((ChromeDriver) driver).findElementByXPath("//label[text()='Mobile']/following-sibling::input");
        Mobile.sendKeys(CMob);
        log.info("Mobile choosed");

        Thread.sleep(1000);
        WebElement Address=((ChromeDriver) driver).findElementByXPath("//label[text()='Address']/following-sibling::input");
        Address.sendKeys(CAddress);
        log.info("Address choosed");

        Thread.sleep(1000);
        WebElement Street=((ChromeDriver) driver).findElementByXPath("//label[text()='Street']/following-sibling::input");
        Street.sendKeys(CStreet);
        log.info("Street choosed");

        Thread.sleep(1000);
        WebElement city=((ChromeDriver) driver).findElementByXPath("//label[text()='City']/following-sibling::input");
        city.sendKeys(CCity);
        log.info("city choosed");

        Thread.sleep(1000);
        WebElement state=((ChromeDriver) driver).findElementByXPath("//label[text()='State']/following-sibling::input");
        state.sendKeys(Cstate);
        log.info("state choosed");





        Thread.sleep(1000);
        WebElement Zip=((ChromeDriver) driver).findElementByXPath("//label[text()='Zip']/following-sibling::input");
        Zip.sendKeys(CZip);
        log.info("Zip choosed");


        Thread.sleep(1000);
        WebElement CreatedDate=((ChromeDriver) driver).findElementByXPath("//input[@placeholder='Eg.2019-03-05']");
        CreatedDate.click();
        log.info("clicked made at created date text box");


        Thread.sleep(1000);
        WebElement EnterDate=((ChromeDriver) driver).findElementByXPath("//input[contains(@class,'ant-calendar-input ')]");
        EnterDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        EnterDate.sendKeys(CCreatedDate);
        log.info("Choosen the date.");


        Thread.sleep(3000);
        WebElement ExpiryDate=((ChromeDriver) driver).findElementByXPath("(//input[@placeholder='Eg.2019-03-05'])[2]");
        ExpiryDate.click();

        log.info("Expiry date text box is clicked.");


        Thread.sleep(1000);
        WebElement Logitute=((ChromeDriver) driver).findElementByXPath("//label[text()='Longitude']/following-sibling::input");
        Logitute.sendKeys(Clog);
        log.info("Logitute choosed");

        Thread.sleep(1000);
        WebElement Latitude=((ChromeDriver) driver).findElementByXPath("//label[text()='Latitude']/following-sibling::input");
        Latitude.sendKeys(Clat);
        log.info("Latitude choosed");



        Thread.sleep(1000);
        WebElement Currency=((ChromeDriver) driver).findElementByXPath("(//div[contains(@class,'rendered')])[4]");
        Currency.click();
        log.info("Currency drop down clicked");

        Thread.sleep(1000);
        WebElement NameCurrency=((ChromeDriver) driver).findElementByXPath("//li[contains(.,'MUR')]");
        NameCurrency.click();
        log.info("choosen the currency..");


        Thread.sleep(1000);
        WebElement ShortFormatDate=((ChromeDriver) driver).findElementByXPath("(//div[@class='ant-select-selection__rendered'])[5]");
        ShortFormatDate.click();
        log.info("short format drop down clicked.");

        js.executeScript("window.scrollBy(0,350)");
        Thread.sleep(1000);
        WebElement ShortDateClick=((ChromeDriver) driver).findElementByXPath("//li[contains(.,'MM/DD/YYYY')]");
        ShortDateClick.click();
        log.info("short date format is clicked.");

        Thread.sleep(1000);
        WebElement TimeFormat=((ChromeDriver) driver).findElementByXPath("(//div[contains(@class,'rendered')])[6]");
        TimeFormat.click();
        log.info("time format clicked");

        Thread.sleep(1000);
        WebElement TimeFormatclick=((ChromeDriver) driver).findElementByXPath("//li[contains(.,'12-hour clock')]");
        TimeFormatclick.click();
        log.info("time format is choosed");

        Thread.sleep(1000);
        WebElement TimeZone=((ChromeDriver) driver).findElementByXPath("(//div[contains(@class,'ant-select-selection__rendered')])[7]");
        TimeZone.click();
        log.info("Time zone is clicked.");

        Thread.sleep(1000);
        WebElement TimeZoneClick=((ChromeDriver) driver).findElementByXPath("//li[contains(.,'Africa/Mauritius')]");
        TimeZoneClick.click();
        log.info("Time zone is choosen.");

        Thread.sleep(1000);
        WebElement Usage=((ChromeDriver) driver).findElementByXPath("(//div[contains(@class,'ant-select-selection__rendered')])[8]");
        Usage.click();
        log.info("Usage is clicked.");

        Thread.sleep(1000);
        WebElement UsageClick=((ChromeDriver) driver).findElementByXPath("//li[contains(.,'Mile')]");
        UsageClick.click();
        log.info("Usage is chosen");

        Thread.sleep(1000);
        WebElement Fuel =((ChromeDriver) driver).findElementByXPath("(//div[contains(@class,'ant-select-selection__rendered')])[9]");
        Fuel.click();
        log.info("Type of fuel is clicked.");

        Thread.sleep(1000);
        WebElement FuelClick=((ChromeDriver) driver).findElementByXPath("//li[contains(.,'Gallons (US)')]");
        FuelClick.click();
        log.info("fuel is choosen.");

        Thread.sleep(1000);
        WebElement Save=((ChromeDriver) driver).findElementByXPath("//button[contains(.,'Save')]");
        Save.click();
        log.info("Clicked the save button.");

        Wait<WebDriver> fluwait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        try {
            WebElement message = fluwait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath("(//span[contains(.,'Company added sucessfully')])[2]"));
                }
            });
        }
        catch (Exception e)
        {
            log.info("success message doesn't appears"+Cname);

        }
        Thread.sleep(3000);

    }

    @DataProvider
    public Object[][] getCompanyData(){
        Object data[][] = ExcelUtils.getTestData("Company");
        return data;
    }


    @AfterTest
    public void CloseBrowser()
    {

        driver.close();
    }
}
