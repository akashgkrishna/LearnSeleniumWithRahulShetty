package restassured.maps.add.response;

import lombok.Data;

@Data
public class AddToMapsResponse {
    public String status;
    public String place_id;
    public String scope;
    public String reference;
    public String id;
}
