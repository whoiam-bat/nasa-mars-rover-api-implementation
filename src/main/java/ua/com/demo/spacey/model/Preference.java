package ua.com.demo.spacey.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "preference")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private int preferenceId;

    @Column(name = "rover_type")
    private String roverType;

    @Column(name = "mars_sol")
    private Integer marsSol;

    @Column(name = "camera_fhaz")
    private Boolean cameraFhaz;

    @Column(name = "camera_rhaz")
    private Boolean cameraRhaz;

    @Column(name = "camera_mast")
    private Boolean cameraMast;

    @Column(name = "camera_chemcam")
    private Boolean cameraChemcam;

    @Column(name = "camera_mahli")
    private Boolean cameraMahli;

    @Column(name = "camera_mardi")
    private Boolean cameraMardi;

    @Column(name = "camera_navcam")
    private Boolean cameraNavcam;

    @Column(name = "camera_pancam")
    private Boolean cameraPancam;

    @Column(name = "camera_minites")
    private Boolean cameraMinites;

    @OneToMany(mappedBy = "preference")
    @ToString.Exclude
    private Set<Customer> customers = new HashSet<>();

    public Preference(String roverType, Integer marsSol,
                      Boolean cameraFhaz, Boolean cameraRhaz, Boolean cameraMast,
                      Boolean cameraChemcam, Boolean cameraMahli, Boolean cameraMardi,
                      Boolean cameraNavcam, Boolean cameraPancam, Boolean cameraMinites) {
        this.roverType = roverType;
        this.marsSol = marsSol;
        this.cameraFhaz = cameraFhaz;
        this.cameraRhaz = cameraRhaz;
        this.cameraMast = cameraMast;
        this.cameraChemcam = cameraChemcam;
        this.cameraMahli = cameraMahli;
        this.cameraMardi = cameraMardi;
        this.cameraNavcam = cameraNavcam;
        this.cameraPancam = cameraPancam;
        this.cameraMinites = cameraMinites;
    }

    public Preference(String roverType, Integer marsSol) {
        this.roverType = roverType;
        this.marsSol = marsSol;
    }
}
