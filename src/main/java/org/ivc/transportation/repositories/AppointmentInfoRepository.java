package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.AppointmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("appointmentInfoRepository")
public interface AppointmentInfoRepository extends JpaRepository<AppointmentInfo, Long> {

}
