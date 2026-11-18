package restassured.ecom.models.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private List<ProductDetails> data;
    private int count;
    private String message;
}
