package ua.com.demo.spacey.model.marsapimodel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarsCamera {

    private Long id;

    private String name;

    @JsonProperty("rover_id")
    private Long roverId;

    @JsonProperty("full_name")
    private String fullName;
}
