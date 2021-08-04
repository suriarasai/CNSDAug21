package sg.nus.iss.portal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PortalApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(PortalApplication.class);

	RestTemplate searchClient = new RestTemplate();

	RestTemplate bookingClient = new RestTemplate();

	RestTemplate checkInClient = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		// Search for a flight
		SearchQuery searchQuery = new SearchQuery("NYC", "SFO", "22-JAN-19");
		// Flight[] flights = searchClient.postForObject("http://search-service/search/get", searchQuery,Flight[].class);
		Flight[] flights = searchClient.postForObject("http://localhost:8083/search/get", searchQuery, Flight[].class);

		Arrays.asList(flights).forEach(flight -> logger.info(" flight >" + flight));

		// create a booking only if there are flights.
		if (flights == null || flights.length == 0) {
			return;
		}

		Flight flight = flights[0];
		BookingRecord booking = new BookingRecord(flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(),
				flight.getFlightDate(), null, flight.getFares().getFare());
		Set<Passenger> passengers = new HashSet<Passenger>();
		passengers.add(new Passenger("Dilbert", "The Geek", "Male", booking));
		booking.setPassengers(passengers);
		long bookingId = 0;
		try {
			//bookingId = bookingClient.postForObject("http://book-service/booking/create", booking, long.class);
			bookingId = bookingClient.postForObject("http://localhost:8080/book/create", booking, long.class);
			logger.info("Booking created " + bookingId);
		} catch (Exception e) {
			logger.error("BOOKING SERVICE NOT AVAILABLE...!!!");
		}
		// check in passenger
		if (bookingId == 0)
			return;
		try {
			CheckInRecord checkIn = new CheckInRecord("The Geek", "Dilbert", "28C", null, "SQ101", "23-JAN-18",
					bookingId);
			long checkinId = checkInClient.postForObject("http://localhost:8081/checkin/create", checkIn, long.class);
			logger.info("Checked IN " + checkinId);
		} catch (

		Exception e) {
			logger.error("CHECK IN SERVICE NOT AVAILABLE...!!!");
		}

	}
}
