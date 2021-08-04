package sg.nus.iss.search.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.search.entity.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findByOriginAndDestinationAndFlightDate(String origin,String destination, String flightDate);

	Flight findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
} 