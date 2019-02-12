package org.ivc.transportation.repositories;

import java.time.ZonedDateTime;
import java.util.List;
import org.ivc.transportation.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Sokolov Slava
 */
@Repository("departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByShortname(String shortname);

    List<Department> findDepartmentsBySuperManagerId(Long id);

    @Query(value = "select department.* from department, claim, record "
            + "where record.start_date between :start_date and :end_date and "
            + "claim.id = record.claim_id and "
            + "claim.affirmation_date is not null and "
            + "claim.actual = true and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsWithAffirmedClaimsByTimeFilter(
            @Param("start_date") ZonedDateTime startDate,
            @Param("end_date") ZonedDateTime endDate);

    @Query(value = "select department.* from department, claim "
            + "where claim.affirmation_date is not null and "
            + "claim.actual = true and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsWithAffirmedClaims();

    @Query(value = "select department.* from department, claim "
            + "where claim.affirmation_date is not null and "
            + "claim.actual = true and "
            + "department.super_manager_id = :id and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsBySuperManagerWithAffirmedClaims(
            @Param("id") Long id);

    @Query(value = "select department.* from department, claim, record "
            + "where record.start_date between :start_date and :end_date and "
            + "claim.id = record.claim_id and "
            + "claim.affirmation_date is not null and "
            + "claim.actual = true and "
            + "department.super_manager_id = :id and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsBySuperManagerWithAffirmedClaimsByTimeFilter(
            @Param("start_date") ZonedDateTime startDate,
            @Param("end_date") ZonedDateTime endDate,
            @Param("id") Long id);

    @Query(value = "select department.* from department, claim, record "
            + "where record.start_date between :start_date and :end_date and "
            + "claim.id = record.claim_id and "
            + "claim.affirmation_date is not null and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsWithPlannedClaimsByTimeFilter(
            @Param("start_date") ZonedDateTime startDate,
            @Param("end_date") ZonedDateTime endDate);

    @Query(value = "select department.* from department, claim "
            + "where claim.affirmation_date is not null and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsWithPlannedClaims();

}
