package restassured.maps.spec.builder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class MapsSpecBuilder {
    private static final String BASE_URL = "https://rahulshettyacademy.com";
    private static final String KEY = "qaclick123";

    public static RequestSpecification addPlaceSpec() {
        return new RequestSpecBuilder().setBaseUri(BASE_URL)
                .addQueryParam("key", KEY)
                .build();
    }

    public static RequestSpecification getPlaceSpec(String placeId) {
        return new RequestSpecBuilder().setBaseUri(BASE_URL)
                .addQueryParam("key", KEY)
                .addQueryParam("place_id", placeId)
                .build();
    }
}
