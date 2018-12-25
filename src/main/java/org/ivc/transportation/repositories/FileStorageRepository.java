package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author first
 */
@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {

    List<FileStorage> findByClaimId(Long id);
}
