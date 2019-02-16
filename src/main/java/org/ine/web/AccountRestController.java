package org.ine.web;

import java.util.ArrayList;
import java.util.List;

import org.ine.entities.AppRole;
import org.ine.entities.AppUser;
import org.ine.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
@Autowired
private AccountService accountService;

	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm registerForm){
		if(!registerForm.getPassword().equals(registerForm.getCoPassword())) throw new RuntimeException("You must confirm your password");
		AppUser appUser=accountService.findUserByUserName(registerForm.getUsername());
		if(appUser!=null) throw new RuntimeException("This user already exists");
		AppUser user  =new AppUser();
		List<AppRole> roles=new ArrayList<>();
	    user.setUsername(registerForm.getUsername());
	    user.setPassword(registerForm.getPassword());
	    user.setRoles(roles);
		accountService.saveUser(user);

	    accountService.addRoleToUser(user.getUsername(), "USER");
			return user;
	}
}


