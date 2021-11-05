package loginforms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Videos_Portal {
	public WebDriver driver;
@Test
public void building_browser() throws InterruptedException {
	System.setProperty("webdriver.chrome.browser", "D:\\java outs\\testing\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://35.154.32.162/login");
	Thread.sleep(1000);
	driver.findElement(By.name("mobile")).sendKeys("9876543210");
	driver.findElement(By.name("password")).sendKeys("123456");
	System.out.println("entered password");
	WebElement submit = driver.findElement(By.className("primary"));
	submit.click();
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<WebElement> NavigationBar = driver.findElements(By.xpath("//nav/a"));
	for(WebElement links : NavigationBar) {
		
		Thread.sleep(800); 
		links.click();
	}
	
}
	
}
