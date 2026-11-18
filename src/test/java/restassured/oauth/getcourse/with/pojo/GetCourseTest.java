package restassured.oauth.getcourse.with.pojo;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.oauth.OAuthClient;
import restassured.oauth.getcourse.with.pojo.response.GetCourseResponse;

public class GetCourseTest {
    @Test
    public void getCourseTest() {
        // Arrange
        String instructorName = "RahulShetty";
        Response authenticationResponse = OAuthClient.authentication();

        if (authenticationResponse.statusCode() != 200) {
            throw new IllegalStateException(
                    "Test setup failed: Pre-requisite Access token not generated.");
        }
        String accessToken =
                authenticationResponse.jsonPath().getString("access_token");

        // Act
        Response response =
                OAuthClient.getCourse(accessToken);

        GetCourseResponse getCourseResponse = response.as(GetCourseResponse.class);

        // Assert
        Assert.assertEquals(getCourseResponse.getInstructor(), instructorName,
                "Instructor name not matching");

        System.out.println(getCourseResponse.getCourses().getApi().get(0));

    }
}
