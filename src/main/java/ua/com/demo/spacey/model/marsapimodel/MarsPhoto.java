package ua.com.demo.spacey.model.marsapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarsPhoto {

    private Long id;

    private Integer sol;

    private MarsCamera camera;

    @JsonProperty("img_src")
    private String imgSrc;

    @JsonProperty("earth_date")
    private LocalDate earthDate ;

    private MarsRover rover;
}
