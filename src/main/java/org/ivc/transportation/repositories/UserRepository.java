package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUserName(String userName);

}
