package com.pr.corina.lab4pr.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pr.corina.lab4pr.model.User;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
        
	
//	@Autowired
//	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");

		if(!emailValidator.valid(user.getEmail())){
			errors.rejectValue("email", "Pattern.userForm.email");
		}
		
	}

}