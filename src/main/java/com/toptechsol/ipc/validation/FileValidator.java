package com.toptechsol.ipc.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.toptechsol.ipc.model.Certificate;

@Component
public class FileValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Certificate.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		Certificate file = (Certificate) obj;

		if (file.getContent() != null) {
			if (file.getContent().length == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}