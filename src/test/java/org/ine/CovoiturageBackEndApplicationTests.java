package org.ine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.ine.dao.UserRepository;
import org.ine.entities.AppRole;
import org.ine.entities.AppUser;
import org.ine.service.AccountServiceImpl;
import org.ine.web.RegisterForm;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class CovoiturageBackEndApplicationTests {

	
	
	@Test
	public void contextLoads() {
	}

	 @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;

	  @Mock
	  private UserRepository userRepository;
	  
	  @Resource
	    private FilterChainProxy springSecurityFilterChain; 
	  
	  @Resource
	    private WebApplicationContext webApplicationContext;
	  @Before
	    public void setUp() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
	                .addFilter(springSecurityFilterChain)
	                .build();
	    }
	  
	  
//	  @Test
//	  public void testUserLogin() throws Exception {
//		  AppUser user=new AppUser();
//		  user.setUsername("admin");
//		  user.setPassword("1234");
//		  
//		  
//		    Gson gson = new Gson();
//		    String json = gson.toJson(user);
//
//
//		  
//		  
//	      RequestBuilder requestBuilder = post("/login").contentType(MediaType.APPLICATION_JSON).content(json);
//	      mockMvc.perform(requestBuilder)
//	              .andDo(print())
//	              .andExpect(status().isOk()) ;
//	  }
	  
	  
	  @Test
	 public void registrationTest() throws Exception {
		  List roles=new ArrayList<AppRole>();
		  
	    RegisterForm user = new RegisterForm();
	    user.setCoPassword("1234");
	    user.setPassword("1234");
	    user.setUsername("user1");

	    mockMvc.perform(post("/register",user)
	            .contentType("application/json")
	            
	            .content(objectMapper.writeValueAsString(user)))
	            .andExpect(status().isOk());

	  }
	  
	  @Test
	  public void whenFindByName_thenReturnUser() {
	      // given
		  AppUser appUser = new AppUser();
		  appUser.setPassword("1234");
		  appUser.setUsername("Zaphod1128q1");
	   
		  userRepository.save(appUser);
	      // when
	      AppUser found = userRepository.findByUsername(appUser.getUsername());
	   
	      
	  }
}

