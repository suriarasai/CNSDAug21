package sg.nus.iss.fares.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.fares.entity.Fare;

public interface FaresRepository extends JpaRepository<Fare,Long> {
	Fare getFareByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
