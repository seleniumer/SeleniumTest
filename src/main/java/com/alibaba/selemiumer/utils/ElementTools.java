package com.alibaba.selemiumer.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class ElementTools implements WebElement {
	
	public WebDriver driver;
	public WebElement element;
	public ElementTools(WebElement element)
	{
		
	}
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear() {
		// TODO Auto-generated method stub
		element.clear();
	}

	public void click() {
		// TODO Auto-generated method stub
		element.click();
		System.out.println("控件的点击");
	}

	public WebElement findElement(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCssValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	public void sendKeys(CharSequence... arg0) {
		// TODO Auto-generated method stub

	}

	public void submit() {
		// TODO Auto-generated method stub

	}

	public void jsclick(){
		try {						
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();");		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
