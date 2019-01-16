package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.RecordInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("recordInfoRepository")
public interface RecordInfoRepository extends JpaRepository<RecordInfo, Long> {

}
