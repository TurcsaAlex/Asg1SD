package com.example.concertapplication.models.repos;

import com.example.concertapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);

    Optional<List<User>> findAllByAuthority(String authority);

}