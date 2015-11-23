package jbosswildfly.view;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	private FacePainter facePainter;

	public FacePainter getFacePainter() {
		return facePainter;
	}

	public void setFacePainter(FacePainter facePainter) {
		this.facePainter = facePainter;
	}

	public void backToLandingPage() {
		facePainter.setMainContent("content/01name.xhtml");
	}

	public void captureUsername() {
		facePainter.setMainContent("content/02birthday.xhtml");
	}

	public void captureBirthday() {
		facePainter.setMainContent("content/03summary.xhtml");
	}

}