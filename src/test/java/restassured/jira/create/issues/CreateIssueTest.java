package restassured.jira.create.issues;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.jira.JiraClient;
import restassured.jira.Payload;

import java.io.File;

public class CreateIssueTest {
    @Test
    public void createIssuesAndAttachAFile() {
        // Arrange
        JiraClient jiraClient = new JiraClient();

        String summary = "New Test To Create Issue with Attachment.";
        String description = "Creating an issue with api from restAssured test and attaching a file.";
        File fileToUpload = new File("Files/Screenshot 1.png");

        String createIssuePayload = Payload.getCreateIssuePayload(summary, description);
        Response issue = jiraClient.createIssue(createIssuePayload);

        if (issue.statusCode() != 201) {
            throw new IllegalStateException(
                    "Test Setup Failed: Prerequisite Jira issue could not be created.");
        }

        String id = issue.jsonPath().get("id");

        // Act
        Response response =
                jiraClient.uploadAttachmentToIssue(id, fileToUpload);

        // Assert
        Assert.assertEquals(response.statusCode(),
                200, "Status code must be 200");

    }
}
