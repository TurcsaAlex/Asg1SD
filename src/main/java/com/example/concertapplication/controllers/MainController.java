package com.example.concertapplication.controllers;

import com.example.concertapplication.models.Band;
import com.example.concertapplication.models.User;
import com.example.concertapplication.models.dto.Band.BandDTO;
import com.example.concertapplication.models.repos.BandRepository;
import com.example.concertapplication.models.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BandRepository bandRepository;

    @PostMapping(path="/band")
    public @ResponseBody Band addNewBand (@RequestBody BandDTO band) {

        Band b= new Band(band.getName(), band.getGenre());
        Band saved=bandRepository.save(b);
        return saved;
    }

    @GetMapping(path="/all")
    public @ResponseBody List<User> getAllUsers() {

        return userRepository.findAll();
    }
}