package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("claimRepository")
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    
    Claim findByRecords(Record record);
    List<Claim> findByDepartmentAndAffirmationDateIsNull(Department department); 
}
