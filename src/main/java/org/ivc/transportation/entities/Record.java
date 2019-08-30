package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"appointmentGroup", "route"})
@ToString(exclude = {"appointmentGroup", "route"})
@Entity
@Table(name = "record")
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "entrance_date", nullable = false)
    private LocalDateTime entranceDate;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "affirmation_date")
    private LocalDateTime affirmationDate;

    @Column(name = "carBossName")
    private String carBossName;

    @Column(name = "carBossFirstname")
    private String carBossFirstname;

    @Column(name = "carBossSurname")
    private String carBossSurname;

    @Column(name = "carBoss_post")
    private String carBossPost;

    @Column(name = "carBoss_contacts")
    private String carBossContacts;

    @ManyToOne
    private AppUser affirmator;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id")
    private List<RouteTask> routeTasks;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id")
    private List<Appointment> appointments;

    public Record(Long id, LocalDateTime entranceDate, AppUser affirmator, LocalDateTime startDate,
            LocalDateTime endDate, LocalDateTime affirmationDate, String carBossName, String carBossFirstname,
            String carBossSurname, String carBossPost, String carBossContacts, List<RouteTask> routeTasks, List<Appointment> appointments) {
        this.id = id;
        this.affirmationDate = affirmationDate;
        this.affirmator = affirmator;
        this.appointments = appointments;
        this.carBossName = carBossName;
        this.carBossFirstname = carBossFirstname;
        this.carBossSurname = carBossSurname;
        this.carBossContacts = carBossContacts;
        this.carBossPost = carBossPost;
        this.endDate = endDate;
        this.entranceDate = entranceDate;
        this.routeTasks = routeTasks;
        this.startDate = startDate;
    }

    public Record(Record tempRec) {
        this.id = tempRec.id;
        this.affirmationDate = tempRec.affirmationDate;
        this.affirmator = tempRec.affirmator;
        this.appointments = tempRec.appointments;
        this.carBossFirstname = tempRec.carBossFirstname;
        this.carBossSurname = tempRec.carBossSurname;
        this.carBossContacts = tempRec.carBossContacts;
        this.carBossContacts = tempRec.carBossContacts;
        this.carBossPost = tempRec.carBossPost;
        this.endDate = tempRec.endDate;
        this.entranceDate = tempRec.entranceDate;
        this.routeTasks = tempRec.routeTasks;
        this.startDate = tempRec.startDate;
    }

}
