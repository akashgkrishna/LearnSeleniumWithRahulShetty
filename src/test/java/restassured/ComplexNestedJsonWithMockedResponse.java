package restassured;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
/*
1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount
 */

public class ComplexNestedJsonWithMockedResponse {
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(mockResponse());

        System.out.print("1. Print No of courses returned by API: ");
        int courseSize = jsonPath.getInt("courses.size()");
        System.out.println(jsonPath.getInt("courses.size()"));

        System.out.print("\n2. Print Purchase Amount: ");
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        System.out.print("\n3. Print Title of the first course: ");
        System.out.println(
                jsonPath.getString("courses[0].title")
        );

        System.out.print("\n4. Print All course titles and their respective Prices: \n");
        for (int i = 0; i < courseSize; i++) {
            System.out.print(jsonPath.getString("courses[" + i + "].title")
                    + " " + jsonPath.getString("courses[" + i + "].price") + "\n");
        }

        System.out.print("\n5. Print no of copies sold by RPA Course: ");
        for (int i = 0; i < courseSize; i++) {
            if (jsonPath.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
                System.out.print(jsonPath.getString("courses[" + i + "].copies") + "\n");
            }
        }

        System.out.println("\n6. Verify if Sum of all Course prices matches with Purchase Amount: ");

        int totalPurchaseAmount = totalPurchaseAmount(jsonPath);
        System.out.println("PurchaseAmount: " + purchaseAmount);
        System.out.println("Total PurchaseAmount after multiply: " + totalPurchaseAmount);
        Assert.assertEquals(purchaseAmount, totalPurchaseAmount);

    }

    public static String mockResponse() {
        return """
                {
                  "dashboard": {
                    "purchaseAmount": 910,
                    "website": "rahulshettyacademy.com"
                  },
                  "courses": [
                    {
                      "title": "Selenium Python",
                      "price": 50,
                      "copies": 6
                    },
                    {
                      "title": "Cypress",
                      "price": 40,
                      "copies": 4
                    },
                    {
                      "title": "RPA",
                      "price": 45,
                      "copies": 10
                    }
                  ]
                }""";
    }

    public static int totalPurchaseAmount(JsonPath jsonPath) {
        int sum = 0;
        for (int i = 0; i < jsonPath.getInt("courses.size()"); i++) {
            int price = jsonPath.getInt("courses[" + i + "].price");
            int copies = jsonPath.getInt("courses[" + i + "].copies");
            sum += (price * copies);
        }
        return sum;
    }
}
