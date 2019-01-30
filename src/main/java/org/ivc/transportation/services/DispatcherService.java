package org.ivc.transportation.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alextim
 */
@Service
@Transactional
public class DispatcherService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ClaimRepository claimRepository;
    
    @Autowired
    private UserRepository userRepository;

    public Claim findClaimByRecord(Record record) {
        return claimRepository.findByRecords(record);
    }
    
    public Record findRecordByAppointment(Appointment appointment){
        return recordRepository.findRecordByAppointmentId(appointment.getId());
    }
    
    public List<CompositeClaimRecord> getAppointments(Principal principal) {
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByTransportDep(findTransportDepByUser(principal).getId());
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        appointmentList.forEach(u -> result.add(
                new CompositeClaimRecord(claimRepository.findClaimByAppointmentId(u.getId()),
                        recordRepository.findRecordByAppointmentId(u.getId()), u)
        ));
        return result;
    }
    
    public TransportDep findTransportDepByUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getTransportDep();
        }
        return null;
    }


}
