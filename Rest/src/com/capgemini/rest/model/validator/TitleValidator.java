package com.capgemini.rest.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TitleValidator implements
		ConstraintValidator<CorrectTitle, String> {

	private String incorrectTitle;

	@Override
	public void initialize(CorrectTitle arg) {
		this.incorrectTitle = arg.incorrectTitle();
	}

	@Override
	public boolean isValid(String title, ConstraintValidatorContext context) {	
		if (title != null && title.contains(incorrectTitle)) {
			return false;
		}
		return true;
	}

}
