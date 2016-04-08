package TestngProj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.alibaba.seleniumer.element.BrowserTools;


public class ClassTestDemo {
	
	
	public String baseUrl = "http://www.baidu.com";	
	public WebDriver driver;
	public BrowserTools br;
	  

	@BeforeMethod
	public void beforeMethod() {
//		System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");
//		DesiredCapabilities cap = DesiredCapabilities.chrome();
//		cap.setCapability("chrome.switches", Arrays.asList("--incognito"));
//		ChromeOptions options =new ChromeOptions();
//		options.addArguments("--test-type");
//		cap.setCapability("chrome.binary", "src/ucBrowserDrivers/chromedriver.exe");
//		cap.setCapability(ChromeOptions.CAPABILITY, options);
//	    driver = new ChromeDriver(cap);
//	    driver.manage().window().maximize();
	  } 


	@Test
	public void visitUrl() throws InterruptedException{
		  driver.get(baseUrl);
		  WebElement loginbutton = driver.findElement(By.xpath("//*[@id='u1']/a[7]"));
		  loginbutton.click();		  
		  Thread.sleep(3000);
		  if(driver.findElement(By.id("TANGRAM__PSP_8__userName_clearbtn")).isDisplayed()){
		  driver.findElement(By.id("TANGRAM__PSP_8__userName_clearbtn")).click();
		  }
		  driver.findElement(By.name("userName")).sendKeys("huayuyixin@163.com");
		  driver.findElement(By.id("TANGRAM__PSP_8__password")).sendKeys("yixin007*");
		  driver.findElement(By.id("TANGRAM__PSP_8__memberPass")).click();
		  driver.findElement(By.id("TANGRAM__PSP_8__submit")).click();
	
	}	  

}