package restassured.maps.spec.builder.add.place.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddPlaceResponse {
    public String status;

    @JsonProperty("place_id")
    public String placeId;

    public String scope;
    public String reference;
    public String id;
}
