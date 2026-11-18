package restassured.maps.add;

import io.restassured.response.Response;
import restassured.maps.add.request.AddToMapsRequest;

import static io.restassured.RestAssured.given;

public class AddToMapsClient {
    private static final String BASE_URL = "https://rahulshettyacademy.com";

    public static Response addToMaps(AddToMapsRequest addToMapsRequest) {
        return given().baseUri(BASE_URL)
                .queryParam("key", "qaclick123")
                .body(addToMapsRequest)
                .when().post("/maps/api/place/add/json");
    }
}
