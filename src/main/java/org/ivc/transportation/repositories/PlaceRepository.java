package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("placeRepository")
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
