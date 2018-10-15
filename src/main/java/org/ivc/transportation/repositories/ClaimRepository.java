package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Sokolov Slava
 */
@RepositoryRestResource
public interface ClaimRepository extends JpaRepository<Claim, Long>{
}
