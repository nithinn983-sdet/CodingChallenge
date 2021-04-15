package com.noon;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class scrappdata {
	static WebDriver driver;
	static Set<String> str=new TreeSet();
	static int count=0;
	static WebElement ele;
	static WebElement findpos;
	static JavascriptExecutor jse;
	
	public static void main(String[] args) throws Exception {
		
		 WebDriverManager.chromedriver().setup();
		int cn=1;
		// TODO Auto-generated method stub
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.noon.com/uae-en/");
		
		
		
		jse=(JavascriptExecutor)(driver);
		
		moveslowly(); /*If I execute this code without this function I will get only one set of elements
		so in order to avaoid it using this function, it will scroll the page from top to bottom*/

		//This list is going to have all the webelements which is having that navigation bar
		List<WebElement> elenext=driver.findElements(By.xpath("//div[contains(@class,'swiper-button-next custom-navigation')]"));
				
				System.out.println("---size----"+elenext.size());
				for(int i=0;i<=(elenext.size()-1);i++)
				{
					System.out.println("----Elementname----"+elenext.get(i));
					movetoelementscrolly(elenext.get(i)); //Using javascript executor to focuns on that navigation bar fr set of elements
					foralldisplayedelement(elenext.get(i));// if navigation bar is displayed it is going to print  the elements 
				}
				for(String prints:str)
				{
					count++;
					System.out.println(count+")"+prints);
				}

	}
	
	public static void foralldisplayedelement(WebElement elenext) throws Exception
	{
		
		ele=elenext;
		while(ele.isDisplayed())
		{
		printdisplayedelement(); // It is going to print all the  elements from the navigation bar if the elements is displayed.
		ele.click();
		
		
		}

	}
	
	public static void printdisplayedelement() throws Exception
	{
		List<WebElement> alldata=driver.findElements(By.xpath("//div[@class='swiper-wrapper']/div/descendant::div[@data-qa='product-name']"));
		
		for(WebElement ele:alldata)
		{
			if(ele.isDisplayed())
			{
				str.add(ele.getText());
			}
		}
		
	}
	

	
	public static void movetoelementscrolly(WebElement ele) throws Exception
	{
		
	
		findpos=ele;
	
		jse.executeScript("arguments[0].scrollIntoView(true);", findpos);
		
	
	}
	
	public static void moveslowly() throws InterruptedException
	{
		long lastHeight=((Number)jse.executeScript("return document.body.scrollHeight")).longValue();
		System.out.println("lastheight"+lastHeight);
		for(long i=0;i<=lastHeight;i+=500)
		{
			
			jse.executeScript("window.scrollTo(0,"+i+")");
			
		}
	}

}
