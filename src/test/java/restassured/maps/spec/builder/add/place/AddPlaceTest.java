package restassured.maps.spec.builder.add.place;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.maps.spec.builder.MapsClient;
import restassured.maps.spec.builder.add.place.request.AddPlaceRequest;
import restassured.maps.spec.builder.add.place.request.Location;
import restassured.maps.spec.builder.add.place.response.AddPlaceResponse;

import java.util.List;

public class AddPlaceTest {
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
    public void addPlaceTest() {
        // Arrange
        AddPlaceRequest addPlaceRequest = getAddPlaceRequest();

        // Act
        Response response = MapsClient.addPlaceClient(addPlaceRequest);
        AddPlaceResponse addPlaceResponse = response.as(AddPlaceResponse.class);

        // Assert
        Assert.assertEquals(response.statusCode(), 200,
                "StatusCode not matching.");
        Assert.assertEquals(addPlaceResponse.getStatus(), "OK",
                "Status is not matching.");
    }
}
