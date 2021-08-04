package sg.nus.iss.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.checkin.entity.CheckInRecord;

public interface CheckinRepository extends JpaRepository<CheckInRecord,Long> {

}
