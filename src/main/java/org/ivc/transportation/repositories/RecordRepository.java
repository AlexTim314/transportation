package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.AppointmentGroup;
import org.ivc.transportation.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("recordRepository")
public interface RecordRepository extends JpaRepository<Record, Long> {
    
    List<Record> findRecordsByAppointmentGroup(AppointmentGroup appointmentGroup);

}
