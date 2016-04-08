package com.alibaba.seleniumer.element;

import java.sql.SQLException;  
import java.util.List;  
import org.openqa.selenium.By;  
import org.openqa.selenium.Dimension;  
import org.openqa.selenium.JavascriptExecutor;  
import org.openqa.selenium.NoSuchElementException;  
import org.openqa.selenium.Point;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebElement;  
import org.openqa.selenium.interactions.internal.Coordinates;  
import org.openqa.selenium.remote.RemoteWebElement;  
import org.openqa.selenium.support.ui.Select;  
  
public class JSWebElement {  
    private RemoteWebElement we = null;  
    private JavascriptExecutor jse = null;  
      
    public JSWebElement(){}  
      
    public JSWebElement(RemoteWebElement we){  
        this.we = we;  
    }  
      
    ///  
    ///通过元素ID定位元素  
    ///  
    public JSWebElement findElementById(String using) {  
        try {  
            return new JSWebElement((RemoteWebElement)we.findElementById(using));  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
      
    ///  
    ///通过元素CSS表达式定位元素  
    ///  
    public JSWebElement findElementByCssSelector(String using) {  
        try {  
            return new JSWebElement((RemoteWebElement)we.findElementByCssSelector(using));  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
  
    ///  
    ///通过元素Xpath表达式定位元素  
    ///   
    public JSWebElement findElementByXPath(String using) {  
        try {  
            return new JSWebElement((RemoteWebElement)we.findElementByXPath(using));  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
  
    ///  
    ///通过链接的文字定位元素  
    ///   
    public JSWebElement findElementByLinkText(String using) {  
        try {  
            return new JSWebElement((RemoteWebElement)we.findElementByLinkText(using));  
        }catch (NoSuchElementException e){  
            return new JSWebElement();  
        }  
    }  
  
    ///  
    ///通过元素DOM表达式定位元素  
    ///   
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
    ///判断元素是否存在  
    ///  
    public Boolean isExist(){  
        if (we != null){  
            return true;  
        }else{  
            return false;  
        }  
    }  
  
    ///  
    ///获取元素的HTML内容  
    ///  
    public String getHtml(){  
        return we.getAttribute("outerHTML");  
    }  
  
    ///  
    ///获取元素的文本内容  
    ///   
    public String getText(){  
        return we.getText();  
    }  
  
    ///  
    ///获取元素的value值  
    ///   
    public String getValue(){  
        return this.getAttribute("value");  
    }   
      
    ///  
    ///获取元素的特定属性值  
    ///  
    public String getAttribute(String name){  
        return we.getAttribute(name);  
    }   
          
    ///  
    ///向可输入元素发送内容，如：text、textarea、filefield等  
    ///  
    public void sendKeys(String string){  
        String old_bg = this.setBackground("yellow");  
        try {  
            Thread.sleep(800);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        we.sendKeys(string);  
        this.setBackground(old_bg);  
    }  
      
    ///  
    ///判断元素是否可用  
    ///  
    public boolean isEnabled(){  
        return we.isEnabled();  
    }  
      
    ///  
    ///判断元素是否可见  
    ///  
    public boolean isVisible(){  
        return we.isDisplayed();  
    }  
      
    ///  
    ///清空可编辑元素的内容。不可编辑元素次操作会抛异常  
    ///  
    public void clear(){  
        we.clear();  
    }  
      
    ///  
    ///对元素进行点击操作  
    ///  
    public void click(){  
        we.click();  
    }  
      
    ///  
    ///检查元素的特定属性值  
    ///  
    public void checkAttr(String attribute, JSWebUtils utils) throws SQLException, JSONException  
    {  
        String [] attributes=attribute.split("=", 2);  
        String actual = this.we.getAttribute(attributes[0]);  
        if (actual == null){ actual = "null"; }  
        utils.checkPointBase(actual,attributes[1]);  
    }  
      
    ///  
    ///获取元素的CSS值  
    ///  
    public String getCssValue(String name)  
    {  
        return we.getCssValue(name);  
    }  
      
    ///  
    ///判断元素是否被选中  
    ///  
    public boolean isSelected()  
    {  
        return we.isSelected();  
    }     
      
    ///  
    ///可选元素进行选中操作；如：select  
    ///  
    public void select(String by, String value) throws Exception  
    {  
        if (we.getTagName().equals("select")){  
            Select select = new Select(we);  
            if (by.equals("index")){  
                select.selectByIndex(Integer.parseInt(value));  
            }else if (by.equals("value")){  
                select.selectByValue(value);  
            }else if (by.equals("text")){  
                select.selectByVisibleText(value);                
            }  
        }else{  
            Exception e = new Exception("The element is not SELECT Object");  
            throw e;  
        }  
    }  
      
    ///  
    ///对可选中元素进行取消选择操作；如:SELECT in multiple type  
    ///  
    public void deSelect(String by, String...value) throws Exception  
    {  
        if (we.getTagName().equals("select")){  
            Select select = new Select(we);  
            if (by.equals("index")){  
                select.deselectByIndex(Integer.parseInt(value[0]));  
            }else if (by.equals("value")){  
                select.deselectByValue(value[0]);  
            }else if (by.equals("text")){  
                select.deselectByVisibleText(value[0]);  
            }else if (by.equals("*")){  
                select.deselectAll();  
            }  
        }else{  
            Exception e = new Exception("The element is not SELECT Object");  
            throw e;  
        }  
    }  
      
    ///  
    ///判断下拉框是否为多选  
    ///  
    public boolean isMultiple() throws Exception  
    {  
        if (we.getTagName().equals("select")){  
            Select select = new Select(we);  
            if (select.isMultiple()){  
                return true;  
            }else{  
                return false;  
            }  
        }else{  
            Exception e = new Exception("The element is not SELECT Object");  
            throw e;  
        }  
    }     
      
    ///  
    ///获取select的当前选中值  
    ///  
    public String getSelectedText() throws Exception  
    {  
        if (we.getTagName().equals("select")){  
            String text = "";  
            Select select = new Select(we);  
            List<WebElement> options = select.getAllSelectedOptions();  
            for (WebElement w : options){  
                text += w.getText() + "\r\n";  
            }  
            return text;  
        }else{  
            Exception e = new Exception("The element is not SELECT Object");  
            throw e;  
        }  
    }  
      
    ///  
    ///判断指定项是否存在  
    ///  
    public boolean isInclude(String name) throws Exception  
    {  
        if (we.getTagName().equals("select")){  
            Select select = new Select(we);  
            List<WebElement> options = select.getOptions();  
            for (WebElement w : options){  
                if (w.getText().equals(name)){  
                    return true;  
                }  
            }  
            return false;  
        }else{  
            Exception e = new Exception("The element is not SELECT Object");  
            throw e;  
        }  
    }  
      
    ///  
    ///获取元素的tagname  
    ///  
    public String getTagName(){  
        return we.getTagName();  
    }  
      
    ///  
    ///获取元素的id  
    ///  
    public String getId(){  
        return we.getId();  
    }  
      
    ///  
    ///获取元素的绝对位置  
    ///  
    public Point getLocation(){  
        return we.getLocation();  
    }  
      
    ///  
    ///获取元素的出现在屏幕可见区时的位置  
    ///  
    public Point getLocationOnScreenOnceScrolledIntoView(){  
        return we.getLocationOnScreenOnceScrolledIntoView();  
    }  
      
    ///  
    ///获取元素的坐标  
    ///   
    public Coordinates getCoordinates(){  
        return we.getCoordinates();  
    }  
      
    ///  
    ///获取元素的大小  
    ///  
    public Dimension getSize(){  
        return we.getSize();  
    }  
  
    ///  
    ///提交元素所在form的内容  
    ///   
    public void submit()  
    {  
        we.submit();  
    }  
      
    ///  
    ///勾选radio、checkbox  
    ///  
    public void check(String...values) throws Exception  
    {  
        if (we.getTagName().equals("input")){  
            if (we.getAttribute("type").equals("radio")){  
                WebDriver wd = we.getWrappedDriver();  
                List<WebElement> wl = wd.findElements(By.name(we.getAttribute("name")));  
                if (values[0].equals("index")){  
                    wl.get(Integer.parseInt(values[1])).click();  
                }else if (values[0].equals("value")){  
                    for (WebElement w : wl){  
                        if (w.getAttribute("value").equals(values[1])){  
                            w.click();  
                            break;  
                        }  
                    }  
                }  
            }else if (we.getAttribute("type").equals("checkbox")){  
                if (!we.isSelected()){  
                    we.click();  
                }     
            }else{  
                Exception e = new Exception("The element is not Radio or CheckBox Object");  
                throw e;                  
            }  
        }else{  
            Exception e = new Exception("The element is not INPUT Object");  
            throw e;  
        }  
    }  
      
    ///  
    ///取消勾选checkbox  
    ///  
    public void unCheck() throws Exception  
    {  
        if (we.getTagName().equals("input") && we.getAttribute("type").equals("checkbox")){  
                if (we.isSelected()){  
                    we.click();  
                }                 
        }else{  
            Exception e = new Exception("The element is not CheckBox Object");  
            throw e;  
        }  
    }  
      
    ///  
    ///checkbox、radio是否勾选  
    ///  
    public boolean isChecked(String...values) throws Exception  
    {  
        if (we.getTagName().equals("input")){  
            if (we.getAttribute("type").equals("radio")){  
                WebDriver wd = we.getWrappedDriver();  
                List<WebElement> wl = wd.findElements(By.name(we.getAttribute("name")));  
                if (values[0].equals("index")){  
                    return wl.get(Integer.parseInt(values[1])).isSelected();  
                }else if (values[0].equals("value")){  
                    for (WebElement w : wl){  
                        if (w.getAttribute("value").equals(values[1])){  
                            return w.isSelected();  
                        }  
                    }  
                }  
                return false;  
            }else if (we.getAttribute("type").equals("checkbox")){  
                return we.isSelected();  
            }else{  
                Exception e = new Exception("The element is not Radio or CheckBox Object");  
                throw e;                  
            }  
        }else{  
            Exception e = new Exception("The element is not INPUT Object");  
            throw e;  
        }         
    }  
  
    ///  
    ///把元素滚动到可视区  
    ///  
    public void scroll()  
    {  
        this.focus();  
    }  
      
    ///  
    ///高亮元素  
    ///  
    public void highLight() throws InterruptedException  
    {  
        this.focus();  
        JavascriptExecutor js = getJSE();  
        String old_style = we.getAttribute("style");  
        for (int i = 0; i < 3; i++) {               
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", this.we, "background-color: red; border: 2px solid red;" + old_style);   
            Thread.sleep(500);  
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", this.we, old_style);   
            Thread.sleep(500);  
        }  
    }  
  
    ///  
    ///触发元素的特定事件  
    ///  
    public void fireEvent(String event){  
        JavascriptExecutor js = getJSE();  
        js.executeScript(String.format("arguments[0].%s()", event), this.we);  
    }  
      
    ///  
    ///使元素获取焦点  
    ///  
    public void focus(){  
//      this.we.sendKeys("");  
        JavascriptExecutor js = getJSE();  
        js.executeScript("arguments[0].focus();", this.we);  
    }     
      
    ///  
    ///对元素执行JavaScript操作；即执行元素的dom操作  
    ///  
    public void executeJS(String commands){  
        JavascriptExecutor js = getJSE();     
        String[] comandArr = commands.split(";");  
        commands = "";  
        for (String comand : comandArr){  
            if (!comand.trim().equals("")){  
                commands += String.format("arguments[0].%s;", comand);  
            }  
        }  
        if (!commands.equals("")){  
            js.executeScript(commands, this.we);  
        }  
    }  
      
    ///  
    ///获取原始的RemoteWebElement对象  
    ///  
    public RemoteWebElement getNativeWebElement(){  
        return this.we;  
    }  
          
    private JavascriptExecutor getJSE(){  
        if (this.isExist()){  
            if (this.jse == null){  
                WebDriver wd = we.getWrappedDriver();  
                this.jse = (JavascriptExecutor) wd;               
            }         
        }  
        return jse;  
    }  
      
    private String setBackground(String color){  
        JavascriptExecutor js = getJSE();  
        String old_bg = we.getCssValue("background-color");  
        js.executeScript("arguments[0].style.background = arguments[1];", this.we, color);   
        return old_bg;  
    }         
      
}  