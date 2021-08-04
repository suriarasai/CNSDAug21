package sg.nus.iss.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.book.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Inventory findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
	
}
