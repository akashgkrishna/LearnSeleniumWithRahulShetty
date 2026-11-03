package restassured.jira.create.issues;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.jira.JiraClient;
import restassured.jira.Payload;

public class GetIssueTest {
    @Test
    public void getIssueDetailsTest() {
        // Arrange
        JiraClient jiraClient = new JiraClient();
        String summary = "New To Use GetIssueAPI";
        String description = "Creating a issue to retrieve its data";
        Response createResponse = jiraClient.createIssue(
                Payload.getCreateIssuePayload(summary, description));

        if (createResponse.statusCode() != 201) {
            throw new IllegalStateException("Test setup failed: Prerequisite jira issue is not created");
        }
        String id = createResponse.jsonPath().getString("id");

        // Act
        Response getResponse = jiraClient.getIssue(id);

        // Assert
        String getIssueId = getResponse.jsonPath().getString("id");
        String actualSummary = getResponse.jsonPath().getString("fields.summary");
        String actualDescription = getResponse.jsonPath().getString("fields.description");

        Assert.assertEquals(getResponse.statusCode(), 200, "StatusCode does not match.");
        Assert.assertEquals(getIssueId, id,
                "Id from the created issue and retrieved issue does not match.");
        Assert.assertEquals(actualSummary, summary, "Summary does not match.");
        Assert.assertEquals(actualDescription, description, "Description does not match.");
    }
}
