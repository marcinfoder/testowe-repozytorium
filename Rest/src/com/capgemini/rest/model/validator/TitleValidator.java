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
		//ResourceBundleMessageSource messages = AppContext
		//		.getBean(
		//				"messageSource",
		//				org.springframework.context.support.ResourceBundleMessageSource.class);
		if (title != null && title.contains(incorrectTitle)) {
			//context.disableDefaultConstraintViolation();
			//ConstraintViolationBuilder violationBuilder = context
			//		.buildConstraintViolationWithTemplate("{search.my.incorrectTitle}");
			//violationBuilder.addConstraintViolation();
			
			
			//violationBuilder.addNode("{1}").addConstraintViolation()
			//		.buildConstraintViolationWithTemplate("my 1 value")
			//		.addConstraintViolation();
			//violationBuilder.addNode("{2}").addConstraintViolation()
			//.buildConstraintViolationWithTemplate("my 2 value")
			//.addConstraintViolation();
			return false;
		}
		return true;
	}

}
