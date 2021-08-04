package sg.nus.iss.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.book.entity.BookingRecord;

public interface BookingRepository extends JpaRepository<BookingRecord, Long> {
	
}
