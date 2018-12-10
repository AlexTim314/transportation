package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Distance;
import org.ivc.transportation.entities.Waypoint;
import org.ivc.transportation.repositories.DistanceRepository;
import org.ivc.transportation.repositories.WaypointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author first
 */
@Service
@Transactional
public class WaypointServiceImpl implements WaypointService {
    
    @Autowired
    private WaypointRepository wpRep;
    @Autowired
    private DistanceRepository distRep;
    
    @Override
    @Transactional
    public void addWaypoint(Waypoint waypoint) {
        wpRep.save(waypoint);
    }
    
    @Override
    @Transactional
    public void updateWaypoint(Waypoint waypoint, Long id) {
        waypoint.setId(id);
        wpRep.save(waypoint);       
    }
    
    @Override
    @Transactional
    public void removeWaypoint(Long id) {
        wpRep.deleteById(id);
    }

    @Override
    @Transactional
    public void addDistance(Distance distance) {
        distRep.save(distance);
    }

    @Override
    @Transactional
    public void updateDistance(Distance distance, Long id) {
        distance.setId(id);
        distRep.save(distance);
    }

    @Override
    @Transactional
    public void removeDistance(Long id) {
       distRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<Waypoint> getWaypoints() {
       return wpRep.findAll();
    }

    @Override
    @Transactional
    public Optional getWaypointById(Long id) {       
        return wpRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Waypoint> getWaypointByName(String s) {
        return wpRep.findByName(s);       
    }

    @Override
    @Transactional
    public Collection<Distance> getDistances() {
       return distRep.findAll();
    }

    @Override
    @Transactional
    public Optional getDistanceById(Long id) {
        return distRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Distance> getDistanceByStartWaypointAndEndWaypoint(Long end_id, Long start_id) {
       return distRep.findByEndPointIdAndStartPointId(end_id, start_id);
    }
    
}
