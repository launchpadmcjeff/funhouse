package jbosswildfly.view;

import javax.enterprise.inject.Model;

@Model
public class ConversionBean {

	public ConversionBean() {
		// TODO Auto-generated constructor stub
	}

	public String doActionListener() {
		System.out.println("ConversionBean.doActionListener");
		return null;
	}

	public String doAction() {
		System.out.println("ConversionBean.doAction");
		return null;
	}
}
