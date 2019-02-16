package org.ine.sec;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class ConfigCORSFilter  implements Filter{
   
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "/*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "Origin, Accept, XRequeste-With,Content-Type,"
        		+ "Access-Control-Request-Method,"
        		+ "Access-Control-Request-Headers,"
        		+ "Authorization");
        ((HttpServletResponse) response).addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,"
        		+ " Access-Control-Allow-Credentials,"
        		+ "Authorization");
       
        chain.doFilter(request, response);
	      
			
	}

}
