package jbosswildfly.view;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import jbosswildfly.repository.UserBean;

@Named(value = "loginController")
@RequestScoped
public class LoginController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	@EJB
	private UserBean userBean;
	
	public boolean isAuthenticated() {
		return userBean.isAuthenticated();
	}

}
