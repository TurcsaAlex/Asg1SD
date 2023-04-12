package com.example.concertapplication.services;

import com.example.concertapplication.config.security.AuthUser;
import com.example.concertapplication.config.security.JwtTokenUtil;
import com.example.concertapplication.exceptions.UserAlreadyExistsException;
import com.example.concertapplication.models.Concert;
import com.example.concertapplication.models.Ticket;
import com.example.concertapplication.models.User;
import com.example.concertapplication.models.dto.Concert.ConcertDTO;
import com.example.concertapplication.models.dto.User.UserSignUpDTO;
import com.example.concertapplication.models.repos.ConcertRepository;
import com.example.concertapplication.models.repos.TicketRepository;
import com.example.concertapplication.models.repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private TicketRepository ticketRepository;
    public String createCashier(UserSignUpDTO userSignUpDTO) {
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
        user.setAuthority("CASHIER");
        if(email != null && password != null)
            repository.save(user);
        return jwtTokenUtil.generateAccessToken(new AuthUser(user.getId(), user.getEmail(), user.getPassword(),user.getAuthority()));
    }

    public String updateCashier(UserSignUpDTO userSignUpDTO) {
        String email = userSignUpDTO.getEmail();
        String firstName=userSignUpDTO.getFirstName();
        String lastName=userSignUpDTO.getLastName();
        String password=userSignUpDTO.getPassword();
        String phoneNumber= userSignUpDTO.getPhoneNumber();
        User user = new User();

        if(repository.findByEmail(email).isPresent()){
            user=repository.findByEmail(email).get();
        }

        if(email !=null)
            user.setEmail(email);
        if(firstName !=null)
            user.setFirstName(firstName);
        if(lastName !=null)
            user.setLastName(lastName);
        if(phoneNumber !=null)
            user.setPhoneNumber(phoneNumber);
        if(password !=null)
            user.setPassword(passwordEncoder.encode(password));
        user.setAuthority("CASHIER");
        if(email != null && password != null)
            repository.save(user);
        return jwtTokenUtil.generateAccessToken(new AuthUser(user.getId(), user.getEmail(), user.getPassword(),user.getAuthority()));
    }

    public void deleteCashier(String email){
        if(repository.findByEmail(email).isPresent()) {
            User deletedUser = repository.findByEmail(email).get();
            repository.delete(deletedUser);
        }
    }

    public List<User> getAllCashiers()throws NoSuchElementException {
        return repository.findAllByAuthority("CASHIER").orElseThrow();
    }
    public List<User> getAllCustomers()throws  NoSuchElementException{
        return repository.findAllByAuthority("CUSTOMER").orElseThrow();
    }

    public List<Ticket> getAllTicketsFromConcert(ConcertDTO concertDTO){
        Concert concert= concertRepository.findByName(concertDTO.getName()).get();
        return ticketRepository.getTicketsByConcert(concert);
    }
}
