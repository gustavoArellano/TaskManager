package com.codingdojo.taskmanager.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.taskmanager.models.UserModel;
import com.codingdojo.taskmanager.repositories.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserModel registerUser(UserModel user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    public List<UserModel> allUsers() {
    	return (List<UserModel>) userRepository.findAll();
    }

    // find user by email
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public UserModel findUserById(Long id) {
    	Optional<UserModel> user = userRepository.findById(id);
    	
    	if(user.isPresent()) {
            return user.get();
    	} else {
    	    return null;
    	}
    }

    public void update(UserModel user) {
        userRepository.save(user);
    }

    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        UserModel user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
        	// if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

	public List<UserModel> getAll() {
		return userRepository.findAll();
	}
}
