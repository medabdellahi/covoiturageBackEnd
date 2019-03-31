package org.ine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.ine.dao.RoleRepository;
import org.ine.dao.UserRepository;
import org.ine.entities.AppRole;
import org.ine.entities.AppUser;
import org.ine.service.AccountService;
import org.ine.service.AccountServiceImpl;
import org.ine.web.RegisterForm;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	  
	  @MockBean
	  BCryptPasswordEncoder bCryptPasswordEncoder;
	  
	  @InjectMocks
	  AccountServiceImpl accountServiceImpl;
	 
	  @Autowired
	  private ObjectMapper objectMapper;

	  @Mock
	  private UserRepository userRepository;
	  
	  @Mock
	  private RoleRepository roleRepository;
	  
	  @Resource
	    private FilterChainProxy springSecurityFilterChain; 
	  
	  @Resource
	    private WebApplicationContext webApplicationContext;
	  
	  	private AppUser appUser;
	  
	  @Before
	  public void init() {
	   MockitoAnnotations.initMocks(this);
	   List roles=new ArrayList<AppRole>();
		  AppRole appRole=new AppRole(null,"rolename");
		  roles.add(appRole);
		  AppUser appUser=new AppUser(null,"userName","password",roles);
	  }
	  
	  @Before
	    public void setUp() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
	                .addFilter(springSecurityFilterChain)
	                .build();
	    }
	  
	  
	 @Test
	 public void registrationTest() throws Exception {
		MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    		MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

		List roles=new ArrayList<AppRole>();
		RegisterForm user = new RegisterForm();
	    user.setCoPassword("1234");
	    user.setPassword("1234");
	    user.setUsername("user1");
	    
	    mockMvc.perform(post("/register",user)
	            .contentType("application/json")
	            
	            .content(objectMapper.writeValueAsString(user)))
	            .andExpect(status().isOk())
	    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
	    .andExpect(jsonPath("$.username", is("user1")));
	  }
	  
	  @Test
	  public void saveRoleTest(){
		  AppRole appRole =new AppRole(null,"roleName");
		  accountServiceImpl.saveRole(appRole);
		  verify(roleRepository,times(1)).save(appRole);
	  }
	  
	  @Test
	  public void findUserByUserNameTest(){
		  List roles =new ArrayList<AppRole>();
		  when(userRepository.findByUsername("Ahmed")).thenReturn(new AppUser(null,"Ahmed","1234",roles));
		  appUser = accountServiceImpl.findUserByUserName("Ahmed");
		  assertEquals("Ahmed", appUser.getUsername());
		  
	  }
	  @Test
	  public void saveUserTest(){
		  List roles=new ArrayList<AppRole>();
		  AppRole appRole=new AppRole(null,"rolename");
		  roles.add(appRole);
		  appUser= new AppUser(null,"userName","password",roles);
		  Mockito.when(userRepository.save(appUser)).thenReturn(appUser);
		    assertThat(accountServiceImpl.saveUser(appUser)).isEqualTo(appUser);
		  Assert.assertTrue(true);
	  }

}

