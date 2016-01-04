package jbosswildfly.arquillian;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.support.FindBy;

@Location("manageJob.jsf")
public class ManageJobPage {
	@FindBy(id = "manageJobForm:title")
	private GrapheneElement title;
	@FindBy(id = "manageJobForm:description")
	private GrapheneElement description;
	@FindBy(id = "manageJobForm:salary")
	private GrapheneElement salary;
	@FindBy(id = "manageJobForm:save")
	private GrapheneElement save;

	public void assertOnManageJobsPage() {
		title.clear();
		title.sendKeys("Coach");
		description.clear();
		description.sendKeys("The team coach");
		salary.clear();
		salary.sendKeys("99232");
		save.click();
	}

}