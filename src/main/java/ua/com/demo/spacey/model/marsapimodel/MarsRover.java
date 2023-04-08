package ua.com.demo.spacey.model.marsapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarsRover {

    private Long id;

    private String name;

    @JsonProperty("landing_date")
    private LocalDate landingDate;

    @JsonProperty("launch_date")
    private LocalDate launchDate;

    private String status;
}
