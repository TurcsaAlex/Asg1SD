package com.example.concertapplication.services;

import com.example.concertapplication.models.Band;
import com.example.concertapplication.models.Concert;
import com.example.concertapplication.models.dto.Concert.AddBandToConcertDTO;
import com.example.concertapplication.models.repos.BandRepository;
import com.example.concertapplication.models.repos.ConcertRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BandConcertService {
    @Autowired
    private ConcertRepository concertRepository;
    @Autowired
    private BandRepository bandRepository;

    public void addBandToConcert(AddBandToConcertDTO bandToConcertDTO) throws Exception {
        if(!concertRepository.findByName(bandToConcertDTO.getConcertName()).isPresent()){
            throw new Exception("Concert does not exist");
        }
        if(!bandRepository.findByName(bandToConcertDTO.getBandName()).isPresent()){
            throw new Exception("Band does not exist");
        }
        Band band=bandRepository.findByName(bandToConcertDTO.getBandName()).get();
        Concert concert =concertRepository.findByName(bandToConcertDTO.getConcertName()).get();
        band.addConcert(concert);
        concert.addBand(band);
        bandRepository.save(band);
        concertRepository.save(concert);
    }
}
