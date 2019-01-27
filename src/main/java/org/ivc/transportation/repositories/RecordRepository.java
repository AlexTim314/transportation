package org.ivc.transportation.repositories;

import java.util.List;
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

    void deleteByIdIn(List<Long> recordIds);

    @Modifying
    @Query(value = "delete r from Record r where r.claim_id = :claim_id", nativeQuery = true)
    void deleteByClaimId(@Param("claim_id") Long claimId);

}
