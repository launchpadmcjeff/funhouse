package jbosswildfly.jsf;

import javax.inject.Named;

@Named("bean")
public class Foo {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
