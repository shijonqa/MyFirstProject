import com.google.common.base.Function;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RegisterLogin {
    WebDriver driver;
    @BeforeTest
    public void LaunchUrl()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-frontend.revitsone.com/register");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
    @AfterTest
    public void CloseBrowser()
    {
        driver.quit();
    }

    @Test(dataProvider = "getRegisterData")
    public void RegisterUser(String firstname,String MiddleName,String LastName,String PhoneNumber,String email,String Pword,String CName) throws InterruptedException
    {
        Thread.sleep(5000);
        String title=driver.findElement(By.xpath("//div[@class='register-right']/h2")).getText();
        Assert.assertEquals(title,"Register");

        WebElement FName= driver.findElement(By.xpath("//label[text()='First Name']/following-sibling::input"));
        FName.clear();
        Thread.sleep(1000);
        FName.sendKeys(firstname);

        WebElement MName= driver.findElement(By.xpath("//label[text()='Middle Name']/following-sibling::input"));
        MName.clear();
        Thread.sleep(1000);
        MName.sendKeys(MiddleName);

        WebElement LName= driver.findElement(By.xpath("//label[text()='Last Name']/following-sibling::input"));
        Thread.sleep(1000);
        LName.clear();
        LName.sendKeys(LastName);

        WebElement PNumber= driver.findElement(By.xpath("//label[text()='Phone Number']/following-sibling::input"));
        Thread.sleep(1000);
        PNumber.clear();
        PNumber.sendKeys(PhoneNumber);

        WebElement mail= driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"));
        Thread.sleep(1000);
        mail.clear();
        mail.sendKeys(email);

        WebElement Password= driver.findElement(By.xpath("//label[text()='Password']/following-sibling::input"));
        Thread.sleep(1000);
        Password.clear();
        Password.sendKeys(Pword);

        WebElement ConPassword= driver.findElement(By.xpath("//label[text()='Confirm Password']/following-sibling::input"));
        Thread.sleep(1000);
        ConPassword.clear();
        ConPassword.sendKeys(Pword);

        WebElement CompanyName= driver.findElement(By.xpath("//label[text()='Company Name']/following-sibling::input"));
        Thread.sleep(1000);
        CompanyName.clear();
        CompanyName.sendKeys(CName);

        WebElement AcceptCheckBox= driver.findElement(By.xpath("//input[@type='checkbox' and @class = 'ant-checkbox-input']"));
        Thread.sleep(1000);
        AcceptCheckBox.click();

        Thread.sleep(1000);
        WebElement StartUsingRevitBtn= driver.findElement(By.xpath("//button[@type='button']"));
        StartUsingRevitBtn.click();

        Thread.sleep(3000);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, SECONDS).pollingEvery(1, SECONDS).ignoring(NoSuchElementException.class);


        WebElement Message = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@class='ant-message']/span/div"));
            }
        });
        System.out.println("Message displayed is "+Message.getText());

    }
    @DataProvider
    public Object[][] getRegisterData(){
        Object data[][] = ExcelUtils.getTestData("RegisterUser");
        return data;
    }
}
