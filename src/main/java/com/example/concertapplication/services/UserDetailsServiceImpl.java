package com.example.concertapplication.services;

import com.example.concertapplication.models.repos.UserRepository;
import com.example.concertapplication.config.security.AuthUser;
import com.example.concertapplication.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public AuthUser loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException(
                       String.format("User %s not found", email)));

        return new AuthUser(user.getId(), user.getEmail(), user.getPassword(),user.getAuthority());
    }
}
