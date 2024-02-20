package com.allywheel.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allywheel.Model.UserModel;
import com.allywheel.Repository.UserRepository;


import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	    public List<UserModel> getUsers() {
	        return userRepository.findAll();
	    }

//	    @Override
//	    public Optional<UserModel> getUserByUsername(String username) {
//	        return userRepository.findByUsername(username);
//	    }

	   
	    public Optional<UserModel> getUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    
	    public boolean hasUserWithUsername(String username) {
	        return userRepository.existsByUsername(username);
	    }

	   
	    public boolean hasUserWithEmail(String email) {
	        return userRepository.existsByEmail(email);
	    }

	   
	    public UserModel validateAndGetUserByUsername(String username) {
	        return getUserByEmail(username)
	                .orElseThrow();
	    }

	   
	    public UserModel saveUser(UserModel user) {
	        return userRepository.save(user);
	    }

	    
	    public void deleteUser(UserModel user) {
	        userRepository.delete(user);
	    }

		
		public Optional<UserModel> getUserByUsername(String username) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}
}
