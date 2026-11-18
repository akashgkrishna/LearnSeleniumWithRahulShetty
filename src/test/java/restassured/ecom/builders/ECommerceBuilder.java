package restassured.ecom.builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ECommerceBuilder {
    private static final String BASE_URI = "https://rahulshettyacademy.com";

    public static RequestSpecification loginSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getAllProductsSpec(String authorization) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", authorization)
                .build();
    }
}
