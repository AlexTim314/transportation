package org.ivc.transportation.repositories;

import java.time.ZonedDateTime;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("claimRepository")
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Claim findByRecords(Record record);

    List<Claim> findByDepartmentAndAffirmationDateIsNullAndTemplateNameIsNull(Department department);

    List<Claim> findByDepartmentAndTemplateNameIsNotNull(Department department);

    @Query(value = "select claim.* from claim, record " +
            "where record.start_date between :start_date and :end_date and " +
            "claim.id = record.claim_id and " +
            "claim.department_id = :department_id and " +
            "claim.affirmation_date is not null and " +
            "claim.actual = true " +
            "group by claim.id", nativeQuery = true)
    List<Claim> findAffirmedClaimsByDepartmentTimeFilter(@Param("department_id") Long departmentId,
            @Param("start_date") ZonedDateTime startDate,
            @Param("end_date") ZonedDateTime endDate);

    List<Claim> findByDepartmentAndAffirmationDateIsNotNullAndActualIsTrue(Department department);

    void deleteByIdAndAffirmationDateIsNull(Long id);

    void deleteByIdInAndAffirmationDateIsNull(List<Long> claimIds);
}
