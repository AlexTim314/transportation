package org.ivc.transportation.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    
    Optional<Department> findByPlanOrder(int num);

    List<Department> findDepartmentsBySuperManagerId(Long id);

    @Query(value = "select department.* from department, claim, record "
            + "where record.start_date between :start_date and :end_date and "
            + "claim.id = record.claim_id and "
            + "claim.affirmation_date is not null and "
            + "claim.actual = true and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsWithAffirmedClaimsByTimeFilter(
            @Param("start_date") LocalDateTime startDate,
            @Param("end_date") LocalDateTime endDate);

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
            @Param("start_date") LocalDateTime startDate,
            @Param("end_date") LocalDateTime endDate,
            @Param("id") Long id);

    @Query(value = "select department.* from department, claim, record "
            + "where record.start_date between :start_date and :end_date and "
            + "claim.id = record.claim_id and "
            + "claim.affirmation_date is not null and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsWithPlannedClaimsByTimeFilter(
            @Param("start_date") LocalDateTime startDate,
            @Param("end_date") LocalDateTime endDate);

    @Query(value = "select department.* from department, claim "
            + "where claim.affirmation_date is not null and "
            + "department.id = claim.department_id "
            + "group by department.id", nativeQuery = true)
    List<Department> findDepartmentsWithPlannedClaims();    

}
