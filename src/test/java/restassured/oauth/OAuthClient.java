package restassured.oauth;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OAuthClient {
    private static final String BASE_URL = "https://rahulshettyacademy.com";

    public static Response authentication() {
        return given().baseUri(BASE_URL)
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when()
                .post("/oauthapi/oauth2/resourceOwner/token");
    }

    public static Response getCourse(String accessToken) {
        return given().baseUri(BASE_URL)
                .queryParam("access_token", accessToken)
                .when()
                .get("/oauthapi/getCourseDetails");
    }
}