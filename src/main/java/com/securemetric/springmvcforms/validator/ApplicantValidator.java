package com.securemetric.springmvcforms.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.securemetric.springmvcforms.model.ApplicantInfo;

@Component
public class ApplicantValidator implements Validator {

	// common validator lib
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	// Cac class duoc ho vo validate
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ApplicantInfo.class;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ApplicantInfo applicantInfo = (ApplicantInfo) target;
		
		// Kiem tra cac field cua ApplicantInfo
		// Cac truong thuoc: messages/validator.properties
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.applicantForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.applicantForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "NotEmpty.applicantForm.position");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.applicantForm.gender");
		
		if(!emailValidator.isValid(applicantInfo.getEmail())) {
			// Loi truong nhap email
			errors.rejectValue("email", "Pattern.applicantForm.email");
		}
		if(applicantInfo.getSkills() == null || applicantInfo.getSkills().length == 0) {
			errors.rejectValue("skills", "Select.applicantForm.skills");
		}
	}
}
