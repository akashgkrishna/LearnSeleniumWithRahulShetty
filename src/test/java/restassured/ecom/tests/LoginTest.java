package restassured.ecom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.ecom.clients.AuthClient;
import restassured.ecom.models.ApiResponse;
import restassured.ecom.models.auth.LoginRequest;
import restassured.ecom.models.auth.LoginResponse;

public class LoginTest {
    @Test
    public static void loginTest() {
        // Arrange
        String userEmail = "nandume7@gmail.com";
        String userPassword = "Nandu@123";

        LoginRequest loginRequest = createLoginPayload(userEmail, userPassword);

        // Act
        ApiResponse<LoginResponse> response = AuthClient.login(loginRequest);

        // Assert
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.getBody().getToken(), "token cannot be empty");
        Assert.assertNotNull(response.getBody().getUserId(), "userId cannot be empty");
        Assert.assertEquals(response.getBody().getMessage(),
                "Login Successfully", "Login not Successful");

    }

    private static LoginRequest createLoginPayload(String userEmail, String userPassword) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail(userEmail);
        loginRequest.setUserPassword(userPassword);
        return loginRequest;
    }
}
