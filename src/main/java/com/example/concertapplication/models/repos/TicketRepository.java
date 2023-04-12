package com.example.concertapplication.models.repos;

import com.example.concertapplication.models.Concert;
import com.example.concertapplication.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Optional<Integer> countAllByConcert(Concert concert);

    @Query("select sum(t.places) from Ticket t where t.concert=?1")
    Optional<Integer> sumPlacesByConcert(Concert concert);
    void deleteById(Integer id);

    List<Ticket> getTicketsByConcert(Concert concert);
}
