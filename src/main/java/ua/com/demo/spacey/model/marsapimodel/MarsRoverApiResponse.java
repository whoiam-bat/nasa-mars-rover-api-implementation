package ua.com.demo.spacey.model.marsapimodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MarsRoverApiResponse {

    private List<MarsPhoto> photos = new ArrayList<>();

}
