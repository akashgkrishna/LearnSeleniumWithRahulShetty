package restassured.ecom.clients;

import io.restassured.response.Response;
import restassured.ecom.builders.ECommerceBuilder;
import restassured.ecom.models.ApiResponse;
import restassured.ecom.models.auth.LoginRequest;
import restassured.ecom.models.auth.LoginResponse;

import static io.restassured.RestAssured.given;

public class AuthClient {
    public static ApiResponse<LoginResponse> login(LoginRequest loginRequest) {
        Response apiResponse = given()
                .spec(ECommerceBuilder.loginSpec())
                .body(loginRequest)
                .when()
                .post("/api/ecom/auth/login");

        ApiResponse<LoginResponse> response = new ApiResponse<>();
        response.setStatusCode(apiResponse.statusCode());
        response.setBody(apiResponse.as(LoginResponse.class));

        return response;
    }
}
