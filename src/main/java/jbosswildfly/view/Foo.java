package jbosswildfly.view;

import javax.inject.Named;

@Named
public class Foo {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
