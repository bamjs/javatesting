package loginforms;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class login {

	public WebDriver driver;
	public String baseUrl = "http://35.154.32.162/login";
	public WebElement mobile;
	public WebElement password;
	public WebElement submit;
	public String desc;
	public LocalDateTime myDate;
	public DateTimeFormatter myDateObj;
	@BeforeMethod
	public void building_browser() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\java outs\\testing\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(500);
		myDate = LocalDateTime.now();
		myDateObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		String time = myDate.format(myDateObj);
		System.out.println(time);

	}

	@Test
	public void login_without_credentials() throws InterruptedException {
		desc ="login_without_credentials()";
		WebElement submit = driver.findElement(By.className("primary"));
		submit.click();
		Thread.sleep(500);
		WebElement toastMsg = driver.findElement(By.className("msg-list"));
		System.out.println(toastMsg.getText());
	}

	@Test
	public void login_without_password() throws InterruptedException {
		desc = "login_without_password";
		WebElement mobile = driver.findElement(By.name("mobile"));
		mobile.sendKeys("9876543210");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("");
		System.out.println(password.getText());
		WebElement submit = driver.findElement(By.className("primary"));
		submit.click();
		Thread.sleep(500);
		WebElement toastMsg = driver.findElement(By.className("msg-list"));
		System.out.println(toastMsg.getText());

	}
	@Test
	public void Login_with_wrong_password() throws InterruptedException {
		desc ="Login_with_wrong_password()";
		WebElement mobile = driver.findElement(By.name("mobile"));
		mobile.sendKeys("9876543210");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("1234567fd");
		System.out.println(password.getText());
		WebElement submit = driver.findElement(By.className("primary"));
		submit.click();
		Thread.sleep(500);
		WebElement toastMsg = driver.findElement(By.className("msg-list"));
		System.out.println(toastMsg.getText());
	}

	
	@Test
	public void login_with_correct_credentials() throws InterruptedException {
		desc="login_with_correct_credentials";
		WebElement mobile = driver.findElement(By.name("mobile"));
		mobile.sendKeys("9876543210");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		System.out.println(password.getText());
		WebElement submit = driver.findElement(By.className("primary"));
		submit.click();
		Thread.sleep(500);
//		WebElement toastMsg = driver.findElement(By.className("msg-list"));
//		System.out.println(toastMsg.getText());
	}
	@AfterMethod
	public void Closing_browser() throws InterruptedException, IOException{
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		ImageIO.write(screenshot.getImage(), "PNG", new File("\\java outs\\testing\\screenshots\\" + desc + ".png"));
		driver.close();
	}

}
