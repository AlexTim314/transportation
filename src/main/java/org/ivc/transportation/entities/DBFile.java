/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author alextim
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "file")
public class DBFile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name", length = 255)
    private String fileName;

    @Column(name = "file_type", length = 64)
    private String fileType;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;


    public DBFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = data;
    }

}
