package com.alibaba.seleniumer.element;

import java.io.File;  
import java.io.IOException;  
import java.net.URL;  
import java.util.HashMap;  
import java.util.Map;  
import java.util.Set;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

import org.apache.commons.io.FileUtils;  
import org.openqa.selenium.Alert;  
import org.openqa.selenium.Capabilities;  
import org.openqa.selenium.Cookie;  
import org.openqa.selenium.JavascriptExecutor;  
import org.openqa.selenium.NoSuchElementException;  
import org.openqa.selenium.OutputType;  
import org.openqa.selenium.TakesScreenshot;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;  
import org.openqa.selenium.remote.Augmenter;  
import org.openqa.selenium.remote.RemoteWebDriver;  
import org.openqa.selenium.remote.RemoteWebElement; 
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;

public class JSWebDriver {  
  
	private RemoteWebDriver wd = null;  
    private JavascriptExecutor jse = null;  
      
    public JSWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {  
        wd = new RemoteWebDriver(remoteAddress, desiredCapabilities);  
    }  
      
    ///  
    ///浏览器url导航  
    ///  
    public void get(String url){  
        wd.get(url);  
    }     
      
    ///  
    ///浏览器退出  
    ///  
    public void quit(){  
        wd.quit();  
    }  
  
    ///  
    ///浏览器后退  
    ///  
    public void back(){  
        wd.navigate().back();  
    }  
  
    ///  
    ///浏览器前进  
    ///  
    public void forward(){  
        wd.navigate().forward();  
    }  
      
    ///  
    ///浏览器刷新  
    ///  
    public void refresh(){  
        wd.navigate().refresh();  
    }  
      
    ///  
    ///切换到新浏览器窗口；按照title、url、index；支持正则匹配  
    ///  
    public void switchToWindow(String by, String value, String...match) throws Exception{  
        String currenthandle = wd.getWindowHandle();  
        Set<String> handles = wd.getWindowHandles();  
        int currentIndex = -1;  
        String searchString = "";  
        for(String handle : handles){  
            currentIndex += 1;  
            if(handle.equals(currenthandle)){  
                continue;  
            }else{                
                wd.switchTo().window(handle);  
                if (match.length == 1 && match[0].equals("regex")){                   
                    if (by.equals("title")){  
                        searchString = wd.getTitle();  
                    }else if (by.equals("url")){  
                        searchString = wd.getCurrentUrl();  
                    }     
                    Pattern pattern = Pattern.compile(value);  
                    Matcher matcher = pattern.matcher(searchString);  
                    if(matcher.find()){  
                        return;  
                    }  
                }else{  
                    if (by.equals("title")){  
                        searchString = wd.getTitle();  
                    }else if (by.equals("url")){  
                        searchString = wd.getCurrentUrl();  
                    }else if (by.equals("index")){  
                        searchString = Integer.toString(currentIndex);  
                    }  
                    if(searchString.equals(value)){  
                        return;  
                    }  
                }  
            }  
        }  
        Exception e = new Exception("Swtich Window Failed, Please Make Sure The Locator Was Right.");  
        throw e;  
    }  
      
    ///  
    ///JS弹框确认  
    ///  
    public void clickAlertSure(){  
        Alert alert = wd.switchTo().alert();  
        alert.accept();  
    }  
      
    ///  
    ///JS弹框取消  
    ///  
    public void clickAlertDismiss()  
    {  
        Alert alert = wd.switchTo().alert();  
        alert.dismiss();  
    }  
      
    ///  
    ///设置prompt弹框内容  
    ///  
    public void setPromptMessage(String parameter){  
        Alert alert = wd.switchTo().alert();  
        alert.sendKeys(parameter);  
    }  
      
    ///  
    ///获取JS弹框内容  
    ///  
    public String getPromptMessage(){  
        Alert alert = wd.switchTo().alert();  
        return alert.getText();  
    }     
      
    ///  
    ///切换到Frame窗口；先定位到iframe元素  
    ///  
    public void switchToFrame(JSWebElement jselement){        
        wd.switchTo().frame(jselement.getNativeWebElement());  
    }  
  
    ///  
    ///执行JS脚本  
    ///  
    public void executeScript(String parameter){  
        JavascriptExecutor js = getJSE();  
        js.executeScript(parameter);  
    }  
  
    ///  
    ///获取指定cookie  
    ///  
    public String getCookie(String name){  
        Cookie cookie=wd.manage().getCookieNamed(name);  
        if (cookie == null){ return "null"; }  
        return cookie.getValue();  
    }  
      
    ///  
    ///获取所有cookie  
    ///  
    public Map<String, String> getCookies(){  
        Map<String, String> newCookies = new HashMap<String, String>();  
        Set<Cookie> cookies= wd.manage().getCookies();  
        for (Cookie cookie : cookies){  
            newCookies.put(cookie.getName(), cookie.getValue());  
        }  
        return newCookies;  
    }  
      
    ///  
    ///截取屏幕  
    ///  
    public void getScreen(String filepath){  
        WebDriver augmentedDriver = new Augmenter().augment(this.wd);   
        TakesScreenshot ts = (TakesScreenshot) augmentedDriver;  
        File screenShotFile = ts.getScreenshotAs(OutputType.FILE);   
        try {   
            FileUtils.copyFile (screenShotFile, new File(filepath));   
        }catch (IOException e){   
            e.printStackTrace();   
        }   
    }  
  
    ///  
    ///获取title  
    ///  
    public String getTitle(){  
        return wd.getTitle();  
    }     
  
    ///  
    ///获取url  
    ///  
    public String getUrl(){  
        return wd.getCurrentUrl();  
    }  
      
    ///  
    ///获取HTML源码  
    ///  
    public String getSource(){  
        try {  
            Thread.sleep(500);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return wd.getPageSource();  
    }  
      
    ///  
    ///滚动页面到指定位置  
    ///  
    public void scroll(String x, String y){  
        if (x.equals("left")){  
            x = "0";  
        }else if (x.equals("right")){  
            x = "document.body.scrollWidth";  
        }else if (x.equals("middle")){  
            x = "document.body.scrollWidth/2";  
        }  
        if (y.equals("top")){  
            y = "0";  
        }else if (y.equals("buttom")){  
            y = "document.body.scrollHeight";  
        }else if (y.equals("middle")){  
            y = "document.body.scrollHeight/2";  
        }  
        this.executeScript(String.format("scroll(%s,%s);", x, y));  
    }  
      
    ///  
    ///最大化浏览器  
    ///  
    public void maximize(){  
        wd.manage().window().maximize();  
    }  
      
    public JSWebElement findElementById(String using) {  
        try {  
            return new JSWebElement((RemoteWebElement)wd.findElementById(using));  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
      
    public JSWebElement findElementByCssSelector(String using) {  
        try {  
            return new JSWebElement((RemoteWebElement)wd.findElementByCssSelector(using));  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
      
    public JSWebElement findElementByXPath(String using) {  
        try {  
            return new JSWebElement((RemoteWebElement)wd.findElementByXPath(using));  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
  
    public JSWebElement findElementByLinkText(String using) {  
        try {  
            return new JSWebElement((RemoteWebElement)wd.findElementByLinkText(using));  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
      
    public JSWebElement findElementByDom(String using) {  
        try {  
            JavascriptExecutor js = this.getJSE();  
            WebElement we = (WebElement)js.executeScript(String.format("return %s", using));              
            return new JSWebElement((RemoteWebElement)we);  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
      
    ///  
    ///获取原生的RemoteWebdriver对象  
    ///  
    public RemoteWebDriver getNativeWebDriver(){  
        return this.wd;  
    }  
      
    private JavascriptExecutor getJSE(){  
        if (this.jse == null){  
            this.jse = (JavascriptExecutor) this.wd;                  
        }         
        return jse;  
    }  
}  