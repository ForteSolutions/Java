package com.codingdojo.loginandregister.services;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.loginandregister.models.User;
import com.codingdojo.loginandregister.repositories.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user, BindingResult result) {
    	Optional<User> potentialUser = userRepository.findByEmail(user.getEmail());
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "GENERAL_ERROR", "This email is already registered!");
    	}
    	if(!user.getPassword().equals(user.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
    	if(result.hasErrors()) {
			return null;
		}
    	else {
    		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashed);
            return userRepository.save(user);
    	}        
    }
    // find user by email
    public User findByEmail(String email) {
    	Optional<User> user=userRepository.findByEmail(email);
    	User userObject=user.get();
        return userObject;
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        Optional<User> user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        User userObject=user.get();
        if(user == null) {
            return false;
        } else {
        	// if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, userObject.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
}