package jbosswildfly.view;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.arquillian.example.UserBean;

@Named(value = "loginController")
@RequestScoped
public class LoginController {

//	String theme = "aristo";
//	String theme = "cupertino";
	String theme = "midnight";
	
	public String getTheme() {
		System.out.println("LoginController theme: " + theme);
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	@EJB
	private UserBean userBean;
	
	public boolean isAuthenticated() {
		return userBean.isAuthenticated();
	}

}
