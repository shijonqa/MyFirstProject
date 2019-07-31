import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;


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
        System.setProperty("webdriver.chrome.driver", "/home/shijon/Downloads/chromedriver_linux64(2)/chromedriver");
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
    public void Registration_data(String CarName,String DevId,String LicPlate,String vin,String make,String trim,String model,String year,String vehicleType,String dept)throws  Exception{


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
        log.info("Text entered successfully");


        Thread.sleep(1000);

        WebElement DeviceId=((ChromeDriver) driver).findElementByXPath("//label[text()='Device ID(D id) ']/following-sibling::input");
        DeviceId.clear();
        log.info("Device id cleared");
        DeviceId.sendKeys(DevId);
        log.info("device id entered");

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



        WebElement Status=((ChromeDriver) driver).findElementByXPath("//label[text()='Status ']/following-sibling::div");
        Status.click();
        log.info("status selected");

        Thread.sleep(1000);

        WebElement chooseStatus=((ChromeDriver) driver).findElementByXPath("//li[text()='Active']");
        chooseStatus.click();
        log.info("Active status choosed");


        Thread.sleep(1000);
        WebElement Department=((ChromeDriver) driver).findElementByXPath("//label[text()='Department ']/following-sibling::div");
        Department.click();
        log.info("department choosed");

        Thread.sleep(1000);
        //select which department
        String DeptName ="//li[text()='"+dept+"']";
        WebElement chooseDepartment=((ChromeDriver) driver).findElementByXPath(DeptName);
        chooseDepartment.click();
        log.info("warehouse dept is selected.");


        WebElement OwnerShip=((ChromeDriver) driver).findElementByXPath("//label[text()='Ownership ']/following-sibling::div");
        OwnerShip.click();
        log.info("ownership is clicked");

        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,1000)");
        log.info("scroll happens...");

        WebElement chooseOwnerShip=((ChromeDriver) driver).findElementByXPath("//li[text()='Owned']");
        chooseOwnerShip.click();
        log.info("owned is selected..");

        Thread.sleep(1000);

        WebElement Save=((ChromeDriver) driver).findElementByXPath("//span[text()='Save']/ancestor::button[@class='ant-btn rone-btn']");
        Save.click();
        Thread.sleep(500);
        try {

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Device Added Successfully']"))));
            // Assert.assertTrue(true, (driver.findElement(By.xpath("//span[text()='Device added successfully']")).getText()));
        }
        catch (NoSuchElementException e)
        {
            System.out.println("****Vehicle doesn't added*******"+LicPlate);
            String[] data={CarName.toString(),DevId.toString(),LicPlate.toString(),make.toString(),trim.toString(),model.toString(),vin.toString(),year.toString()};
            for(int i=0;i<data.length;i++)
            {
                log.info(data[i]);
            }
            sampleExcelWrite =new SampleExcelWrite();
            sampleExcelWrite.writeExcel("/home/shijon/Documents/AlreadyExistingVehicle.xlsx","Sheet1",data);
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
    }
}
