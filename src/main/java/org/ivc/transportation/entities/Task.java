/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"taskList"})
@EqualsAndHashCode(exclude = {"taskList"})
public class Task implements Serializable {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
    
    @NonNull
    @Column(nullable = false)
    private String status;
    
    @NonNull
    @Column(length = 1024)
    private String reason;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskList taskList;
    
    public Task(String status,String reason,TaskList taskList){
        this.status=status;
        this.reason=reason;
        this.taskList=taskList;
        
    }
}
