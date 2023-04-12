package com.example.concertapplication.services;

import com.example.concertapplication.config.security.AuthUser;
import com.example.concertapplication.config.security.JwtTokenUtil;
import com.example.concertapplication.exceptions.NotAuthenticatedException;
import com.example.concertapplication.exceptions.UserAlreadyExistsException;
import com.example.concertapplication.exceptions.service.ResourceNotFoundException;
import com.example.concertapplication.models.User;
import com.example.concertapplication.models.dto.User.UserChangePasswordDTO;
import com.example.concertapplication.models.dto.User.UserLoginDTO;
import com.example.concertapplication.models.dto.User.UserSignUpDTO;
import com.example.concertapplication.models.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepository repository;
    private JwtTokenUtil jwtTokenUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;

    public String signUp(UserSignUpDTO userSignUpDTO) {
        String email = userSignUpDTO.getEmail();
        String firstName=userSignUpDTO.getFirstName();
        String lastName=userSignUpDTO.getLastName();
        String password=userSignUpDTO.getPassword();
        String phoneNumber= userSignUpDTO.getPhoneNumber();

        if(repository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.setAuthority("CUSTOMER");
        if(email != null && password != null)
            repository.save(user);
        return jwtTokenUtil.generateAccessToken(new AuthUser(user.getId(), user.getEmail(), user.getPassword(),user.getAuthority()));
    }

    public void changePassword(UserChangePasswordDTO userChangePasswordDTO) {
        String newPassword = userChangePasswordDTO.getNewPassword();
        String oldPassword = userChangePasswordDTO.getOldPassword();


        User user = getById(getCurrentUserId());
        String username=user.getEmail();

        AuthUser authUser = userDetailsService.loadUserByUsername(username);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUser, oldPassword));
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }

    public String login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        AuthUser authUser = userDetailsService.loadUserByUsername(username);
        System.out.println("1"+authUser.getAuthorities());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUser, password));
        return jwtTokenUtil.generateAccessToken(authUser);
    }


    protected Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null) {
            throw new NotAuthenticatedException();
        }
        return ((AuthUser) authentication.getPrincipal()).getId();
    }

    public User getById(Integer id) throws ResourceNotFoundException {
        User entity = repository.findById(id)
                .orElse(null);
        if (entity == null) {
            throw new ResourceNotFoundException();
        }
        return entity;
    }
}
