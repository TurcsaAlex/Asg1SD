package com.example.concertapplication.models.repos;

import com.example.concertapplication.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcertRepository extends JpaRepository<Concert,Integer> {
    @Override
    Optional<Concert> findById(Integer integer);

    Optional<Concert> findByName(String name);
}
