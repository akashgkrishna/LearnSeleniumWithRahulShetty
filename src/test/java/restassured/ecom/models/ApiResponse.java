package restassured.ecom.models;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int statusCode;
    private T body;
}
