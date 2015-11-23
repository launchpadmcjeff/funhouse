package jbosswildfly.view;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
 
@Named
@SessionScoped
public class FacePainter {
     
    private String mainContent = "content/01name.xhtml";
 
    public String getMainContent() {
        return mainContent;
    }
 
    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }
 
}