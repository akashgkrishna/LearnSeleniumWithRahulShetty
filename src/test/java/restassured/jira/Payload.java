package restassured.jira;

public class Payload {
    public static String getCreateIssuePayload(String summary, String description) {
        return """
                {
                    "fields": {
                        "project": {
                            "key": "TEST"
                        },
                        "summary": "%s",
                        "description": "%s",
                        "issuetype": {
                            "name": "Story"
                        }
                    }
                }
                """.formatted(summary, description);
    }
}
