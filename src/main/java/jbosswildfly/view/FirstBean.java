package jbosswildfly.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

@Named
@RequestScoped
public class FirstBean {
	private UIComponent dialogBinding;
	private UIComponent msg;
	private UIComponent form1;
	private UIComponent showDialog1Btn;
	private UIComponent name;
	private UIComponent okCommandButton;
	private String nameValue;
	private String description;
	
	public String okAction() {
		System.out.println("okAction - nameValue: " + nameValue + " description: " + description);
		return null;
	}
	
	public String okActionListener() {
		System.out.println("okActionListener - nameValue: " + nameValue + " description: " + description);
		return null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameValue() {
		return nameValue;
	}

	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}

	public UIComponent getName() {
		return name;
	}

	public void setName(UIComponent name) {
		logBinder(name);
		this.name = name;
	}

	public UIComponent getShowDialog1Btn() {
		return showDialog1Btn;
	}

	public void setShowDialog1Btn(UIComponent showDialog1Btn) {
		logBinder(showDialog1Btn);
		this.showDialog1Btn = showDialog1Btn;
	}

	public UIComponent getMsg() {
		return msg;
	}

	public void setMsg(UIComponent msg) {
		logBinder(msg);
		this.msg = msg;
	}

	public UIComponent getForm1() {
		return form1;
	}

	public void setForm1(UIComponent form1) {
		logBinder(form1);
		this.form1 = form1;
	}

	public FirstBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UIComponent getDialogBinding() {
		System.out.println("FirstBean getDialogBinding called");
		return dialogBinding;
	}

	public void setDialogBinding(UIComponent dialogBinding) {
		logBinder(dialogBinding);
		this.dialogBinding = dialogBinding;
	}

	public void handleClose(CloseEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("handleClose()"));
		System.out.println("EVENT: " + event);
	}
	public void handleMove(MoveEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("handleMove()"));
		System.out.println("EVENT: " + event);
	}
	public void handleMaximize(AjaxBehaviorEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("handleClose()"));
		System.out.println("EVENT handleMaximize: " + event);
	}
	public void handleMinimize(AjaxBehaviorEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("handleClose()"));
		System.out.println("EVENT handleMinimize: " + event);
	}
	public void handleRestoreMaximize(AjaxBehaviorEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("handleClose()"));
		System.out.println("EVENT handleRestoreMaximize: " + event);
	}
	public void handleRestoreMinimize(AjaxBehaviorEvent event) {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		currentInstance.addMessage(null,
				new FacesMessage("handleClose()"));
		System.out.println("EVENT handleRestoreMinimize: " + event);
	}

	public UIComponent getOkCommandButton() {
		return okCommandButton;
	}

	public void setOkCommandButton(UIComponent okCommandButton) {
		logBinder(okCommandButton);
		this.okCommandButton = okCommandButton;
	}
	
	private void logBinder(UIComponent comp) {
		String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
		System.out.println("logBinder: " + caller + " UIComponent: " + comp);
	}
}