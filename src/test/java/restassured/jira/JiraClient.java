package restassured.jira;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraClient {
    private static final String BASE_URI = "https://kriyalabs.atlassian.net";
    // Access token only valid till 04/11/2026
    private static final String BEARER_TOKEN =
            "Basic bmFuZHVtZTdAZ21haWwuY29tOkFUQVRUM3hGZkdGMHRWOWloUGs1SDJOVmFKUlozNnk0MHowQzRyVnBSaWpFbWRrR0VHdkVMN3dMSlVseDFCVEhjUGMyYUZyNFI4cnNISkRqaW5OeGVZRElaZDc3blBmX0NQbVAyRVI1YVM4c081Rnl6cUZsQzNxeU9DYlB4ODBUajc3eGdsZXJwZV83OEQtNzVURnB4Z3pQb0Z6QTZmeEhiRDNzM1JERzlGdDhSN3pvMmJQUTlYbz1FOEFFMjQ5OQ==";

    public Response createIssue(String payload) {
        return given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .header("Authorization", BEARER_TOKEN)
                .body(payload)
                .when()
                .post("/rest/api/2/issue")
                .then()
                .extract().response();
    }

    public Response uploadAttachmentToIssue(String issueId, File fileToUpload) {
        return given()
                .baseUri(BASE_URI)
                .header("X-Atlassian-Token", "no-check")
                .header("Authorization", BEARER_TOKEN)
                .pathParam("issueId", issueId)
                .multiPart("file", fileToUpload)
                .when()
                .post("/rest/api/3/issue/{issueId}/attachments")
                .then().extract().response();
    }

    public Response getIssue(String issueId) {
        return given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .header("Authorization", BEARER_TOKEN)
                .pathParam("issueId", issueId)
                .when()
                .get("/rest/api/2/issue/{issueId}")
                .then().extract().response();
    }

    public Response deleteIssue(String issueId) {
        return given()
                .baseUri(BASE_URI)
                .header("Authorization", BEARER_TOKEN)
                .pathParam("issueId", issueId)
                .when()
                .delete("/rest/api/3/issue/{issueId}")
                .then().extract().response();
    }
}
