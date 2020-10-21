package com.sai.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sai.model.User;
import com.sai.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private UserRepository userrepo;
	

	public UserService(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userrepo.findByEmail(username);
		System.out.println("emai is : "+user.getEmail()+" password is :   "+user.getPassword());
		if(user==null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),getAuthorities(user.getRole()) );
	}

	
	
	public Collection<? extends GrantedAuthority> getAuthorities(String role) {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(role));
	}
	

	public User save(User user) {
		// TODO Auto-generated method stub
		userrepo.save(user);
		return null;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
