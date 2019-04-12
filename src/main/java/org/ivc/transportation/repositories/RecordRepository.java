package org.ivc.transportation.repositories;

import java.time.LocalDateTime;
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

    @Query(value = "select record.* from record, claim where " +
            "claim.department_id = :department_id and " +
            "claim.affirmation_date is not null and " +
            "claim.actual = true and " +
            "record.claim_id = claim.id order by record.start_date", nativeQuery = true)
    List<Record> findByDepartmentIdAndAffirmationDateIsNotNullAndActualIsTrue(@Param("department_id") Long departmentId);

    @Query(value = "select record.* from record, claim where " +
            "claim.department_id = :department_id and " +
            "claim.affirmation_date is not null and " +
            "record.claim_id = claim.id order by record.start_date", nativeQuery = true)
    List<Record> findByDepartmentIdAndAffirmationDateIsNotNullPlanned(@Param("department_id") Long departmentId);

    @Query(value = "select record.* from record, claim where " +
            "claim.department_id = :department_id and " +
            "claim.affirmation_date is not null and " +
            "claim.actual = true and " +
            "record.start_date between :date_start and :date_end and " +
            "record.claim_id = claim.id order by record.start_date", nativeQuery = true)
    List<Record> findByDepartmentIdAndAffirmationDateIsNotNullAndActualIsTrueTimeFilter(@Param("department_id") Long departmentId,
            @Param("date_start") LocalDateTime dateStart, @Param("date_end") LocalDateTime dateEnd);

    @Query(value = "select record.* from record, claim where " +
            "claim.department_id = :department_id and " +
            "claim.affirmation_date is not null and " +
            "record.start_date between :date_start and :date_end and " +
            "record.claim_id = claim.id order by record.start_date", nativeQuery = true)
    List<Record> findByDepartmentIdAndAffirmationDateIsNotNullTimeFilterPlanned(@Param("department_id") Long departmentId,
            @Param("date_start") LocalDateTime dateStart, @Param("date_end") LocalDateTime dateEnd);
}
