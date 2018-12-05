package org.ivc.transportation.controllers;

import java.util.Collection;
import org.ivc.transportation.entities.Distance;
import org.ivc.transportation.entities.Waypoint;
import org.ivc.transportation.services.WaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author first
 */
@RestController
public class WaypointController {

    @Autowired
    private WaypointService wpServ;

    @GetMapping("/waypoints")
    public Collection<Waypoint> getAllWaypoints() {
        return wpServ.getWaypoints();
    }

    @GetMapping("/waypoints/{name}")
    public Collection<Waypoint> getWaypointsByName(@PathVariable("name") String s) {
        return wpServ.getWaypointByName(s);
    }

    @PostMapping("/waypoints/create")
    public Collection<Waypoint> addWaypoint(@RequestBody Waypoint w) {
        wpServ.addWaypoint(w);
        return wpServ.getWaypoints();
    }

    @PutMapping("/waypoints/update")
    public Collection<Waypoint> updateWaypoint(@RequestBody Waypoint w) {
        wpServ.updateWaypoint(w, w.getId());
        return wpServ.getWaypoints();
    }

    @DeleteMapping("/waypoints/delete")
    public Collection<Waypoint> delWaypoint(@RequestBody Waypoint w) {
        wpServ.removeWaypoint(w.getId());
        return wpServ.getWaypoints();
    }

    @GetMapping("/waypoints/distances")
    public Collection<Distance> getAllDistances() {
        return wpServ.getDistances();
    }

    @PostMapping("/waypoints/distances/create")
    public Collection<Distance> addDistance(@RequestBody Distance d) {
        wpServ.addDistance(d);
        return wpServ.getDistances();
    }

    @PutMapping("/waypoints/distances/update")
    public Collection<Distance> updateDistance(@RequestBody Distance d) {
        wpServ.updateDistance(d, d.getId());
        return wpServ.getDistances();
    }

    @DeleteMapping("/waypoints/distances/delete")
    public Collection<Distance> delDistance(@RequestBody Distance d) {
        wpServ.removeDistance(d.getId());
        return wpServ.getDistances();
    }

    @GetMapping("/waypoints/distances/{s_w}/{e_w}")
    public Collection<Distance> getDistanceByWaypoints(@PathVariable("s_w") Long start_waypoint, @PathVariable("e_w") Long end_waypoint) {
        return wpServ.getDistanceByStartWaypointAndEndWaypoint(end_waypoint, start_waypoint);
    }
}
