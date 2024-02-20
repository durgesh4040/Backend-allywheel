package com.allywheel.Security;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.allywheel.Model.UserModel;
import com.allywheel.Repository.UserRepository;
import com.allywheel.Service.UserService;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel user=this.userRepository.findByEmail(username)
				.orElseThrow(()->new RuntimeException("User Not found"));
		List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return mapUserToCustomUserDetails(user, authorities);
	}
    public CustomUserDetails mapUserToCustomUserDetails(UserModel user,List<SimpleGrantedAuthority> authorities) {
	CustomUserDetails customUserDetails=new CustomUserDetails();
	 customUserDetails.setId(user.getId());
     customUserDetails.setUsername(user.getUsername());
     customUserDetails.setPassword(user.getPassword());
     customUserDetails.setName(user.getName());
     customUserDetails.setEmail(user.getEmail());
     customUserDetails.setAuthorities(authorities);
     return customUserDetails;
    
}

  
}

