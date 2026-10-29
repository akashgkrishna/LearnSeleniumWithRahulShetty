package restassured.maps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Intro {
    public static void main(String[] args) {

        String placeId = postCall(); // Adding new entry
        getCall(placeId); // Getting the  first entry

        String newAddress = "70 winter walk, USA"; // new address to change

        putCall(placeId, newAddress); // Changing the address of the entry
        Response response = getCall(placeId);// Getting the update

        //Checking for the new address
        response.then()
                .assertThat().body("address", equalTo(newAddress));
    }

    private static void putCall(String placeId, String newAddress) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response response = given()
                .body("{\n" +
                        "    \"place_id\": \"" + placeId + "\",\n" +
                        "    \"address\": \"" + newAddress + "\",\n" +
                        "    \"key\": \"qaclick123\"\n" +
                        "}")
                .when()
                .put("https://rahulshettyacademy.com/maps/api/place/update/json");

        response.then().assertThat().statusCode(200);

        System.out.println("\nRESPONSE FROM PUT: \n" + response.asPrettyString());
    }

    public static String postCall() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RequestSpecification headers = given()
//                .log().all()
                .queryParam("key", "qaclick123")
                .headers("Content-Type", "application/json");

        RequestSpecification body = headers.body("{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}");

        Response response = body.when()
                .post("maps/api/place/add/json");

        response.then().assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"));

        JsonPath jsonPath = new JsonPath(response.asString());
        String placeId = jsonPath.getString("place_id");

        System.out.println("RESPONSE OF POST: \n" + response.asPrettyString());

        System.out.println("Place id: " + placeId);
        return placeId;

    }

    public static Response getCall(String placeId) {
        // Given
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        RequestSpecification headers = given()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId);

        // When
        Response response = headers
                .get("maps/api/place/get/json");

        // Then
        response.then()
                .assertThat().statusCode(200);

        System.out.println("\nRESPONSE FROM GET: \n" + response.asPrettyString());
        return response;
    }
}
