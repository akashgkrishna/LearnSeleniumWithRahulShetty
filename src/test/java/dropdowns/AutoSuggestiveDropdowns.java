package dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Demonstrates handling dynamic auto-suggestive dropdowns in Selenium by
 * entering search text, capturing option elements, and selecting a target item.
 */
public class AutoSuggestiveDropdowns {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // Type search text into auto-suggest box to trigger suggestions
        Thread.sleep(3000);
        driver.findElement(By.id("autosuggest")).sendKeys("Ind");

        // Allow time for AJAX/dynamic suggestions to render in the DOM
        Thread.sleep(3000);

        // Fetch all generated suggestion list elements
        List<WebElement> options = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));

        // Iterate through suggestions to locate target match
        for (WebElement option : options) {
            // Read text once and store locally to avoid stale or empty DOM reads
            String optionText = option.getText();

            if (optionText.equalsIgnoreCase("India")) {
                option.click();
                System.out.println("Clicked on " + optionText);

                // Exit loop immediately after click because clicking closes the dropdown menu
                break;
            }
        }

        Thread.sleep(3000);
        driver.quit();
    }
}