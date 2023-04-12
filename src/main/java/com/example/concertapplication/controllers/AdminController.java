package com.example.concertapplication.controllers;

import com.example.concertapplication.models.Band;
import com.example.concertapplication.models.Concert;
import com.example.concertapplication.models.Ticket;
import com.example.concertapplication.models.User;
import com.example.concertapplication.models.dto.Band.BandDTO;
import com.example.concertapplication.models.dto.Concert.AddBandToConcertDTO;
import com.example.concertapplication.models.dto.Concert.ConcertDTO;
import com.example.concertapplication.models.dto.User.EmailDTO;
import com.example.concertapplication.models.dto.User.UserSignUpDTO;
import com.example.concertapplication.services.AdminService;
import com.example.concertapplication.services.BandConcertService;
import com.example.concertapplication.services.BandService;
import com.example.concertapplication.services.ConcertService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private BandService bandService;
    private ConcertService concertService;
    private BandConcertService bandConcertService;

    @GetMapping("/all-cashiers")
    public @ResponseBody List<User> getAllCashiers(){
        return adminService.getAllCashiers();
    }

    @PostMapping("/create-cashier")
    public Map<String, String> createCashier(@RequestBody UserSignUpDTO userSignUpDTO) {
        return buildJWTResponse(adminService.createCashier(userSignUpDTO));
    }

    @PutMapping("/update-cashier")
    public @ResponseBody Map<String, String> updateCashier(@RequestBody UserSignUpDTO userSignUpDTO){
        return buildJWTResponse(adminService.updateCashier(userSignUpDTO));
    }

    @DeleteMapping("/delete-cashier")
    public ResponseEntity<?>  deleteCashier(@RequestBody EmailDTO email){
        adminService.deleteCashier(email.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/band")
    public @ResponseBody Band createBand(@RequestBody BandDTO bandDTO){
        try {
            return bandService.createBand(bandDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/band")
    public @ResponseBody List<Band> getAllBands(){
        return bandService.getAllBands();
    }

    @PutMapping("/band")
    public @ResponseBody Band updateBand(@RequestBody BandDTO bandDTO){
        try {
            return bandService.update(bandDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/band")
    public @ResponseBody ResponseEntity<?> deleteBand(@RequestBody BandDTO bandDTO){
        try {
            bandService.deleteBand(bandDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/concert")
    public @ResponseBody Concert createConcert(@RequestBody ConcertDTO concertDTO){
        try {
            return concertService.createConcert(concertDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/concert")
    public @ResponseBody List<Concert> getAllConcerts(){
        return concertService.getAllConcerts();
    }

    @PutMapping("/concert")
    public @ResponseBody Concert updateConcert(@RequestBody ConcertDTO concertDTO){
        try {
            return concertService.updateConcert(concertDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/concert")
    public @ResponseBody ResponseEntity<?> deleteConcert(@RequestBody ConcertDTO concertDTO){
        try {
            concertService.deleteConcert(concertDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addBandConcert")
    public @ResponseBody ResponseEntity<?> addBandToConcert(@RequestBody AddBandToConcertDTO addBandToConcertDTO){
        try {
            bandConcertService.addBandToConcert(addBandToConcertDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Map<String, String> buildJWTResponse(String token) {
        HashMap<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @GetMapping("/tickets")
    public @ResponseBody List<Ticket> getAllTicketsFromConcert(ConcertDTO concertDTO){
        return adminService.getAllTicketsFromConcert(concertDTO);
    }
}
