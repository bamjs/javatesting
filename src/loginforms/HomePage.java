package loginforms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class HomePage {
	public WebDriver driver;
	public String baseUrl = "http://35.154.32.162/login";
	public WebElement mobile;
	public WebElement password;
	public WebElement submit;
	public String desc;
	public navbar[] navigationBar;
	public int i = 0;
	public String Desc;
	public PrintWriter print;
	public int j = 0;
	public DateTimeFormatter myFormat;
	public LocalDateTime myDate;
	public String mydate;
	public JavascriptExecutor js;
	public sideBars[] sLinks;

	class navbar {
		public String LinkTo;
		public String href;

		navbar(String LinkTo, String href) {
			this.LinkTo = LinkTo;
			this.href = href;

		}
	}

	class sideBars {
		public Iterator<WebElement> side_menu;

		sideBars(Iterator<WebElement> sideLinks) {
			this.side_menu = sideLinks;
		}
	}
	

	@BeforeMethod
	public void building_browser() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "D:\\java outs\\testing\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		myDate = LocalDateTime.now();
		myFormat = DateTimeFormatter.ofPattern("dd-MM-yy:HH-mm-ss");
		mydate = myDate.format(myFormat);
		System.out.println(mydate);
		Thread.sleep(1000);
		// creating a file
		File file = new File("output.txt");
		// creating writer
		FileWriter writer = new FileWriter(file);
		// printing content;
		print = new PrintWriter(writer);
		desc = "login_with_correct_credentials";
		System.out.println(desc);
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
	public void Getting_All_AnchorText_in_navbar() throws InterruptedException, IOException {

		navigationBar = new navbar[6];
		List<WebElement> allLinks = driver.findElements(By.xpath("//nav/a"));
		for (WebElement link : allLinks) {
			Desc = link.getText();
			System.out.println(link.getText());
			link.click();
			System.out.println(Desc + "=" + link.getAttribute("href"));
			Thread.sleep(500);
			navigationBar[i] = new navbar(link.getText(), link.getAttribute("href"));
			i++;
			Thread.sleep(1000);
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);

			ImageIO.write(screenshot.getImage(), "PNG",
					new File("\\java outs\\testing\\screenshots\\navbar" + Desc + mydate + ".png"));
			Thread.sleep(500);
			List<WebElement> asideLinks = driver.findElements(By.xpath(
					"/html/body/application-root/div/application-layout/div/div/application-aside-menu/aside/a"));
			System.out.println(asideLinks);
			Iterator<WebElement> sideLinks = asideLinks.iterator();
			System.out.println(sideLinks.next());
//			sLinks = new sideBars[20];
			sLinks =new sideBars[20];
			int length = asideLinks.size();
			for (int j = 0; j < length; j++) {
				sLinks[j]= new sideBars(sideLinks);
				
//				for (WebElement sideL : asideLinks) {
////					sLinks[j]=new sideBars(sideLinks);
//					
//			}
			}

		}
		System.out.println(sLinks);
	
		System.out.println(navigationBar.length);
		for (navbar links : navigationBar) {

			System.out.println(links.href);
			System.out.println(links.LinkTo);

			print.close();
		}

		driver.close();
	}

}
