/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alextim
 */
@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
    
}
