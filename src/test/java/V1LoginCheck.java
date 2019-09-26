import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

public class V1LoginCheck {
    WebDriver driver;
    ExcelWrite excelWrite;
//    @BeforeTest
//    public void setup()
//    {
//        System.setProperty("webdriver.chrome.driver", "/home/shijon/Downloads/chromedriver_linux64(1)/chromedriver");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://gps.thinture.com");
//        driver.manage().deleteAllCookies();
//
//    }
    @Test (dataProvider="getlogin")
    public void login(String Uname,String Passwrd) throws InterruptedException
    {
        String arr[]= new String [4];
        System.setProperty("webdriver.chrome.driver", "/home/shijon/Downloads/chromedriver_linux64(1)/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://gps.thinture.com");
        driver.manage().deleteAllCookies();

       WebElement UserName = driver.findElement(By.id("UserName"));
        UserName.clear();

        UserName.sendKeys(Uname);
        arr[0]=Uname;
        WebElement Password =driver.findElement(By.id("Password"));
        Password.clear();
        Password.sendKeys(Passwrd);
        arr[1]=Passwrd;
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        try
        {
            driver.findElement(By.xpath("//li[text()='Your Account has been Disabled']"));
            System.out.println("Account is disable " +Uname);
            arr[2]="Not Active";
            arr[3]="Department not available";

        }
        catch (Exception e)
        {
            arr[2]="Active";
            System.out.println("Account disabled warning message doesn't appears");
            Thread.sleep(2000);
            try {
                if(driver.getTitle().contains("Home | Thinture"))
                {
                    Thread.sleep(2000);
                WebElement menu=  driver.findElement(By.id("pushmenu-button"));
                menu.click();
                  System.out.println("menu button clicked");
                  try {
                      Thread.sleep(2000);
                     WebElement Managmnt= driver.findElement(By.xpath("//a[text()='Management ']"));
                      Managmnt.click();
                      System.out.println("Management button clicked");
                      Thread.sleep(2000);
                      driver.findElement(By.xpath("//a[@data-original-title='Department']")).click();
                      System.out.println("Department button clicked");
                      arr[3]="Department available";
                  }
                  catch (Exception ee)
                  {
                      arr[3]="Department not available";
                      System.out.println("Login performed.");
                  }
                }
                else
                {
                    arr[3]="Department not available";
                }
            }
            catch (Exception er)
            {
                arr[3]="Department not available";
                System.out.println("login doesn't perform");
            }
        }finally {
            WriteData(arr);
            System.out.println(arr[3]);
            Thread.sleep(2000);
            driver.quit();
        }
    }
    @DataProvider
    public Object[][] getlogin(){
        Object data[][] = ExcelUtils.getTestData("V1Login");
        return data;
    }

    public void WriteData(String err[])
    {


        try {

            FileInputStream file = new FileInputStream(new File("/home/shijon/Documents/Test.xls"));

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet("Sheet1");
//                                sheet.createRow(0).createCell(0).setCellValue("Hiiiii");

            // Cell cell = null;

            //Update the value of cell

//            for (String s :err){
//                if (s!=null){
//                    sheet.createRow(0).createCell(1).setCellValue(s);
//
//                }
//
//            }
            int i=1+sheet.getLastRowNum();
            System.out.println("last row +1 "+i);
            for(int j=0;j<err.length;j++)
            {

                sheet.createRow(i).createCell(j).setCellValue(err[j]);
                System.out.println(" read excel "+err[j]);

            }






            file.close();

            FileOutputStream outFile =new FileOutputStream(new File("/home/shijon/Documents/Test.xls"));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
