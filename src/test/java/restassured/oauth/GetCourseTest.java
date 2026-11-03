package restassured.oauth;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        Response getCourseResponse = OAuthClient.getCourse(accessToken);

        // Assert
        String instructor = getCourseResponse.jsonPath().getString("instructor");
        Assert.assertEquals(getCourseResponse.statusCode(),
                401, "The status code is not matching.");
        Assert.assertEquals(instructor, instructorName,
                "Instructor name does not match.");

    }
}
