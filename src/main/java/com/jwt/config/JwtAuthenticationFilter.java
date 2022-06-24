package com.jwt.config;

import com.jwt.util.JwtUtil;
import com.jwt.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
// we need authenticate to user and validate to token

    @Autowired
    CustomUserDetailsService customUserDetailsService;


    @Autowired
    JwtUtil jwtUtil;


    public JwtAuthenticationFilter() {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get jwt
        //Bearer
        //validate
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;//to take username from authrization(token)
        String jwtToken = null;

        //to check null and format
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {

                username = this.jwtUtil.getUsernameFromToken(jwtToken);//will get username


            } catch (Exception e) {
                e.printStackTrace();
            }
            //security ,validate user
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);


                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());//objcet created

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


            } else {
                System.out.println("Token is not validated..");
            }

        }


        filterChain.doFilter(request, response);


    }
}
