package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("carBossRepository")
public interface CarBossRepository extends JpaRepository<CarBoss, Long> {

    List<CarBoss> findByDepartment(Department department);

}
