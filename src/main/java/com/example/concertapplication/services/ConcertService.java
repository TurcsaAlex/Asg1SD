package com.example.concertapplication.services;

import com.example.concertapplication.models.Band;
import com.example.concertapplication.models.Concert;
import com.example.concertapplication.models.dto.Band.BandDTO;
import com.example.concertapplication.models.dto.Concert.ConcertDTO;
import com.example.concertapplication.models.repos.ConcertRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ConcertService {
    @Autowired
    private ConcertRepository concertRepository;

    public Concert createConcert(ConcertDTO concertDTO) throws Exception {

        Concert c=new Concert(concertDTO.getName(),concertDTO.getSeats(),concertDTO.getDate());
        System.out.println(c);
        if(concertRepository.findByName(concertDTO.getName()).isPresent()){
            throw new Exception("Concert already exists");
        }
        concertRepository.save(c);
        return c;
    }
    public Concert updateConcert(ConcertDTO concertDTO) throws Exception {

        Concert c=new Concert(concertDTO.getName(),concertDTO.getSeats(),concertDTO.getDate());
        System.out.println(c);
        concertRepository.save(c);
        return c;
    }
    public List<Concert> getAllConcerts(){
        return concertRepository.findAll();
    }

    public void deleteConcert(ConcertDTO concertDTO) throws Exception {

        Concert c=new Concert(concertDTO.getName(),concertDTO.getSeats(),concertDTO.getDate());
        System.out.println(c);
        if(!concertRepository.findByName(concertDTO.getName()).isPresent()){
            throw new Exception("Concert does not exist");
        }
        concertRepository.delete(c);
        return;
    }
}
