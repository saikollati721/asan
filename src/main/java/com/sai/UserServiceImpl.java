package com.sai;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userrepo;
	

	public UserServiceImpl(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userrepo.findByEmail(username);
		System.out.println("emai is : "+user.getEmail()+" password is :   "+user.getPassword());
		if(user==null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),getAuthorities(user.getRole()) );
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(String role) {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(role));
	}
	
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		userrepo.save(user);
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
