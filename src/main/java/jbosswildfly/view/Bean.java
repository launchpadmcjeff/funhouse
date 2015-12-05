package jbosswildfly.view;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped
public class Bean {
	private boolean showMe = false;

	public boolean isShowMe() {
		return showMe;
	}

	public void setShowMe(boolean showMe) {
		this.showMe = showMe;
	}

	public void toggleShowMe() {
		System.out.println(showMe);
		if (showMe == true) {
			showMe = false;
		} else {
			showMe = true;
		}
	}

	/** Creates a new instance of Bean */
	public Bean() {
		System.out.println("Bean Initialized");
	}

	private int count = 0;
	public void increment(ActionEvent ae) {
		System.out.println("increment: " + count + " ActionEvent: " + ae);
		count++;
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String x = requestParameterMap.get("x");
		String y = requestParameterMap.get("y");
		System.out.println("Request x: " + x + " Request y: " + y);
	}
	
	public int getCount() {
		System.out.println("getCount: " + count);
		return count;
	}
}