package com.checkinLinks.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckLinkTests {
	
	WebDriver driver;
	ChekingLinksPage page; //creamos el objeto para usar la scipt base o clase ChekingLinksPage
	
	@Test
	public void chekingLinks() {
		assertTrue(page.chekingPageList(), "There are broken links"); //chekingPageList es un mètodo
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");  //lugar donde lo guardo desde Eclipse
		driver = new ChromeDriver();
		page = new ChekingLinksPage(driver); //creamos un objeto page
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/test/newtours/index.php");
		
	}

	@AfterClass
	public void afterClass() {
	  driver.close();
	}

}
