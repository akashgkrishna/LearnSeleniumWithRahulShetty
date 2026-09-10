package dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Demonstrates handling dynamic dependent dropdowns in Selenium using parent-child
 * XPath relationship scoping to uniquely identify elements in overlapping menus.
 */
public class DynamicDropdowns {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // Open the 'From' (Origin) dropdown menu
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        Thread.sleep(3000);

        // Select 'BLR' (Bengaluru) from the active Origin dropdown
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        Thread.sleep(3000);

        // Select 'COK' (Kochi) from the 'To' (Destination) dropdown.
        // Parent-child scoping (Destination container ID -> link) is used to avoid
        // clicking the hidden 'COK' option in the Origin dropdown.
        driver.findElement(
                By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='COK']")).click();

        Thread.sleep(3000);
        driver.quit();
    }
}