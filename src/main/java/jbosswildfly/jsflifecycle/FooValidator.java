package jbosswildfly.jsflifecycle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

//@FacesValidator
public class FooValidator implements Validator {

	public FooValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		System.out.println("validate: " + value);
		
	}

}
