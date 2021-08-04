package sg.nus.iss.book;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.nus.iss.book.component.BookingComponent;
import sg.nus.iss.book.entity.BookingRecord;
import sg.nus.iss.book.entity.Inventory;
import sg.nus.iss.book.entity.Passenger;
import sg.nus.iss.book.repository.InventoryRepository;

@SpringBootApplication
public class BookApplication implements CommandLineRunner{

	
	private static final Logger logger = LoggerFactory.getLogger(BookApplication.class);
	
	
	@Autowired
	private BookingComponent bookingComponent;
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		
		Inventory[] invs = { 
					new Inventory("SQ100", "22-JAN-19", 100),
					new Inventory("SQ101", "22-JAN-19", 100),
					new Inventory("SQ102", "22-JAN-19", 100),
					new Inventory("SQ103", "22-JAN-19", 100),
					new Inventory("SQ104", "22-JAN-19", 100),
					new Inventory("SQ105", "22-JAN-19", 100),
					new Inventory("SQ106", "22-JAN-19", 100)};
		Arrays.asList(invs).forEach(inventory -> inventoryRepository.save(inventory));
		BookingRecord booking = new BookingRecord("SQ101", "NYC","SFO","22-JAN-19",new Date(),"101");
		Set<Passenger> passengers = new HashSet<Passenger>();
		passengers.add(new Passenger("Dilbert","The Geek","Male", booking));
		passengers.add(new Passenger("Alice","The Geek","Female",booking));
	 	
		booking.setPassengers(passengers);
 		long record  = bookingComponent.book(booking);
		logger.info("Booking successfully saved..." + record);
		logger.info("Looking to load booking record..."); 
	    logger.info("Result: " + bookingComponent.getBooking(record));
	}

}
