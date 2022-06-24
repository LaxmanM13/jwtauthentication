package com.jwt.controller;

import com.jwt.util.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    //@Autowired
    private final AuthenticationManager authenticationManager;


    //@Autowired
    private final CustomUserDetailsService customUserDetailsService;


    //@Autowired
    private final JwtUtil jwtUtil;

    public JwtController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        //we accepth the username and password so need to create request class
        System.out.println("Inside Controller");
        System.out.println(jwtRequest);
        //need to authenticate username and pass if sucess then it will generate token
        try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));


        } catch (UsernameNotFoundException e) {

            throw new Exception("usename not found");
        } catch (BadCredentialsException e) {

            throw new Exception("Bad Credentials");
        }


        //generate token from userdetails service
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String token = this.jwtUtil.generateToken(userDetails);//to generate token call util object


        //create resposnse class
        //cretae respo class

        return ResponseEntity.ok(new JwtResponse(token));//in response pass the value it will automatic comvert into json

    }
}
