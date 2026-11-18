package restassured.maps.spec.builder.add.place.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddPlaceRequest {
    public Location location;
    public int accuracy;
    public String name;
    @JsonProperty("phone_number")
    public String phoneNumber;
    public String address;
    public List<String> types;
    public String website;
    public String language;
}