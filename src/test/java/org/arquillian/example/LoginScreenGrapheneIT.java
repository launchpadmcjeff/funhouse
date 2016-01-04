package org.arquillian.example;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.jboss.arquillian.graphene.Graphene.waitAjax;
import static org.jboss.arquillian.graphene.Graphene.waitModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
public class LoginScreenGrapheneIT {
	private static final String LOGIN_JSF = "login.jsf";
	private static final String USER_NAME = "demo";
	private static final String USER_PASSWORD = "demo";

	private static final String WEBAPP_SRC = "src/main/webapp/io/jsflifecycle";

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "login.war")
				.addClasses(Credentials.class, User.class,
						LoginController.class)
				.addAsWebResource(new File(WEBAPP_SRC, "login.xhtml"))
				.addAsWebResource(new File(WEBAPP_SRC, "home.xhtml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(
						new StringAsset("<faces-config version=\"2.0\"/>"),
						"faces-config.xml");
		System.out.println(archive.toString(true));
		return archive;
	}

	/**
	 * Remember the Deployment(testable = false) annotation!
	 * 
	 * Same as createDeployment, but automatically pick up all the *.xhtml files
	 * in the microdeployment:
	 */
	// public static WebArchive createDeploymentAll() {
	// WebArchive archive = ShrinkWrap
	// .create(WebArchive.class, "login.war")
	// .addClasses(Credentials.class, User.class,
	// LoginController.class)
	// .merge(ShrinkWrap.create(GenericArchive.class)
	// .as(ExplodedImporter.class).importDirectory(WEBAPP_SRC)
	// .as(GenericArchive.class), "/",
	// Filters.include(".*\\.xhtml$"))
	// .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	// .addAsWebInfResource(
	// new StringAsset("<faces-config version=\"2.0\"/>"),
	// "faces-config.xml");
	// System.out.println(archive.toString(true));
	// return archive;
	// }

	@Drone
	private WebDriver browser;

	@ArquillianResource
	private URL deploymentUrl;

	@FindBy(id = "userName")
	private WebElement userName;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login")
	private WebElement loginButton;

	@FindBy(tagName = "li")
	private WebElement facesMessage;

	@FindByJQuery("p:visible")
	private WebElement signedAs;

	@FindBy(css = "input[type=submit]")
	private WebElement whoAmI;

	@Test
	public void should_login_successfully1() {
		browser.get(deploymentUrl.toExternalForm() + LOGIN_JSF);
		userName.sendKeys(USER_NAME);
		password.sendKeys(USER_PASSWORD);

		loginButton.click();
		assertEquals("Welcome", facesMessage.getText().trim());

		whoAmI.click();
		assertTrue(signedAs.getText().contains(USER_NAME));
	}

	@Test
	public void should_login_successfully2() {
		browser.get(deploymentUrl.toExternalForm() + LOGIN_JSF);

		userName.sendKeys(USER_NAME);
		password.sendKeys(USER_PASSWORD);

		guardHttp(loginButton).click();
		assertEquals("Welcome", facesMessage.getText().trim());

		guardAjax(whoAmI).click();
		assertTrue(signedAs.getText().contains(USER_NAME));
	}

	@Test
	public void should_login_successfully3() {
		browser.get(deploymentUrl.toExternalForm() + LOGIN_JSF);

		userName.sendKeys(USER_NAME);
		password.sendKeys(USER_PASSWORD);

		loginButton.click();

		waitModel().until().element(facesMessage).is().present();
		assertEquals("Welcome", facesMessage.getText().trim());

		guardAjax(whoAmI).click();
		waitAjax().until().element(signedAs).text().contains(USER_NAME);
	}

	@Page
	private HomePage homePage;

	@Test
	public void should_login_successfully4(@InitialPage LoginPage loginPage) {

		loginPage.login(USER_NAME, USER_PASSWORD);
		homePage.assertOnHomePage();

		homePage.whoAmI();
		assertEquals(homePage.getUserName(), "You are signed in as "
				+ USER_NAME + ".");
	}

	@Test
	public void should_login_successfully5(@InitialPage LoginPage loginPage) {

		LoginForm loginForm = loginPage.getLoginForm();
		loginForm.login(USER_NAME, USER_PASSWORD);
		homePage.assertOnHomePage();

		homePage.whoAmI();
		assertEquals(homePage.getUserName(), "You are signed in as "
				+ USER_NAME + ".");
	}
}