package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.AppointmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("appointmentGroupRepository")
public interface AppointmentGroupRepository extends JpaRepository<AppointmentGroup, Long> {

}
