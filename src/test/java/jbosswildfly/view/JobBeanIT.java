package jbosswildfly.view;

import java.io.File;
import java.net.URL;

import jbosswildfly.model.Job;
import jbosswildfly.repository.JobRepo;
import jbosswildfly.view.JobBean;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@RunWith(Arquillian.class)
public class JobBeanIT {

	private static final String WEBAPP_SRC = "src/main/webapp/io/entities";

	private static final String MANAGE_JOB = "manageJob.jsf";

	@Inject
	private JobBean jobBean;

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		File[] file = Maven.resolver().loadPomFromFile("pom.xml")
				.importRuntimeDependencies().resolve().withTransitivity()
				.asFile();
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class)
				.addClass(JobBean.class)
				.addClass(JobRepo.class)
				.addClass(Job.class)
				.addAsLibraries(file)
				.addAsWebResource(new File(WEBAPP_SRC, "manageJob.xhtml"))
				.addAsWebInfResource("META-INF/persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(
						new StringAsset("<faces-config version=\"2.0\"/>"),
						"faces-config.xml");
		System.out.println(archive.toString(true));
		return archive;
	}

	@Drone
	private WebDriver browser;

	@ArquillianResource
	private URL deploymentUrl;
	
	@FindBy(id = "manageJobForm:title")
	private WebElement title;
	
	@FindBy(id = "manageJobForm:description")
	private WebElement description;
	
	@FindBy(id = "manageJobForm:salary")
	private WebElement salary;
	
	@FindBy(css = "button[type=submit]")
	private WebElement submit;

	@Test
	public void testFoo00() throws InterruptedException {
		browser.get(deploymentUrl.toExternalForm() + MANAGE_JOB);
		title.sendKeys("Director");
		description.sendKeys("Director of the film.");
		salary.sendKeys("1,000,000");
		submit.click();
		Thread.sleep(2000);
		
	}
	
	@Test
	public void testFoo01() throws InterruptedException {
		browser.get(deploymentUrl.toExternalForm() + MANAGE_JOB);
		for (int i = 0; i < 1<<1; i++) {
			title.sendKeys("Director");
			description.sendKeys("Director of the film.");
			salary.sendKeys("1,000,000");
			submit.click();
		}
		Thread.sleep(2000);
		
	}
	
	@Test
	public void testFoo02() throws InterruptedException {
		browser.get(deploymentUrl.toExternalForm() + MANAGE_JOB);
		for (int i = 0; i < 1<<2; i++) {
			
			title.sendKeys("Key Grip");
			description.sendKeys("The gripper of the key");
			salary.sendKeys("339921");
			submit.click();
		}
		Thread.sleep(2000);
		
	}
	
	@Test
	public void testFoo03() throws InterruptedException {
		browser.get(deploymentUrl.toExternalForm() + MANAGE_JOB);
		for (int i = 0; i < 1<<3; i++) {
			
			title.sendKeys(fuzzWord(false));
			description.sendKeys(fuzzWord(false));
			salary.sendKeys(Double.toString(Math.random() * 1000000));
			submit.click();
		}
		Thread.sleep(60000);
		
	}
	
	@Test
	public void testFoo04() throws InterruptedException {
		browser.get(deploymentUrl.toExternalForm() + MANAGE_JOB);
		for (int i = 0; i < 1<<3; i++) {
			
			title.sendKeys(fuzzWord(true));
			description.sendKeys(fuzzWord(true));
			salary.sendKeys(Double.toString(Math.random() * 1000000));
			submit.click();
		}
		Thread.sleep(60000);
		
	}
	
	private String fuzzWord(boolean hard) {
		int strLenBase = hard ? 90 : 80;
		int strLen = (int) (Math.random() * strLenBase);
		StringBuilder sbRet = new StringBuilder(strLen);
		// 0x20 - 0x7e : 95 chars
		for (int i = 0; i < strLen; i++) {
			char rndC = 0;
			if (hard) {
				rndC = (char) (Math.random() * (1<<16));
			} else {
				rndC = (char) (0x20 + (int) (Math.random() * 95));
			}
			sbRet.append(rndC);
		}
		
		return sbRet.toString();
	}
}
