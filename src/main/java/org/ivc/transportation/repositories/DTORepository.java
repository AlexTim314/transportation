package org.ivc.transportation.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.ivc.transportation.utils.RecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("dtoRepository")
public class DTORepository {

    @Autowired
    private EntityManager em;

    public List<RecordDTO> getDTORecords() {
        Query query = em.createNativeQuery(
                "SELECT r.start_date AS startDate, r.end_date AS endDate "
                + "FROM Record r",
                "RecordDTOMapping");
        List<RecordDTO> result = query.getResultList();
        return result;
    }

}
