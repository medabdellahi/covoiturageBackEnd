package org.ine.service;

import java.util.ArrayList;
import java.util.Collection;

import org.ine.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user=accountService.findUserByUserName(username)	;
		if(user==null) throw new UsernameNotFoundException("");
		Collection<GrantedAuthority> authoroties =new ArrayList<>(); 
		user.getRoles().forEach(r->{
			authoroties.add(new SimpleGrantedAuthority(r.getRoleName()));
		});

		return new User(user.getUsername(),user.getPassword(),authoroties);
	}

}
