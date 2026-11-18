package restassured.maps.add.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.maps.add.AddToMapsClient;
import restassured.maps.add.request.AddToMapsRequest;
import restassured.maps.add.request.Location;
import restassured.maps.add.response.AddToMapsResponse;

import java.util.List;

public class AddToMapsTest {
    @Test
    public void addToMapsTest(){

        AddToMapsRequest addToMapsRequest = new AddToMapsRequest();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addToMapsRequest.setLocation(location);
        addToMapsRequest.setAccuracy(50);
        addToMapsRequest.setName("Frontline house");
        addToMapsRequest.setPhone_number("(+91) 983 893 3937");
        addToMapsRequest.setAddress("29, side layout, cohen 09");
        addToMapsRequest.setTypes(List.of("shoe park", "shop"));
        addToMapsRequest.setWebsite("http://google.com");
        addToMapsRequest.setLanguage("French-IN");

        Response response = AddToMapsClient.addToMaps(addToMapsRequest);
        AddToMapsResponse addToMapsResponse = response.as(AddToMapsResponse.class);

        Assert.assertEquals(response.statusCode(), 200, "Status code did not match");
        System.out.println(addToMapsResponse);

    }
}
