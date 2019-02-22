package org.ine.sec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorisationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, XRequeste-With,Content-Type,"
        		+ "Access-Control-Request-Method,"
        		+ "Access-Control-Request-Headers,"
        		+ "Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,"
        		+ " Access-Control-Allow-Credentials,"
        		+ "Authorization");
       if(request.getMethod().equals("OPTIONS")){
    	   response.setStatus(HttpServletResponse.SC_OK);
    	   
       }else{

	   		String jwt =request.getHeader(SecurityConstants.HEADER_STRING); 
	   		System.out.println("jwt: "+jwt);
	   		if(jwt==null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)){
	   			filterChain.doFilter(request, response); 
	   			return;
	   		}
	   		Claims claims=Jwts.parser().setSigningKey(SecurityConstants.SECRET)
	   				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
	   				.getBody();
	   		String username =claims.getSubject();
	   		ArrayList<Map<String,String>> roles=(ArrayList<Map <String,String>>) claims.get("roles");
	   		Collection <GrantedAuthority> athorities=new ArrayList<>();
	   		roles.forEach(r->{
	   			athorities.add(new SimpleGrantedAuthority(r.get("authority")));
	   		});
	   		UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(username, null,athorities);
	   		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	   		filterChain.doFilter(request, response);
	       }
			
		
	}

}
