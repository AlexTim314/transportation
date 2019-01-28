package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("recordRepository")
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "delete from record where claim_id = :claim_id", nativeQuery = true)
    void deleteByClaimId(@Param("claim_id") Long claimId);

    @Query(value = "select record.* from record, appointment where record.id = appointment.record_id and appointment.id = :appointment_id", nativeQuery = true)
    public Record findRecordByAppointmentId(@Param("appointment_id") Long appointmentId);
}
