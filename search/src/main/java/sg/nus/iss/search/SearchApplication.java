package sg.nus.iss.search;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.nus.iss.search.entity.Fares;
import sg.nus.iss.search.entity.Flight;
import sg.nus.iss.search.entity.Inventory;
import sg.nus.iss.search.repository.FlightRepository;

@SpringBootApplication
public class SearchApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SearchApplication.class);

	@Autowired
	private FlightRepository flightRepository;

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("SQ100", "SEA", "SFO", "22-JAN-19", new Fares("100", "USD"), new Inventory(100)));
		flights.add(new Flight("SQ101", "NYC", "SFO", "22-JAN-19", new Fares("101", "USD"), new Inventory(100)));
		flights.add(new Flight("SQ102", "NYC", "SFO", "22-JAN-19", new Fares("102", "USD"), new Inventory(100)));
		flights.add(new Flight("SQ105", "NYC", "SFO", "22-JAN-19", new Fares("105", "USD"), new Inventory(100)));
		flights.add(new Flight("SQ106", "NYC", "SFO", "22-JAN-19", new Fares("106", "USD"), new Inventory(100)));
		flights.add(new Flight("SQ102", "CHI", "SFO", "22-JAN-19", new Fares("102", "USD"), new Inventory(100)));
		flights.add(new Flight("SQ103", "HOU", "SFO", "22-JAN-19", new Fares("103", "USD"), new Inventory(100)));
		flights.add(new Flight("SQ104", "LAX", "SFO", "22-JAN-19", new Fares("104", "USD"), new Inventory(100)));

		flightRepository.saveAll(flights);

		logger.info("Looking to load flights...");
		for (Flight flight : flightRepository.findByOriginAndDestinationAndFlightDate("NYC", "SFO", "22-JAN-19")) {
			logger.info(flight.toString());
		}
	}

}
