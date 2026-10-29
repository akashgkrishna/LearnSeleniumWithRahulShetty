package restassured.library.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class LibraryApi {

    private static final String BASE_URI = "https://rahulshettyacademy.com";
    private static final String ADD_BOOK = "/Library/Addbook.php";

    public static Response addBook(String name, String isbn, String aisle, String author) {
        String payload = """
                {
                  "name": "%s",
                  "isbn": "%s",
                  "aisle": "%s",
                  "author": "%s"
                }
                """.formatted(name, isbn, aisle, author);

        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(ADD_BOOK)
                .then()
                .extract().response();
    }
}