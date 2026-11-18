package restassured.ecom.models.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDetails {
    @JsonProperty("_id")
    private String id;
    private String productName;
    private String productCategory;
    private String productSubCategory;
    private int productPrice;
    private String productDescription;
    private String productImage;
    private String productRating;
    private String productTotalOrders;
    private boolean productStatus;
    private String productFor;
    private String productAddedBy;
    @JsonProperty("__v")
    private String v;
}
