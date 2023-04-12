package com.example.concertapplication.models.repos;

import com.example.concertapplication.models.Band;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BandRepository extends JpaRepository<Band,Integer> {

    @Override
    Optional<Band> findById(Integer integer);

    Optional<Band> findByName(String name);


}
