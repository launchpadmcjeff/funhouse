package org.arquillian.example;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("login.jsf")
public class LoginPage {
	@FindBy
    private LoginForm loginForm;        // locates the root of a page fragment on a particular page
    
    public LoginForm getLoginForm() {   // we can either manipulate with the login form or just expose it
       return loginForm;
    }
    
	@FindBy(id = "userName")
	private WebElement userName;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login")
	private WebElement loginButton;

	public void login(String userName, String password) {
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		guardHttp(loginButton).click();
	}
}