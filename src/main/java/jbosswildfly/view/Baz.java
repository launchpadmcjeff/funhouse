package jbosswildfly.view;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@Model
public class Baz {

	private String drink = "Bebida";

	public String getDrink() {
		System.out.println("Baz.getDrink");
		return drink;
	}

	public void setDrink(String drink) {
		System.out.println("Baz.setDrink");
		this.drink = drink;
	}

	public void buttonAction(ActionEvent actionEvent) {
		System.out.println("Baz.buttonAction - PhaseId: " + actionEvent.getPhaseId() + " source: " + actionEvent.getSource());
		addMessage("Welcome to Primefaces!!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
