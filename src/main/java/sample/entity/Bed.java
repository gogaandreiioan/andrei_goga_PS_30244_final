package sample.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int number;
    @OneToMany(mappedBy = "bed")
    private Set<Hospitalization> hospitalizations;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "saloon_id", nullable = false)
    private Saloon saloon;
    private Boolean isOccupied;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Hospitalization> getHospitalizations() {
        return hospitalizations;
    }

    public void setHospitalizations(Set<Hospitalization> hospitalization) {
        this.hospitalizations = hospitalization;
    }

    public Saloon getSaloon() {
        return saloon;
    }

    public void setSaloon(Saloon saloon) {
        this.saloon = saloon;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }
}
