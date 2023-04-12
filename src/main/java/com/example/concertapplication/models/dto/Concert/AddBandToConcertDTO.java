package com.example.concertapplication.models.dto.Concert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddBandToConcertDTO {
    String concertName;
    String bandName;
}
