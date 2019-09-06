
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class AddVehicle {

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
        Thread.sleep(5000);



    }
    // @Test(description ="Edit the vehicle data")
    public void EditVehicle()
    {
        try {
            Thread.sleep(8000);
            WebElement MainMenu=((ChromeDriver) driver).findElementByXPath("//img[@src='/static/media/top-nav-0.696f012a.svg']");
            MainMenu.click();
            log.info("clicked the main menu..");
            Thread.sleep(1000);
            WebElement VehicleMenu=((ChromeDriver) driver).findElementByXPath("//img[@alt='Vehicle']");
            VehicleMenu.click();
            log.info("Clicked the vehicle menu.");
            WebElement VehicleList=((ChromeDriver) driver).findElementByXPath("//li[text()='Vehicle List']");
            VehicleList.click();
            log.info("Clicked the vehicle menu.");
            Thread.sleep(1000);
            WebElement SearchBox=((ChromeDriver) driver).findElementByXPath("//input[@class='ant-input']");
            SearchBox.sendKeys("14B0002024");
            log.info("Entered the text in the search box");
            Thread.sleep(5000);
            WebElement tableFloatingMenu=((ChromeDriver) driver).findElementByXPath("//i[@class='anticon anticon-ellipsis']");
            tableFloatingMenu.click();
            log.info("Floating menu is clicked...");
            Thread.sleep(1000);
            WebElement EditIsClicked=((ChromeDriver) driver).findElementByXPath("//li[text()='Edit']");
            EditIsClicked.click();
            Thread.sleep(2000);
            WebElement VehicleName=((ChromeDriver) driver).findElementByXPath("//label[text()='Vehicle Name ']/following-sibling::input");
            VehicleName.clear();
            log.info("Text entered successfully");
            Thread.sleep(3000);

        }
        catch (Exception e)
        {
            log.info("Exception occurs..");
        }


    }

    @Test(dataProvider="getCarModel")
    public void Registration_data(String CarName,String DevId,String LicPlate,String vin,String make,String trim,String model,String year,String vehicleType,String dept,String dsim,
                                  String DinstallDate,String DExprDate,String Slimit,String CmpNam,String Vownnam,
                                  String SplType,String SerNo,String Tamser,String TechNam,String Applstd,String Icount,String eno,String cno)throws  Exception{


        Thread.sleep(4000);
        WebDriverWait wait=new WebDriverWait(driver, 20);

        WebElement AddMenu=((ChromeDriver) driver).findElementByXPath("//img[@class='rone-mnicn-3']/parent::*");
        AddMenu.click();
        //  wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='rone-mnicn-3']/parent::*")))).click();
        log.info("Add menu is clicked");
        Thread.sleep(1000);

        WebElement AddVehicleMenu=((ChromeDriver) driver).findElementByXPath("//a[@href='/add-vehicle']");
        AddVehicleMenu.click();
        log.info("Add vehicle is clicked.");

        Thread.sleep(1000);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Vehicle Name ']/following-sibling::input"))).clear();
        Thread.sleep(1000);
        log.info("vehicle name is visible");
        WebElement VehicleName=((ChromeDriver) driver).findElementByXPath("//label[text()='Vehicle Name ']/following-sibling::input");
        VehicleName.sendKeys(CarName);
        log.info("Vehicle name entered successfully"+CarName);


        Thread.sleep(1000);

        WebElement DeviceId=((ChromeDriver) driver).findElementByXPath("//label[text()='Device ID(D id) ']/following-sibling::input");
        DeviceId.clear();
        log.info("Device id cleared"+DevId);
        DeviceId.sendKeys(DevId);
        log.info("device id entered");



        WebElement DeSimId=((ChromeDriver) driver).findElementByXPath("//label[text()='Device SIM Number ']/following-sibling::input");
        DeSimId.clear();
        log.info("sim id cleared");
        DeSimId.sendKeys(dsim);
        log.info("sim id entered");



        //Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(DinstallDate);
        log.info("installed"+DinstallDate);


        WebElement DInstallDate=((ChromeDriver) driver).findElementByXPath("(//input[@placeholder='Install Date'])[1]");
        log.info("device install date clicked");
        DInstallDate.click();
        WebElement DInstallEnter=((ChromeDriver) driver).findElementByXPath("(//input[@placeholder='Install Date'])[2]");
        log.info("device install date clicked");
        DInstallEnter.sendKeys(DinstallDate);
        DInstallEnter.sendKeys(Keys.ENTER);
        log.info("sim id entered");


        WebElement DExpDate=((ChromeDriver) driver).findElementByXPath("(//input[@placeholder='Expiry Date'])[1]");
        log.info("Device exp clicked");
        DExpDate.click();
        WebElement DExpDateEnter=((ChromeDriver) driver).findElementByXPath("(//input[@placeholder='Expiry Date'])[2]");
        log.info("device install date clicked");
        DExpDateEnter.sendKeys(DExprDate);
        DExpDateEnter.sendKeys(Keys.ENTER);
        log.info("sim id entered");

        Thread.sleep(3000);
/*
        WebElement DExpDate=((ChromeDriver) driver).findElementByXPath("//input[@placeholder='Expiry Date']");
        DExpDate.clear();
        log.info("sim id cleared");
        DExpDate.sendKeys(DExprDate);
        log.info("sim id entered");

*/


        WebElement EngineNo=((ChromeDriver) driver).findElementByXPath("//label[text()='Engine Number']/following-sibling::input");
        EngineNo.clear();
        EngineNo.sendKeys(eno);
        log.info("Engine number added successfully");


        WebElement ChaseNo=((ChromeDriver) driver).findElementByXPath("//label[text()='Chasis Number']/following-sibling::input");
        ChaseNo.clear();
        ChaseNo.sendKeys(cno);
        log.info("Chase number added successfully");




        WebElement LicensePlate=((ChromeDriver) driver).findElementByXPath("//label[text()='License Plate ']/following-sibling::input");
        LicensePlate.clear();
        LicensePlate.sendKeys(LicPlate);
        log.info("register number added successfully");

        WebElement Vin=((ChromeDriver) driver).findElementByXPath("//label[text()='VIN/SN ']/following-sibling::div[@class='rone-inline-btn']/child::input");
        Vin.clear();
        Vin.sendKeys(vin);
        log.info("Vin Entered.");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
        log.info("Scroll happens");
        Thread.sleep(1000);

        //Make
        WebElement Make=((ChromeDriver) driver).findElementByXPath("//label[text()='Make ']/following-sibling::input");
        Make.clear();
        Make.sendKeys(make);
        log.info("make added ");

        //select vehicle type
        Thread.sleep(1000);
        WebElement Type=((ChromeDriver) driver).findElementByXPath("//label[text()='Type ']/following-sibling::div");
        Type.click();
        log.info("Type choosed");

        Thread.sleep(1000);

        //choosing vehicle type
        String VehicleType="//li[text()='"+vehicleType+"']";
        WebElement chooseType=((ChromeDriver) driver).findElementByXPath(VehicleType);
        chooseType.click();
        log.info("Truck is selected.");



        //trim
        WebElement Trim=((ChromeDriver) driver).findElementByXPath("//label[text()='Trim']/following-sibling::input");
        Trim.clear();
        Trim.sendKeys(trim);
        log.info("Trim added");


        //Model
        WebElement Model=((ChromeDriver) driver).findElementByXPath("//label[text()='Model ']/following-sibling::input");
        Model.clear();
        Model.sendKeys(model);
        log.info("Model added");

        WebElement Year=((ChromeDriver) driver).findElementByXPath("//label[text()='Year ']/following-sibling::input");
        Year.clear();
        Year.sendKeys(year);
        log.info("Year added");

        WebElement Speedlimit=((ChromeDriver) driver).findElement(By.id("errorMaxSpeed"));
        Speedlimit.clear();
        Speedlimit.sendKeys(Slimit);
        log.info("speed limiter added");



        WebElement Status=((ChromeDriver) driver).findElementByXPath("//label[text()='Status ']/following-sibling::div");
        Status.click();
        log.info("status selected");

        Thread.sleep(1000);

        WebElement chooseStatus=((ChromeDriver) driver).findElementByXPath("//li[text()='Active']");
        chooseStatus.click();
        log.info("Active status choosed");

        Thread.sleep(1000);
        WebElement company=((ChromeDriver) driver).findElementById("errorDepartment");
        company.click();
        log.info("company choosed");

        Thread.sleep(1000);
        //select which department
        String compname ="//li[text()='"+CmpNam+"']";
        WebElement comname=((ChromeDriver) driver).findElementByXPath(compname);
        comname.click();
        log.info("warehouse dept is selected.");


        Thread.sleep(1000);
        WebElement Department=((ChromeDriver) driver).findElementByXPath("//label[text()='Department ']/following-sibling::div");
        Department.click();
        log.info("department clicked");

        Thread.sleep(1000);
        //select which department
        String DeptName ="//li[@role='option' and @class='ant-select-dropdown-menu-item' and text()='"+dept+"']";

        log.info("dept xpath "+DeptName);

        WebElement chooseDepartment=((ChromeDriver) driver).findElementByXPath(DeptName);
        chooseDepartment.click();
        log.info(dept+" dept is selected");


        WebElement OwnerShip=((ChromeDriver) driver).findElementByXPath("//label[text()='Ownership ']/following-sibling::div");
        OwnerShip.click();
        log.info("ownership is clicked");

        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,1000)");
        log.info("scroll happens...");

        WebElement chooseOwnerShip=((ChromeDriver) driver).findElementByXPath("//li[text()='Owned']");
        chooseOwnerShip.click();
        log.info("owned is selected..");




        WebElement Vown=((ChromeDriver) driver).findElementByXPath("//label[text()='Vehicle Owner']/following-sibling::input");
        Vown.clear();
        Vown.sendKeys(Vownnam);
        log.info("owner added "+Vownnam);

        Thread.sleep(1000);
        WebElement SplimiterType=((ChromeDriver) driver).findElementByXPath("(//div[contains(@class,'ant-select-selection__rendered')])[6]");
        SplimiterType.click();
        log.info("speed limiter choosed");

        Thread.sleep(1000);
        //select which department
        String speedLimit ="//li[text()='Cable Control']";
        WebElement choosespeedLimit=((ChromeDriver) driver).findElementByXPath(speedLimit);
        choosespeedLimit.click();
        log.info("choosespeedLimit is selected.");


        WebElement SNo=((ChromeDriver) driver).findElementByXPath("//label[text()='Serial Number']/following-sibling::input");
        SNo.clear();
        SNo.sendKeys(SerNo);
        log.info("Year added");

        WebElement Tseal=((ChromeDriver) driver).findElementByXPath("//label[text()='Tamper Seal Number']/following-sibling::input");
        Tseal.clear();
        Tseal.sendKeys(Tamser);
        log.info("Year added");


        WebElement TName=((ChromeDriver) driver).findElementByXPath("//label[text()='Technician Name']/following-sibling::input");
        TName.clear();
        TName.sendKeys(TechNam);
        log.info("Tech added");


        WebElement AppStd=((ChromeDriver) driver).findElementByXPath("(//div[contains(@class,'ant-select-selection__rendered')])[7]");
        AppStd.click();

        log.info("Appstd clicked");

        Thread.sleep(1000);
        //select which department
        String Apstd ="//li[text()='"+Applstd+"']";
        WebElement chooseAppStd=((ChromeDriver) driver).findElementByXPath(Apstd);
        chooseAppStd.click();
        log.info("chooseAppStd is selected.");



        WebElement Icountry=((ChromeDriver) driver).findElementByXPath("//label[text()='Installation Country']/following-sibling::input");
        Icountry.clear();
        Icountry.sendKeys(Icount);
        log.info("Installed country added");


        Thread.sleep(1000);

        WebElement Save=((ChromeDriver) driver).findElementByXPath("//span[text()='Save']/ancestor::button[@class='ant-btn rone-btn']");
        Save.click();
        Thread.sleep(7000);
        try{
            newTabClose();
        }
        catch (Exception e)
        {
            log.info("certificate not generated"+e);
        }
        Wait<WebDriver> fluwait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);




        try {
            WebElement message = fluwait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath("//span[contains(.,'User Created Successfully')]"));
                }
            });
        }catch (Exception e)
        {
            log.info("No success message appears..");

        }

    }

    @DataProvider
    public Object[][] getCarModel(){
        Object data[][] = ExcelUtils.getTestData("Sheet1");
        return data;
    }

    @AfterTest
    public void CloseBrowser()
    {

        driver.close();
        driver.quit();
    }

    public void newTabClose()
    {
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());

        driver.switchTo().window(tabs2.get(1));
        driver.close();
        driver.switchTo().window(tabs2.get(0));

    }
}
