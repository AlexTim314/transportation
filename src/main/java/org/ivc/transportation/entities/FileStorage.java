package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author first
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"claim"})
@EqualsAndHashCode(exclude = {"claim"})
public class FileStorage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String fileName;

    @NonNull
    @Column(nullable = false)
    private String fileType;

    @Lob
    private byte[] file;

    @ManyToOne(fetch = FetchType.EAGER)
    private Claim claim;

    public FileStorage(String fileName, String fileType, byte[] file) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.file = file;
    }
    
    public byte[] getData() {
        return file;
    }

}
