package com.example.concertapplication.services;

import com.example.concertapplication.models.Band;
import com.example.concertapplication.models.dto.Band.BandDTO;
import com.example.concertapplication.models.repos.BandRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BandService {
    @Autowired
    private BandRepository bandRepository;

    public Band createBand(BandDTO bandDTO) throws Exception {

        Band b=new Band(bandDTO.getName(),bandDTO.getGenre());
        System.out.println(b);
        if(bandRepository.findByName(bandDTO.getName()).isPresent()){
            throw new Exception("Band already exists");
        }
        bandRepository.save(b);
        return b;
    }
    public Band update(BandDTO bandDTO){
        Band b=new Band(bandDTO.getName(),bandDTO.getGenre());
        bandRepository.save(b);
        return b;
    }

    public List<Band> getAllBands(){
        return bandRepository.findAll();
    }

    public void deleteBand(BandDTO bandDTO) throws Exception {
        Band b=new Band(bandDTO.getName(),bandDTO.getGenre());
        if(!bandRepository.findByName(bandDTO.getName()).isPresent()){
            throw new Exception("Band already exists");
        }
        bandRepository.delete(b);
        return;
    }
}
