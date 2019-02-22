package org.ine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.ine.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootApplication
public class CovoiturageBackEndApplication implements CommandLineRunner  {

@Autowired
private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(CovoiturageBackEndApplication.class, args);
	} 
	@Bean
	public BCryptPasswordEncoder getBCPW(){
		return new BCryptPasswordEncoder();
	}
	@Override
	public void run(String... arg0) throws Exception {
		
//		accountService.saveUser(new AppUser(null,"admin","1234",null));
//		accountService.saveUser(new AppUser(null,"user","1234",null));
//		accountService.saveRole(new AppRole(null,"ADMIN") );
//		accountService.saveRole(new AppRole(null,"USER") );
//		accountService.addRoleToUser("admin", "ADMIN");
//		accountService.addRoleToUser("admin", "USER");
//		accountService.addRoleToUser("user", "USER");
//
//
//		
//
//		Stream.of("T1","T2","T3").forEach(t->{
//			taskReposotory.save(new Task(null,t));
//		});
//		taskReposotory.findAll().forEach(t->{
//			System.out.println(t.getTaskName());
//		});
	}

}

