package org.ivc.transportation.repositories;

import java.time.ZonedDateTime;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("recordRepository")
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Modifying
    @Query(value = "delete from record where claim_id = :claim_id", nativeQuery = true)
    void deleteByClaimId(@Param("claim_id") Long claimId);

    @Query(value = "select record.* from record, appointment where appointment.id = :appointment_id and record.id = appointment.record_id", nativeQuery = true)
    public Record findRecordByAppointmentId(@Param("appointment_id") Long appointmentId);

    @Query(value = "select * from record where start_date between :date_start and :date_end and claim_id = :claim_id", nativeQuery = true)
    List<Record> findByClaimIdAndTimeFilter(@Param("claim_id") Long claimId, @Param("date_start") ZonedDateTime dateStart, @Param("date_end") ZonedDateTime dateEnd);

}
