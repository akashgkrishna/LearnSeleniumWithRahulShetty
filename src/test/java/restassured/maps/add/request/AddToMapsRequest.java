package restassured.maps.add.request;

import lombok.Data;

import java.util.List;

@Data
public class AddToMapsRequest {
    Location location;
    int accuracy;
    String name;
    String phone_number;
    String address;
    List<String> types;
    String website;
    String language;
}
