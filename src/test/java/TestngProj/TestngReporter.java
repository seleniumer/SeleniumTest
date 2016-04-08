package TestngProj;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.alibaba.seleniumer.element.BrowserTools;

public class TestngReporter {
	
	  String baseUrl = "http://www.baidu.com";	
	  public WebDriver driver;


	  @BeforeMethod
	  public void beforeMethod() {
		    driver = BrowserTools.startChrome();	  
	    }
	  
		@Test
		public void OpenBrowser(){
			System.out.println("浏览器打开方法被调用");
			Reporter.log("调用打开浏览器的方法");			
		}
		
		@Test
		public void visitUrl() throws InterruptedException{		
	        System.out.println("开始执行");
			driver.get(baseUrl);
			System.out.println("执行完成");
			driver.navigate().to("http://wwww.hao123.com");
			Thread.sleep(3000);
			driver.navigate().back();
			Thread.sleep(3000);
			driver.navigate().forward();
			driver.navigate().refresh();			
			System.out.println(driver.getTitle());
			driver.get(baseUrl);
			String pageSource=driver.getPageSource();
			System.out.println(pageSource);
			Assert.assertTrue(pageSource.contains("登录"));
			WebElement input = driver.findElement(By.id("kw"));
			input.sendKeys("软件@#^&*()_");
			Thread.sleep(3000);
			input.clear();
			input.sendKeys("自动化测试");
			
		}
		@Test
		public void operateList() throws InterruptedException{
			driver.navigate().to("http://localhost:8080/JSPStudy/15/login.jsp");
			Select list = new Select(driver.findElement(By.name("fruit")));
//			Assert.assertEquals("桃子", list.getFirstSelectedOption().getText());
			list.selectByIndex(2);
			Thread.sleep(3000);
			list.selectByValue("taozi4");
			Thread.sleep(3000);		
		}
		@Test
		public void captureScreenInCurrentWindow(){
			driver.get("http://www.baidu.com");
			File imgFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(imgFile,new File("D:\\webdriver\\web.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void executeJavaScript(){
			driver.get("http://www.baidu.com");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String title = (String) js.executeScript("return document.title");
			System.out.println(title);
		}

}
