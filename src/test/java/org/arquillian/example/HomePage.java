package org.arquillian.example;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(tagName = "li")
	private WebElement facesMessage;

	@FindByJQuery("#whoami p")
	private WebElement signedAs;

	@FindBy(css = "input[type=submit]")
	private GrapheneElement whoAmI;

	public void assertOnHomePage() {
		assertEquals("We should be on home page", "Welcome", getMessage());
	}

	public String getMessage() {
		return facesMessage.getText().trim();
	}

	public String getUserName() {
		return signedAs.getText();
	}

	public void whoAmI() {
		guardAjax(whoAmI).click();
	}
}