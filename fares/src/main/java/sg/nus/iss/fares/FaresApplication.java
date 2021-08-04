package sg.nus.iss.fares;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.nus.iss.fares.entity.Fare;
import sg.nus.iss.fares.repository.FaresRepository;

@SpringBootApplication
public class FaresApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(FaresApplication.class);

	@Autowired
	FaresRepository faresRepository;

	public static void main(String[] args) {
		SpringApplication.run(FaresApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Fare[] fares = { new Fare("SQ100", "22-JAN-19", "101"), new Fare("SQ101", "22-JAN-19", "101"),
				new Fare("SQ102", "22-JAN-19", "102"), new Fare("SQ103", "22-JAN-19", "103"),
				new Fare("SQ104", "22-JAN-19", "104"), new Fare("SQ105", "22-JAN-19", "105"),
				new Fare("SQ106", "22-JAN-19", "106") };
		List<Fare> list = Arrays.stream(fares).collect(Collectors.toList());
		list.forEach(fare -> faresRepository.save(fare));

		logger.info("Result: " + faresRepository.getFareByFlightNumberAndFlightDate("SQ101", "22-JAN-19"));

	}

}
