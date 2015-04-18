package com.capgemini.rest.model.validator;

import org.springframework.validation.Errors;

public interface IValidator<T> {

	boolean validate(T object, Errors errors);
}
