package com.qualitystream.testng;

import org.testng.annotations.Test;     //VIDEo 7: Selenium WevDriver cómo trabajr con testng
import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class TestNGExample { //todas las anotaciones para abajo son las que seleccioné cuando cree la clase
	
	WebDriver driver;
	By searchBoxLocator = By.id("search_query_top");
	By resultsLocator = By.cssSelector("span.heading-counter");
	
	
	@BeforeClass
	public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");  //lugar donde lo guardo desde Eclipse
			driver = new ChromeDriver();
			driver.manage().window().maximize();//recordar siempre que al crear un proyecto nuevo debo importarlo de forma manual
			driver.get("http://automationpractice.com/index.php");
	  }
  
    @Test
	  public void searchBlouses() {   
		  WebElement searchbox = driver.findElement(searchBoxLocator); //localizar la caja de búsqueda
		  searchbox.clear(); //limpiar la caja de búsqueda
		  searchbox.sendKeys("blouse");
		  searchbox.submit();
		  
		  WebDriverWait wait = new WebDriverWait(driver, 7);
		  wait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator)); //esto hace que el selenium espere hasta que el localizador resultlocator esté en la página
		  
		  System.out.println("This is the result number:" + driver.findElement(resultsLocator).getText()); //le concatenameos el results locator para q aparezca en la consola
		  
		  assertTrue(driver.findElement(resultsLocator).isDisplayed(), "The result number is not present ");
	  }

  @AfterClass
  public void afterClass() {
      //driver.close();
  }

}
