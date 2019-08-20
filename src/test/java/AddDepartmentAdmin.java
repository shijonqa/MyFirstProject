import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.*;
import java.util.Properties;


public class AddDepartmentAdmin {

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
        Thread.sleep(4000);


    }


    @Test
    public void ContactRegistration_data()throws  Exception{


        Thread.sleep(3000);
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

        WebElement ContactListMenu=((ChromeDriver) driver).findElementByXPath("//a[text()='Contact List']");
        ContactListMenu.click();
        log.info("ContactsUser is clicked.");


        Thread.sleep(10000);

        WebElement AddContact=((ChromeDriver) driver).findElementByXPath("//span[text()='Add Contact']/parent::button[@type='button']");
        AddContact.click();
        log.info("AddButton is clicked.");
        Thread.sleep(2000);

        //choose a contact classification
        WebElement ChooseClassification=((ChromeDriver) driver).findElementByXPath("//input[@type='radio' and @value = '9']");
        ChooseClassification.click();
        log.info("choose classification Admin is selected.");


        Thread.sleep(1000);
        WebElement FirstName=((ChromeDriver) driver).findElementByXPath("//label[text()='First Name ']/following-sibling::input");
        FirstName.clear();
        FirstName.sendKeys("test");
        log.info("FirstName is entered");

        WebElement MiddleName=((ChromeDriver) driver).findElementByXPath("//label[text()='Middle Name']/following-sibling::input");
        MiddleName.clear();
        MiddleName.sendKeys("test");
        log.info("Middle name entered successfully");
        Thread.sleep(1000);

        WebElement LastName=((ChromeDriver) driver).findElementByXPath("//label[text()='Last Name']/following-sibling::input");
        LastName.clear();
        LastName.sendKeys("test");
        log.info("LastName entered successfully");
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,100)");
        log.info("scroll happens...");

        Thread.sleep(1000);
        WebElement Department=((ChromeDriver) driver).findElementByXPath("//label[text()='Department ']/following-sibling::div");
        Department.click();
        log.info("department choosed");

        Thread.sleep(1000);
        WebElement JobTitle=((ChromeDriver) driver).findElementByXPath("//label[text()='Job Title']/following-sibling::input");
        JobTitle.clear();
        JobTitle.sendKeys("test");
        log.info("JobTitle entered successfully");
        Thread.sleep(1000);

        WebElement DOB=((ChromeDriver) driver).findElementByXPath("//label[text()='Date of Birth']/following-sibling::span[@class='ant-calendar-picker']/child::div/child::input");
        // DOB.click();
        Thread.sleep(1000);
        // js.executeScript("document.value=’01-07-2019’;");
        //WebElement DOB1=((ChromeDriver) driver).findElementByXPath("//label[text()='Date of Birth']/following-sibling::span[@class='ant-calendar-picker']/child::div/child::input");
        //   DOB.sendKeys("01-07-2019");
        log.info("DOB entered successfully");
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,100)");
        log.info("scroll happens...");

        WebElement phone=((ChromeDriver) driver).findElementByXPath("//label[text()='Phone']/following-sibling::input");
        phone.clear();
        phone.sendKeys("");
        log.info("phone entered successfully");
        Thread.sleep(1000);

        WebElement Email=((ChromeDriver) driver).findElementByXPath("//label[text()='Email']/following-sibling::input");
        Email.clear();
        Email.sendKeys("");
        log.info("Email entered successfully");
        Thread.sleep(1000);

        WebElement Address1=((ChromeDriver) driver).findElementByXPath("//label[text()='Address 1']/following-sibling::input");
        Address1.clear();
        Address1.sendKeys("");
        log.info("Address1 entered successfully");
        Thread.sleep(1000);

        WebElement Address2=((ChromeDriver) driver).findElementByXPath("//label[text()='Address 2']/following-sibling::input");
        Address2.clear();
        Address2.sendKeys("");
        log.info("Address2 entered successfully");
        Thread.sleep(1000);

        WebElement City=((ChromeDriver) driver).findElementByXPath("//label[text()='City']/following-sibling::input");
        City.clear();
        City.sendKeys("");
        log.info("City entered successfully");
        Thread.sleep(1000);

        WebElement State=((ChromeDriver) driver).findElementByXPath("//label[text()='State']/following-sibling::input");
        State.clear();
        State.sendKeys("");
        log.info("State entered successfully");
        Thread.sleep(1000);


        WebElement zip=((ChromeDriver) driver).findElementByXPath("//label[text()='Zip']/following-sibling::input");
        zip.clear();
        zip.sendKeys("");
        log.info("zip entered successfully");
        Thread.sleep(1000);

        WebElement Country=((ChromeDriver) driver).findElementByXPath("//label[text()='Country']/following-sibling::input");
        Country.clear();
        Country.sendKeys("");
        log.info("Country entered successfully");
        Thread.sleep(1000);

        WebElement NxtBtn=((ChromeDriver) driver).findElementByXPath("//span[text()='Next']//parent::button");
        NxtBtn.click();
        log.info("NxtBtn entered successfully");
        Thread.sleep(10000);

        WebElement EnableUserOn=((ChromeDriver) driver).findElementByXPath("//label[@class='ant-radio-button-wrapper']");
        EnableUserOn.click();
        log.info("Clicked enable user On");
        Thread.sleep(3000);

     /*   WebElement Phone=((ChromeDriver) driver).findElementByXPath("//label[text()='Phone']/following-sibling::input[@type='text' and @class='ant-input' and @placeholder='']");
        Phone.click();
        log.info("click happened");
        // Phone.clear();
        js.executeScript("arguments[0].value='"+"573458347547"+"';", Phone);
        // phone.sendKeys("12452345");
        log.info("User phone number entered successfully");
        Thread.sleep(1000);

        WebElement UserEmail=((ChromeDriver) driver).findElementByXPath("(//input[contains(@type,'text')])[23]");
        UserEmail.click();
        // UserEmail.clear();
        UserEmail.sendKeys("test");
        //js.executeScript("arguments[0].value='"+"hi"+"';", UserEmail);
        log.info("UserEmail entered successfully");
        String MoveBack = Keys.chord(Keys.SHIFT, Keys.TAB);
        UserEmail.sendKeys(MoveBack);
        driver.switchTo().activeElement().sendKeys("989898989");
        Thread.sleep(1000);*/

        WebElement password=((ChromeDriver) driver).findElementByXPath("(//input[contains(@type,'password')])[1]");
        password.clear();
        password.sendKeys("test");
        log.info("Password entered successfully");
        Thread.sleep(1000);

        WebElement Cnfpassword=((ChromeDriver) driver).findElementByXPath("(//input[contains(@type,'password')])[2]");
        Cnfpassword.clear();
        Cnfpassword.sendKeys("test");
        Cnfpassword.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);

        // Cnfpassword.sendKeys(Keys.RETURN);
        log.info("cnfpassword entered successfully");
        Thread.sleep(1000);

        WebElement choosedtimeformat=((ChromeDriver) driver).findElementByXPath("//li[@role='option' and text()='Asia/Kabul']");
        choosedtimeformat.click();


        js.executeScript("window.scrollBy(0,400)");

        WebElement SaveBtn=((ChromeDriver) driver).findElementByXPath("//span[text()='Save']/parent::button");
        SaveBtn.click();
        log.info("Save button clicked successfully");
        Thread.sleep(10000);


    }


    @AfterTest
    public void CloseBrowser()
    {

        driver.close();
    }
}
