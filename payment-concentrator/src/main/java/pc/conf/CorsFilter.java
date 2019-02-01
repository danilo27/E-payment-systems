package pc.conf;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter  {

	@Override
	protected void doFilterInternal(
			HttpServletRequest arg0, HttpServletResponse arg1, FilterChain arg2)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

         
    HttpServletResponse httpServletResponse = (HttpServletResponse)arg1;
    httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
    httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS, DELETE");
    httpServletResponse.addHeader("Access-Control-Allow-Headers", "Authorization");

    arg2.doFilter(arg0, arg1);
	}

	 
	
   
}