package org.ine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;






import org.ine.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootApplication
public class CovoiturageBackEndApplication extends SpringBootServletInitializer  {

@Autowired
private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(CovoiturageBackEndApplication.class, args);
	} 
	@Bean
	public BCryptPasswordEncoder getBCPW(){
		return new BCryptPasswordEncoder();
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//	accountService.saveUser(new AppUser(null,"admin","1234",null));
//	accountService.saveUser(new AppUser(null,"user","1234",null));
//	accountService.saveRole(new AppRole(null,"ADMIN"));
//	accountService.saveRole(new AppRole(null,"USER"));
//	accountService.addRoleToUser("user", "USER");
//	accountService.addRoleToUser("admin", "ADMIN");
//
//	}

}

