package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.utils.RecordDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("dtoRepository")
public interface DTORepository extends JpaRepository<RecordDTO, Long> {

    @Query(name = "getRecords", nativeQuery = true)
    public List<RecordDTO> getDTORecords();

}
