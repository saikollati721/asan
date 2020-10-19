package com.sai;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	User save(User user);

	Collection<? extends GrantedAuthority> getAuthorities();

	Collection<? extends GrantedAuthority> getAuthorities(String role);
}
