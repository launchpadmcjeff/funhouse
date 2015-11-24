package jbosswildfly.jsflifecycle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//@FacesConverter
public class FooConverter implements Converter {

	public FooConverter() {
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		System.out.println("getAsObject: " + value);
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		System.out.println("getAsString: " + value);
		return (String) value;
	}

}
