package org.ivc.transportation.services;

import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alextim
 */
@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> findByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }

}
