package com.codingdojo.taskmanager.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.codingdojo.taskmanager.models.UserModel;
import com.codingdojo.taskmanager.repositories.UserRepository;




@Component
public class UserValidator implements Validator {
	private final UserRepository userRepository;
	public UserValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
		
    
    
    @Override
    public boolean supports(Class<?> clazz) {
        return UserModel.class.equals(clazz);
    }
    
   
    @Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "emailrequired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "namerequired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "passwordrequired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirmation", "passwordconfirmationrequired");
		UserModel user = (UserModel) target;
		if(userRepository.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "emailDup");
		}
		if(!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "PasswordConfirm");
		}
	}
}
