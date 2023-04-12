package com.example.concertapplication.models.dto.Ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketDTO {
    private String concertName;
    private String userName;
    private Integer id;
    private Double price;
    private Integer places;
}
