package com.alibaba.seleniumer.element;

import java.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserTools {

	public WebDriver driver;
	//驱动文件的位置需要根据自己的存放位置决定
	public static String WebDriverFilePath="D:\\webdriver\\";
	
	private BrowserTools(WebDriver driver) {
		this.driver = driver;
	}

	public static WebDriver startChrome() {
		System.setProperty("webdriver.chrome.driver",
				WebDriverFilePath+"chromedriver.exe");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability("chrome.switches", Arrays.asList("--incognito"));
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		cap.setCapability("chrome.binary",
				"src/ucBrowserDrivers/chromedriver.exe");
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(cap);
		driver.manage().window().maximize();
		new BrowserTools(driver);
		return driver;
	}

	public static WebDriver startFireFoxByDefault() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		new BrowserTools(driver);
		return driver;
	}

	public static WebDriver startLocalFirefox(String path) {
		System.setProperty("webdriver.firefox.bin", path);
		ProfilesIni pi = new ProfilesIni();
		FirefoxProfile profile = pi.getProfile("default");
		WebDriver driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
		new BrowserTools(driver);
		return driver;
	}

	public static WebDriver startIE() {
		System.setProperty("webdriver.ie.driver",
				WebDriverFilePath+"IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		new BrowserTools(driver);
		return driver;
	}
}