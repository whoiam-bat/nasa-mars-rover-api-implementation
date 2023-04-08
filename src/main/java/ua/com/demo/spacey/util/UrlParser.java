package ua.com.demo.spacey.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.demo.spacey.model.Preference;
import ua.com.demo.spacey.model.marsapimodel.MarsPhoto;
import ua.com.demo.spacey.model.marsapimodel.MarsRoverApiResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Component
@PropertySource("/application.properties")
public class UrlParser {
    @Value("${api-key}")
    private String API_KEY;

    private final Map<String, List<String>> validCameras = new HashMap<>();

    public UrlParser() {
        validCameras.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
        validCameras.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
        validCameras.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
    }

    public MarsRoverApiResponse getDataFromUrl(Preference preference) throws InvocationTargetException, IllegalAccessException {
        RestTemplate restTemplate = new RestTemplate();

        MarsRoverApiResponse apiResponse = new MarsRoverApiResponse();
        List<MarsPhoto> marsPhotos = new ArrayList<>();

        getUrlEndpoints(preference).forEach(url -> {
            MarsRoverApiResponse tempResponse = restTemplate.getForObject(url, MarsRoverApiResponse.class);
            if (tempResponse != null) marsPhotos.addAll(tempResponse.getPhotos());
        });
        apiResponse.setPhotos(marsPhotos);

        return apiResponse;
    }

    private List<String> getUrlEndpoints(Preference preference) throws InvocationTargetException, IllegalAccessException {

        List<String> urls = new ArrayList<>();

        Method[] methods = preference.getClass().getMethods();

        /*
         * Retrieve all getCamera* methods from Preference object,
         * go through all of these methods and check whether returned value is TRUE
         * then retrieve camera name from getCamera* method.
         * After that we check whether this camera is valid for this rover type,
         * and finally build an url and add it in urls list.
         */
        for (Method method : methods) {
            if (method.getName().contains("getCamera") && Boolean.TRUE.equals(method.invoke(preference))) {
                String cameraName = method.getName().split("getCamera")[1].toUpperCase();
                if (validCameras.get(preference.getRoverType()).contains(cameraName))
                    urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + preference.getRoverType() +
                            "/photos?sol=" + preference.getMarsSol() +
                            "&camera=" + cameraName +
                            "&api_key=" + API_KEY);
            }
        }
        return urls;
    }

    public Map<String, List<String>> getValidCameras() {
        return this.validCameras;
    }
}
