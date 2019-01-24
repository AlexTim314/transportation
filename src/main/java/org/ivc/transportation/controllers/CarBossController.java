package org.ivc.transportation.controllers;

import org.ivc.transportation.services.CarBossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class CarBossController {

    @Autowired
    private CarBossService carBossService;

}
