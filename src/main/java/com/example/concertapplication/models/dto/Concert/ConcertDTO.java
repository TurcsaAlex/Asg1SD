package com.example.concertapplication.models.dto.Concert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@Getter
public class ConcertDTO {
    @Getter
    private String name;
    @Getter
    private int seats;
    @Getter
    private Time date;

    public ConcertDTO(String name, int seats, String date) throws ParseException {
        this.name = name;
        this.seats = seats;
        this.date = parseTimeStringToSqlTime(date);
    }
    public static Time parseTimeStringToSqlTime(String timeString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date date = formatter.parse(timeString);
        return new Time(date.getTime());
    }
}
