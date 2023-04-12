package com.example.concertapplication.services;

import com.example.concertapplication.exceptions.UserAlreadyExistsException;
import com.example.concertapplication.models.Concert;
import com.example.concertapplication.models.Ticket;
import com.example.concertapplication.models.User;
import com.example.concertapplication.models.dto.Ticket.TicketDTO;
import com.example.concertapplication.models.repos.ConcertRepository;
import com.example.concertapplication.models.repos.TicketRepository;
import com.example.concertapplication.models.repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CashierService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConcertRepository concertRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket createTicket(TicketDTO ticketDTO){
        if(userRepository.findByEmail(ticketDTO.getUserName()).isEmpty()){
            throw new UserAlreadyExistsException();
        }
        if(concertRepository.findByName(ticketDTO.getConcertName()).isEmpty()){
            throw new RuntimeException("Concert not found");
        }

        User user=userRepository.findByEmail(ticketDTO.getUserName()).get();
        Concert concert=concertRepository.findByName(ticketDTO.getConcertName()).get();
        if(ticketRepository.sumPlacesByConcert(concert).isPresent()&&ticketRepository.sumPlacesByConcert(concert).get()>concert.getSeats()){
            throw new RuntimeException("Not enough seats");
        }
        Ticket ticket= new Ticket(ticketDTO.getPrice(),user,concert,ticketDTO.getPlaces());
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket updateTicket(TicketDTO ticketDTO){
        Ticket ticket= ticketRepository.getById(ticketDTO.getId());
        if(userRepository.findByEmail(ticketDTO.getUserName()).isEmpty()){
            throw new UserAlreadyExistsException();
        }
        if(concertRepository.findByName(ticketDTO.getConcertName()).isEmpty()){
            throw new RuntimeException("Concert not found");
        }
        if(ticketDTO.getPrice()!=null)
            ticket.setPrice(ticketDTO.getPrice());
        if(ticketDTO.getUserName()!=null) {
            User user=userRepository.findByEmail(ticketDTO.getUserName()).get();
            ticket.setUser(user);
        }
        if(ticketDTO.getConcertName()!=null){
            Concert concert=concertRepository.findByName(ticketDTO.getConcertName()).get();
            ticket.setConcert(concert);
        }
        if(ticketDTO.getPlaces()!=null)
            ticket.setPlaces(ticketDTO.getPlaces());
        ticketRepository.save(ticket);
        return ticket;
    }

    public void deleteTicket(TicketDTO ticketDTO){
        ticketRepository.deleteById(ticketDTO.getId());
    }
}
