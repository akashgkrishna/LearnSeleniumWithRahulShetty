package restassured.ecom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.ecom.clients.AuthClient;
import restassured.ecom.clients.ProductClient;
import restassured.ecom.models.ApiResponse;
import restassured.ecom.models.auth.LoginRequest;
import restassured.ecom.models.product.ProductResponse;

public class ProductsTest {
    private static String getToken(String userEmail, String userPassword) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail(userEmail);
        loginRequest.setUserPassword(userPassword);
        return AuthClient.login(loginRequest).getBody().getToken();
    }

    @Test
    public void getAllProductsTest() {
        // Arrange
        String userEmail = "nandume7@gmail.com";
        String userPassword = "Nandu@123";

        String token = getToken(userEmail, userPassword);

        // Act
        ApiResponse<ProductResponse> response = ProductClient.getAllProducts(token);

        // Assert
        Assert.assertEquals(response.getStatusCode(), 200,
                "Status code does not match");
        Assert.assertNotNull(response.getBody().getData(),
                "data should not be null");
        int dataSize = response.getBody().getData().size();
        Assert.assertEquals(response.getBody().getCount(), dataSize,
                "Count does not match");
        Assert.assertEquals(response.getBody().getMessage(), "All Products fetched Successfully",
                "Message does not match");
    }
}
