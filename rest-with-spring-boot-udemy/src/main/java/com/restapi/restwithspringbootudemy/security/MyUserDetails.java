package com.restapi.restwithspringbootudemy.security;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restapi.restwithspringbootudemy.data.models.User;
import com.restapi.restwithspringbootudemy.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = repository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(s -> new SimpleGrantedAuthority(s.getDescription())).filter(Objects::nonNull)
				.collect(Collectors.toList());

		UserDetails details = org.springframework.security.core.userdetails.User.withUsername(username)
				.password(user.getPassword()).authorities(authorities).accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();

		return details;
	}

}
