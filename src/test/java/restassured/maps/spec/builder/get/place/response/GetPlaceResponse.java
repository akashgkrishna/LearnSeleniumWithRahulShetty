package restassured.maps.spec.builder.get.place.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetPlaceResponse {

    public Location location;
    public int accuracy;
    public String name;

    @JsonProperty("phone_number")
    public String phoneNumber;

    public String address;
    public String types;
    public String website;
    public String language;
}