package io.upschool.repository;

import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import io.upschool.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {

}
