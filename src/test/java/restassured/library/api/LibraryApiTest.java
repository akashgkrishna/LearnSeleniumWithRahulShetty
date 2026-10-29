package restassured.library.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LibraryApiTest {

    @DataProvider
    public static Object[][] books() {
        return new Object[][]{
                {"Learn Appium Automation with Java", "bcd", "227", "John foe"},
                {"Clean Code", "xyz", "991", "Robert Martin"},
                {"Effective Java", "pqr", "512", "Joshua Bloch"}
        };
    }

    @Test(dataProvider = "books")
    public void addBookTest(String name, String isbn, String aisle, String author) {

        // Arrange
        String expectedId = isbn.concat(aisle);

        // Act
        Response response = LibraryApi.addBook(name, isbn, aisle, author);

        // Assert
        int statusCode = response.getStatusCode();
        String id = response.jsonPath().getString("ID");

        Assert.assertEquals(statusCode, 200, "Status code should be 200");
        Assert.assertNotNull(id, "ID should not be null");
        Assert.assertEquals(id, expectedId, "ID should equal isbn + aisle");
    }
}