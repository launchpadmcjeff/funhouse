package jbosswildfly.view;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
 
@Named
@SessionScoped
public class FacePainter implements Serializable {
	private static final long serialVersionUID = 1L;

	public FacePainter() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String mainContent = "content/01name.xhtml";
 
    public String getMainContent() {
        return mainContent;
    }
 
    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }
 
}