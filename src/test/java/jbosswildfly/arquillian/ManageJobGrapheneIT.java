package jbosswildfly.arquillian;

import static org.junit.Assert.assertEquals;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.jboss.arquillian.graphene.Graphene.waitAjax;
import static org.jboss.arquillian.graphene.Graphene.waitModel;

import java.io.File;
import java.net.URL;

import jbosswildfly.model.Job;
import jbosswildfly.repository.JobRepo;
import jbosswildfly.view.AddMarkersView;
import jbosswildfly.view.JobBean;
import jbosswildfly.view.LoginController;

import org.arquillian.example.HomePage;
import org.arquillian.example.LoginPage;
import org.arquillian.example.UserBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
public class ManageJobGrapheneIT {
	private static final String WEBAPP_SRC = "src/main/webapp/io/entities";

	private static final String MANAGE_JOB_JSF = "manageJob.jsf";

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		File[] primefaces = Maven.resolver().loadPomFromFile("pom.xml")
				.importRuntimeDependencies().resolve().withTransitivity()
				.asFile();
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "manageJob.war")
				.addClasses(JobBean.class, Job.class, JobRepo.class,
						LoginController.class, UserBean.class)
				.addAsLibraries(primefaces)
				/*.addAsWebResource(new File(WEBAPP_SRC, "manageJob.xhtml"))*/
				.addAsWebResource(new File(WEBAPP_SRC, "manageJob.xhtml"), "io/entities/manageJob.xhtml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("META-INF/persistence.xml",
						"classes/META-INF/persistence.xml")
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
	private GrapheneElement title;
	@FindBy(id = "manageJobForm:description")
	private GrapheneElement description;
	@FindBy(id = "manageJobForm:salary")
	private GrapheneElement salary;
	@FindBy(id = "manageJobForm:save")
	private GrapheneElement save;

//	@FindBy(xpath = "//tbody[@id='manageJobForm:jobTable_data']/tr/td")
	@FindBy(xpath = "//tbody[@id='manageJobForm:jobTable_data']/tr[1]")
	private GrapheneElement tr;

	@FindBy(id = "manageJobForm:jobTable:view")
	private GrapheneElement viewBtn;

	@FindBy(id = "manageJobForm:delete")
	private GrapheneElement deleteBtn;

	@Test
	public void testBasic() throws InterruptedException {
		browser.get(deploymentUrl.toExternalForm() + "io/entities/" + MANAGE_JOB_JSF);
		title.clear();
		title.sendKeys("Coach");
		description.clear();
		description.sendKeys("The team coach");
		salary.clear();
		salary.sendKeys("99232");
		save.click();
		Thread.sleep(10000);
	}

	@Test
	public void testDelete() {
		browser.get(deploymentUrl.toExternalForm() + "io/entities/" + MANAGE_JOB_JSF);
//		browser.get(deploymentUrl.toExternalForm() + MANAGE_JOB_JSF);
		for (int i = 0; i < 3; i++) {
			tr.click();
			guardAjax(viewBtn).click();
			guardAjax(deleteBtn).click();
		}
	}

	@Test
	public void should_be_deployed() {
		Assert.assertNotNull(browser);
		Assert.assertNotNull(deploymentUrl);
	}

	// @Page
	// private ManageJobPage jobPage;
	//
	// @Test
	// public void shouldAddJobSuccessfully() {
	// jobPage.assertOnManageJobsPage();
	//
	// }

}
