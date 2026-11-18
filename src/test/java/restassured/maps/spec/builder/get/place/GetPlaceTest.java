package restassured.maps.spec.builder.get.place;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.maps.spec.builder.MapsClient;
import restassured.maps.spec.builder.add.place.request.AddPlaceRequest;
import restassured.maps.spec.builder.add.place.request.Location;
import restassured.maps.spec.builder.add.place.response.AddPlaceResponse;
import restassured.maps.spec.builder.get.place.response.GetPlaceResponse;

import java.util.List;

public class GetPlaceTest {
    private static AddPlaceRequest getAddPlaceRequest() {
        AddPlaceRequest addPlaceRequest = new AddPlaceRequest();

        Location location = new Location();
        location.setLat("-38.383494");
        location.setLng("33.427362");

        addPlaceRequest.setLocation(location);
        addPlaceRequest.setAccuracy(50);
        addPlaceRequest.setName("Orchid Manor");
        addPlaceRequest.setPhoneNumber("(+91) 983 893 3937");
        addPlaceRequest.setAddress("Electronic City");
        addPlaceRequest.setTypes(List.of("Park", "School"));
        addPlaceRequest.setWebsite("www.orcid-manor.com");
        addPlaceRequest.setLanguage("Kannada-KAN");

        return addPlaceRequest;
    }

    @Test
    public void getPlaceTest() {
        // Arrange
        AddPlaceRequest requestPayload = getAddPlaceRequest();
        Response addPlaceResponse = MapsClient.addPlaceClient(requestPayload);

        if (addPlaceResponse.statusCode() != 200) {
            throw new IllegalStateException("The pre-requisite AddPlaceApi failed.");
        }

        AddPlaceResponse addPlacePojo = addPlaceResponse.as(AddPlaceResponse.class);
        String placeId = addPlacePojo.getPlaceId();

        // Act
        Response response = MapsClient.getPlaceClient(placeId);
        GetPlaceResponse getPlaceResponse = response.as(GetPlaceResponse.class);

        // Assert
        Assert.assertEquals(response.statusCode(), 200,
                "StatusCode does not match.");
        Assert.assertEquals(getPlaceResponse.location.getLatitude(), requestPayload.getLocation().getLat(),
                "Latitude does not match");
        Assert.assertEquals(getPlaceResponse.location.getLongitude(), requestPayload.getLocation().getLng(),
                "Longitude does not match");
        Assert.assertEquals(getPlaceResponse.getAccuracy(), requestPayload.getAccuracy(),
                "Accuracy does not match");
        Assert.assertEquals(getPlaceResponse.getName(), requestPayload.getName(),
                "Name does not match");
        Assert.assertEquals(getPlaceResponse.getPhoneNumber(), requestPayload.getPhoneNumber(),
                "Phone number does not match");
        Assert.assertEquals(getPlaceResponse.getAddress(), requestPayload.getAddress(),
                "Address does not match");
        Assert.assertNotNull(getPlaceResponse.getTypes(), "Types is null");
        Assert.assertEquals(getPlaceResponse.getTypes(), String.join(",", requestPayload.getTypes()),
                "Type does not match");
        Assert.assertEquals(getPlaceResponse.getWebsite(), requestPayload.getWebsite(),
                "Website does not match");
        Assert.assertEquals(getPlaceResponse.getLanguage(), requestPayload.getLanguage(),
                "Language does not match");
    }
}
