package TestngProj;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AnnotationTest {
	@Test
	public void testCase1(){
		System.out.println("测试用例1被执行");
		
	}
	
	@Test
	public void testCase2(){
		System.out.println("测试用例2被执行");
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		System.out.println("在每个测试方法开始前执行");
	}
	
	@AfterMethod
	public void afterMethod(){
		System.out.println("在每个测试方法开始后执行");
	}
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("在每个测试类的第一个测试方法开始调用前执行");
	}
	
	@AfterClass
	public void afterCalss(){
		System.out.println("在每个测试的最后一个测试方法结束后执行");
		
	}
	@BeforeTest
	public void beforeTest(){
		System.out.println("在测试类中的test开始运行前执行");	
		
	}
	@AfterTest
	public void afterTest(){
		System.out.println("在测试类中的test结束后执行");
	}
	@BeforeSuite
	public void beforeSuite(){
		System.out.println("在当前测试集合中的所有测试程序开始之前执行");
		
	}
	@AfterSuite
	public void afterSuite(){
		System.out.println("在当前测试集合中的所有测试程序结束后执行");
	}
}
