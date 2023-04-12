package com.example.concertapplication.controllers;

import com.example.concertapplication.models.Ticket;
import com.example.concertapplication.models.dto.Ticket.TicketDTO;
import com.example.concertapplication.services.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashier")
public class CashierController {
    @Autowired
    private CashierService cashierService;
    @GetMapping("/ticket")
    public @ResponseBody List<Ticket> getAllTickets(){
        return cashierService.getAllTickets();
    }

    @PostMapping("/ticket")
    public @ResponseBody Ticket createTicket(@RequestBody TicketDTO ticketDTO){
        return cashierService.createTicket(ticketDTO);
    }

    @PutMapping("/ticket")
    public @ResponseBody Ticket updateTicket(@RequestBody TicketDTO ticketDTO){
        return cashierService.updateTicket(ticketDTO);
    }

    @DeleteMapping("/ticket")
    public @ResponseBody ResponseEntity<?> deleteTicket(@RequestBody TicketDTO ticketDTO){
        cashierService.deleteTicket(ticketDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
