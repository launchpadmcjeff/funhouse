package org.arquillian.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

@Stateful
public class UserBean {

	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	boolean authenticated = false;

	@PostConstruct
	@PostActivate
	public void setup() {
		System.out.println("##### Create user Bean: " + this.toString());
	}

	public boolean isAuthenticated() {
		System.out
				.println("########## Authentication test is automatically passing.");
		authenticated = true;// hard coded for simplicity.
		return authenticated;
	}

	@PrePassivate
	@PreDestroy
	public void cleanup() {
		System.out.println("##### Destroy user Bean");
	}

}