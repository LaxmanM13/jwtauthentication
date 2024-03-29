package com.jwt.services;

import com.jwt.model.CustomUserDetails;
import com.jwt.model.User;
import com.jwt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // @Autowired
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //this method basically return user when it called
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        final User user = this.userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found !!");
        } else {
            return new CustomUserDetails(user);
        }


        //user database `

//        if (userName.equals("Lakhan")) {
//            return new User("Lakhan", "Lakhan@123", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found !!");
//        }

    }
}
