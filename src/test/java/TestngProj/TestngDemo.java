package TestngProj;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestngDemo {
	
  public WebDriver driver;
  String url = "http://www.baidu.com";
  
  
  @BeforeMethod
  public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability("chrome.switches", Arrays.asList("--incognito"));
		ChromeOptions options =new ChromeOptions();
		options.addArguments("--test-type");
		cap.setCapability("chrome.binary", "src/ucBrowserDrivers/chromedriver.exe");
		cap.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(cap);
        driver.manage().window().maximize();
        driver.get(url);
  } 
  
  @Test
  public void f() {	  
	  driver.findElement(By.id("kw")).sendKeys("自动化测试");
	  driver.findElement(By.id("su")).click();
  }
  
  @Test
  public void loginTest() throws InterruptedException{
	  driver.findElement(By.xpath("//*[@id='u1']/a[7]")).click();
	  Thread.sleep(3000);
	  if(driver.findElement(By.id("TANGRAM__PSP_8__userName_clearbtn")).isDisplayed()){
	  driver.findElement(By.id("TANGRAM__PSP_8__userName_clearbtn")).click();
	  }
	  driver.findElement(By.name("userName")).sendKeys("huayuyixin@163.com");
	  driver.findElement(By.id("TANGRAM__PSP_8__password")).sendKeys("yixin007*");
	  driver.findElement(By.id("TANGRAM__PSP_8__memberPass")).click();
	  driver.findElement(By.id("TANGRAM__PSP_8__submit")).click();	  
  }


  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
