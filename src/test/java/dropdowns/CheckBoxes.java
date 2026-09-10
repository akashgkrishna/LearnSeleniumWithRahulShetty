package dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Demonstrates reading checkbox states using {@code isSelected()}
 * and clicking specific discount checkboxes.
 */
public class CheckBoxes {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // Fetch checkbox labels (for text) and input elements (for selection state)
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("#discount-checkbox label"));
        List<WebElement> checkBoxesSelected = driver.findElements(By.cssSelector("#discount-checkbox input"));

        // Print initial selection status (Text: isSelected)
        for (int i = 0; i < checkBoxes.size(); i++) {
            System.out.println(
                    checkBoxes.get(i).getText() + ": " + checkBoxesSelected.get(i).isSelected());
        }

        // ALTERNATIVE DISCOUNT CHECKBOX OPTIONS
//        driver.findElement(By.id("familyandfriend")).click();
//        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();

        // Select 'Student Discount' using partial ID match (*=)
        driver.findElement(By.cssSelector("input[id*='StudentDiscount']")).click();

        System.out.println();
        // Re-print status to verify state change after click
        for (int i = 0; i < checkBoxes.size(); i++) {
            System.out.println(
                    checkBoxes.get(i).getText() + ": " + checkBoxesSelected.get(i).isSelected());
        }

        driver.quit();
    }
}