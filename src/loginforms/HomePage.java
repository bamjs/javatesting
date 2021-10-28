package loginforms;

import java.lang.reflect.Array;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePage {
	public WebDriver driver;
	public String baseUrl = "http://35.154.32.162/login";
	public WebElement mobile;
	public WebElement password;
	public WebElement submit;
	public String desc;
	public navbar[] navigationBar;
	public int i=0;
	
	
	class navbar {
		public String LinkTo;
		public String href;
		navbar(String LinkTo, String href)
		{
			this.LinkTo =LinkTo;
			this.href=href;
			
		}
	}

	@BeforeMethod
	public void building_browser() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\java outs\\testing\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		desc = "login_with_correct_credentials";
		WebElement mobile = driver.findElement(By.name("mobile"));
		mobile.sendKeys("9876543210");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		System.out.println(password.getText());
		WebElement submit = driver.findElement(By.className("primary"));
		submit.click();
		Thread.sleep(500);

	}

	@Test
	public void Getting_All_AnchorText_in_navbar() throws InterruptedException {
		navigationBar = new navbar[10];
		List<WebElement> allLinks = driver.findElements(By.xpath("//nav/a"));
		for (WebElement link : allLinks) {
			System.out.println(link.getText() + "---" + link.getAttribute("href"));
			
			navigationBar[i] = new navbar(link.getText(),link.getAttribute("href"));
			i++;
			Thread.sleep(1000);
			
			
			
		}
		
	}
//	@Test
//	public void 
	
	
	
}
