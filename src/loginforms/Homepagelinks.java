package loginforms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Homepagelinks {
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
	public String NavigationLinks;
	public String SideLinks;
	public int links = 0;
	public sideHrefLinks[] banksLinks;
	public sideHrefLinks[] manageLinks;
	public sideHrefLinks[] testSeriesLinks;
	public sideHrefLinks[] qBanksLinks;
	public sideHrefLinks[] videosLinks;
	public sideHrefLinks[] portalsLinks;
	public int SideLinksStatus;

	public List<WebElement> asideLinks;
	public String[] anchorLinks;

	class navbar {
		public String LinkTo;
		public String href;

		navbar(String LinkTo, String href) {
			this.LinkTo = LinkTo;
			this.href = href;
		}
	}

	class sideHrefLinks {
		public String href;
		public int sideLength;

		sideHrefLinks(String href, int sideLength) {
			this.href = href;
			this.sideLength = sideLength;
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
		Thread.sleep(1000);
	}

	@Test
	public void Getting_All_AnchorText_in_navbar() throws InterruptedException, IOException {

		navigationBar = new navbar[6];
		int SideLinksStatus = 1;
		// body > application-root > div > application-title > div > header > div >
		// div:nth-child(2) > nav > a
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
					new File("\\java outs\\testing\\screenshots\\navbar" + Desc + ".png"));
			Thread.sleep(500);
			asideLinks = driver.findElements(By.xpath(
					"/html/body/application-root/div/application-layout/div/div/application-aside-menu/aside/a"));
			System.out.println(asideLinks);
			int links = navigationBar.length;
			System.out.println(links);
			int length = asideLinks.size();
			int index = 0;
			switch (SideLinksStatus) {
			case 1:
				banksLinks = new sideHrefLinks[length];
				for (WebElement sidea : asideLinks) {

					System.out.println(index);
					banksLinks[index] = new sideHrefLinks(sidea.getAttribute("href"), length);
					index++;
					System.out.println(sidea.getAttribute("href"));
				}
				break;
			case 2:
				manageLinks = new sideHrefLinks[length];
				for (WebElement sidea : asideLinks) {

					System.out.println(index);
					manageLinks[index] = new sideHrefLinks(sidea.getAttribute("href"), length);
					index++;
					System.out.println(sidea.getAttribute("href"));
				}
				System.out.println("Has reached MAnage Links");
				break;
			case 3:
				testSeriesLinks = new sideHrefLinks[length];
				System.out.println("Has reached test Links");
				for (WebElement sidea : asideLinks) {

					System.out.println(index);
					testSeriesLinks[index] = new sideHrefLinks(sidea.getAttribute("href"), length);
					index++;
					System.out.println(sidea.getAttribute("href"));
				}
				break;
			case 4:
				qBanksLinks = new sideHrefLinks[length];
				System.out.println("Has reached qbank Links");
				for (WebElement sidea : asideLinks) {

					System.out.println(index);
					qBanksLinks[index] = new sideHrefLinks(sidea.getAttribute("href"), length);
					index++;
					System.out.println(sidea.getAttribute("href"));
				}
				break;
			case 5:
				System.out.println("case 5 switch working ");
				videosLinks = new sideHrefLinks[length];
				System.out.println("Has reached video Links");
				for (WebElement sidea : asideLinks) {

					System.out.println(index);
					videosLinks[index] = new sideHrefLinks(sidea.getAttribute("href"), length);
					index++;
					System.out.println(sidea.getAttribute("href"));
					System.out.println("video" + SideLinksStatus);
				}
				break;
			case 6:
				System.out.println("case 6 switch working ");
				portalsLinks = new sideHrefLinks[length];
				System.out.println("Has reached portal Links");
				for (WebElement sidea : asideLinks) {

					System.out.println(index);
					portalsLinks[index] = new sideHrefLinks(sidea.getAttribute("href"), length);
					index++;
					System.out.println(sidea.getAttribute("href"));
				}
				break;
			}
			SideLinksStatus++;

		}
	}

	@Test
	public void getiingBanksLinks() throws InterruptedException {
		Desc ="getiingBanksLinks";
		for (int out = 0; out < banksLinks.length; out++) {
			Thread.sleep(2000);
			String linksside = banksLinks[out].href;
			driver.navigate().to(linksside);
		}
	}

	@Test
	public void gettingAllManageLinks() {
		Desc ="gettingAllManageLinks";
		for (int out = 0; out < manageLinks.length; out++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String linksside = manageLinks[out].href;
			driver.navigate().to(linksside);
			System.out.println(manageLinks[out].href);
		}
	}

	@Test
	public void getting_All_Test_Series_Links() {
		Desc ="getting_All_Test_Series_Links";
		for (int out = 0; out < testSeriesLinks.length; out++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String linksside = testSeriesLinks[out].href;
			driver.navigate().to(linksside);
			System.out.println(testSeriesLinks[out].href);
		}

	}

	@Test
	public void getting_All_Qbanks_Links() {
		Desc ="getting_All_Qbanks_Links";
		for (int out = 0; out < qBanksLinks.length; out++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String linksside = qBanksLinks[out].href;
			driver.navigate().to(linksside);
			System.out.println(qBanksLinks[out].href);
		}

	}

	@Test
	public void getting_All_Videos_Links() {
		Desc="getting_All_Videos_Links";
		for (int out = 0; out < videosLinks.length; out++) {
			System.out.println(out);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String linksside = videosLinks[out].href;
			driver.navigate().to(linksside);
			System.out.println(videosLinks[out].href);
			System.out.println(out);
		}

	}

	@Test
	public void getting_All_Portals_Links() {
		Desc ="getting_All_Portals_Links";
		for (int out = 0; out <portalsLinks.length; out++) {
			System.out.println(out);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String linksside = portalsLinks[out].href;
			driver.navigate().to(linksside);
			System.out.println(portalsLinks[out].href);
			System.out.println(out);
		}

	}

	@AfterMethod
	public void Log_out() throws InterruptedException, IOException {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(
				"body > application-root > div > application-title > div > header > div > div.child.text-right.ng-star-inserted > span"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#mat-menu-panel-0 > div > button:nth-child(2)")).click();
		driver.close();
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "PNG",
				new File("\\java outs\\testing\\screenshots\\navbar" + Desc + ".png"));
	}
}
