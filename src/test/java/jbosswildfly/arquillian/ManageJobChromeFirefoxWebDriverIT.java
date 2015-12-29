package jbosswildfly.arquillian;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ManageJobChromeFirefoxWebDriverIT {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testManageJobWithChrome() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"/eclipse/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		manageJob(driver);
		driver.quit();
	}

	@Test
	public void testManageJobWithFirefox() throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		manageJob(driver);
		driver.get("http://localhost:8080/jbosswildfly3/faces/io/entities/managePerson.xhtml");
		Thread.sleep(5000);
		driver.quit();
	}

	private void manageJob(WebDriver driver) throws InterruptedException {
		driver.get("http://localhost:8080/jbosswildfly3/faces/io/entities/manageJob.xhtml");
		Thread.sleep(5000);
		WebElement title = driver.findElement(By.id("manageJobForm:title"));
		title.sendKeys("Action Actor");
		WebElement submit = driver.findElement(By.id("manageJobForm:ajax"));
		submit.click();
		Thread.sleep(5000);

	}

}
