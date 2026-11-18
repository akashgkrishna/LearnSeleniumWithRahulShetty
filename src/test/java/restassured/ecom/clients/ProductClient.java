package restassured.ecom.clients;

import io.restassured.response.Response;
import restassured.ecom.builders.ECommerceBuilder;
import restassured.ecom.models.ApiResponse;
import restassured.ecom.models.product.ProductResponse;

import static io.restassured.RestAssured.given;

public class ProductClient {
    public static ApiResponse<ProductResponse> getAllProducts(String token) {
        Response apiResponse = given()
                .spec(ECommerceBuilder.getAllProductsSpec(token))
                .when()
                .post("/api/ecom/product/get-all-products");

        ApiResponse<ProductResponse> response = new ApiResponse<>();
        response.setStatusCode(apiResponse.statusCode());
        response.setBody(apiResponse.as(ProductResponse.class));

        return response;
    }
}
