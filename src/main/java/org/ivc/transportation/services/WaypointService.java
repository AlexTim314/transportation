package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Distance;
import org.ivc.transportation.entities.Waypoint;

/**
 *
 * @author first
 */
public interface WaypointService {
    
    public void addWaypoint(Waypoint waypoint);
    
    public void updateWaypoint(Waypoint waypoint, Long id);
    
    public void removeWaypoint(Long id);
    
    public Collection<Waypoint> getWaypoints();
    
    public Optional getWaypointById(Long id);
    
    public Collection<Waypoint> getWaypointByName(String s);
    
    public void addDistance(Distance distance);
    
    public void updateDistance(Distance distance, Long id);
    
    public void removeDistance(Long id);
    
    public Collection<Distance> getDistances();
    
    public Optional getDistanceById(Long id);
    
    public Collection<Distance> getDistanceByStartWaypointAndEndWaypoint(Long start_id, Long end_id);
}
