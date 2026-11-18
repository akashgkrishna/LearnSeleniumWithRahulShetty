package restassured.maps.spec.builder;

import io.restassured.response.Response;
import restassured.maps.spec.builder.add.place.request.AddPlaceRequest;

import static io.restassured.RestAssured.given;

public class MapsClient {
    public static Response addPlaceClient(AddPlaceRequest addPlaceRequest) {
        return given().spec(MapsSpecBuilder.addPlaceSpec())
                .body(addPlaceRequest)
                .post("/maps/api/place/add/json");
    }

    public static Response getPlaceClient(String placeId) {
        return given().spec(MapsSpecBuilder.getPlaceSpec(placeId))
                .get("/maps/api/place/get/json");
    }
}
