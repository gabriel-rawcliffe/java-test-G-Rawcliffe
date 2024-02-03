package nz.co.pfr.art.Music.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "artistid", nullable = false)
    private UUID id;

    private String name;



    @OneToMany(mappedBy = "artist")
    private List<Cd> cds;
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Cd> getCds() {
        return cds;
    }

    public void setCds(List<Cd> cds) {
        this.cds = cds;
    }
}