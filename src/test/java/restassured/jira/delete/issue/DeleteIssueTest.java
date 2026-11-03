package restassured.jira.delete.issue;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.jira.JiraClient;
import restassured.jira.Payload;

public class DeleteIssueTest {
    @Test
    public void deleteIssue() {
        // Arrange
        JiraClient jiraClient = new JiraClient();
        String summary = "New To Use DeleteIssueAPI";
        String description = "Creating a issue to check delete functionality.";

        Response createIssueResponse =
                jiraClient.createIssue(Payload.getCreateIssuePayload(summary, description));
        if (createIssueResponse.statusCode() != 201) {
            throw new IllegalStateException("The issue creation was not successful");
        }

        String issueId = createIssueResponse.jsonPath().getString("id");
        System.out.println(createIssueResponse.asPrettyString());

        // Act
        Response deleteIssueResponse = jiraClient.deleteIssue(issueId);

        // Assert
        Assert.assertEquals(deleteIssueResponse.statusCode(), 204,
                "Status code does not match");
        Response getIssueResponse = jiraClient.getIssue(issueId);
        Assert.assertEquals(getIssueResponse.statusCode(), 404,
                "The issue is still there, the delete failed.");
    }
}
