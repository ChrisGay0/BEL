package com.cgsolutions.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.cgsolutions.security.domain.User;
import com.cgsolutions.security.thread.LoggedInUser;

public class SecurityFilter implements Filter{
  public void destroy(){
	  
  }
 
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
	 if(!((HttpServletRequest)request).getRequestURI().contains("login")){
		 if(((HttpServletRequest)request).getSession().getAttribute("validUser") != null){
			 LoggedInUser.setLoggedInUser((User)((HttpServletRequest)request).getSession().getAttribute("validUser"));
	    	 filterChain.doFilter(request, response);
	     }
	     else{
	    	 ((HttpServletResponse)response).sendRedirect("login.htm");  
	     }
	 }
	 else{
		 filterChain.doFilter(request, response);
	 }
  }

  public void init(FilterConfig arg0) throws ServletException {
	
  }
}
