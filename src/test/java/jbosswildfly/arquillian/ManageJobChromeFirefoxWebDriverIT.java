package jbosswildfly.arquillian;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManageJobChromeFirefoxWebDriverIT {

	private static final long DEFAULT_PAUSE = 3000L;
	private static final String MANAGE_JOBS_URL = "http://localhost:8080/jbosswildfly3/faces/io/entities/manageJob.xhtml";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testManageJobWithChrome() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"/eclipse/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		addJobs(driver);
		Thread.sleep(DEFAULT_PAUSE);
		removeJobs(driver);
		Thread.sleep(DEFAULT_PAUSE);
		driver.get("http://localhost:8080/jbosswildfly3/faces/io/entities/managePerson.xhtml");
		Thread.sleep(DEFAULT_PAUSE);
		driver.quit();
	}

	@Test
	public void testManageJobWithFirefox() throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		addJobs(driver);
		Thread.sleep(DEFAULT_PAUSE);
		removeJobs(driver);
		Thread.sleep(DEFAULT_PAUSE);
		driver.get("http://localhost:8080/jbosswildfly3/faces/io/entities/managePerson.xhtml");
		Thread.sleep(DEFAULT_PAUSE);
		driver.quit();
	}

	private void addJobs(WebDriver driver) throws InterruptedException {
		String[] jobs = { "Director", "One who directs", "99800", "Actor",
				"One who acts", "82000", "Key Grip", "One who grips the key",
				"23000", "Best Boy", "The best boy", "13000",
				"Assistant to Director", "The Director's assistant", "29000",
				"Effects Artist", "The artist of effects", "42000" };
		driver.get(MANAGE_JOBS_URL);
		for (int i = 0; i < jobs.length - 2; i += 3) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			WebElement title = wait
					.until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver driver) {
							return driver.findElement(By
									.id("manageJobForm:title"));
						}
					});
			WebElement description = wait
					.until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver driver) {
							return driver.findElement(By
									.id("manageJobForm:description"));
						}
					});
			WebElement salary = wait
					.until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver driver) {
							return driver.findElement(By
									.id("manageJobForm:salary"));
						}
					});
			WebElement save = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.id("manageJobForm:save"));
				}
			});
			title.clear();
			title.sendKeys(jobs[i]);
			description.clear();
			description.sendKeys(jobs[i + 1]);
			salary.clear();
			salary.sendKeys(jobs[i + 2]);
			save.click();
			Thread.sleep(500);
			waitUntilAjaxRequestCompletes(driver);
		}
	}

	private void removeJobs(WebDriver driver) throws InterruptedException {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(40, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		driver.get(MANAGE_JOBS_URL);
		System.out.println("ok");
		for (int i = 0; i < 6; i++) {
			WebElement tr = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By
							.xpath("//tbody[@id='manageJobForm:jobTable_data']/tr/td"));
				}
			});
			tr.click();
			System.out.println("ok");
			WebElement viewBtn = wait
					.until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver driver) {
							return driver.findElement(By
									.id("manageJobForm:jobTable:j_idt36"));
						}
					});
			viewBtn.click();
			System.out.println("ok");
			WebElement delBtn = wait
					.until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver driver) {
							return driver.findElement(By
									.id("manageJobForm:delete"));
						}
					});
			delBtn.click();
			System.out.println("ok");
			Thread.sleep(500);
			waitUntilAjaxRequestCompletes(driver);
		}
	}

	private static final int DEFAULT_SLEEP_TIME_IN_SECONDS = 2;
	private static final int DEFAULT_TIMEOUT_IN_SECONDS = 30;
	private static final String JQUERY_ACTIVE_CONNECTIONS_QUERY = "return $.active == 0;";

	protected void waitUntilAjaxRequestCompletes(WebDriver driver) {
		new FluentWait<WebDriver>(driver)
				.withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						JavascriptExecutor jsExec = (JavascriptExecutor) d;
						return (Boolean) jsExec
								.executeScript(JQUERY_ACTIVE_CONNECTIONS_QUERY);
					}
				});
	}

	private boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected void waitUntilElementGetsValue(WebDriver driver,
			final String elementId, final String value) {
		new FluentWait<WebDriver>(driver)
				.withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver wd) {
						WebElement element = wd.findElement(By.id(elementId));
						return element.getText().equals(value);
					}
				});
	}

	/**
	 * Use when element is already precisely on the page. Throws
	 * NoSuchElementException when element is not found
	 * 
	 * @param elementId
	 * @param value
	 */
	protected void waitUntilElementExistsAndGetsValue(WebDriver driver,
			final String elementId, final String value) {
		new FluentWait<WebDriver>(driver)
				.withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver wd) {
						WebElement element = wd.findElement(By.id(elementId));
						return element.getText().equals(value);
					}
				});
	}
}
