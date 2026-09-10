package dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Demonstrates handling static HTML {@code <select> </select>} dropdowns using Selenium's {@code Select} class,
 * as well as automating custom UI passenger selection popups.
 */
public class StaticDropdowns {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        System.out.println("Page Title: " + driver.getTitle());

        // =========================================================================
        // PART 1: STATIC HTML <select> DROPDOWN (Currency Dropdown)
        // =========================================================================

        WebElement inrDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));

        // Select class only works for standard HTML <select> elements
        Select select = new Select(inrDropdown);

        // getFirstSelectedOption() returns the currently active option
        System.out.println("Default: " + select.getFirstSelectedOption().getText());

        // selectByIndex uses 0-based indexing (Index 3 = 4th option, USD)
        select.selectByIndex(3);
        System.out.println("By Index (3): " + select.getFirstSelectedOption().getText());

        Thread.sleep(2000);

        // selectByValue matches the HTML value attribute (<option value="AED">)
        select.selectByValue("AED");
        System.out.println("By Value ('AED'): " + select.getFirstSelectedOption().getText());


        // =========================================================================
        // PART 2: CUSTOM UI DROPDOWN / POPUP (Passenger Selection)
        // =========================================================================

        System.out.println("Initial: " + driver.findElement(By.id("divpaxinfo")).getText());
        driver.findElement(By.id("divpaxinfo")).click();

        Thread.sleep(2000);

        // Loop 2 times to add 2 adults (1 default + 2 added = 3 total)
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.xpath("//span[@id='hrefIncAdt']")).click();
        }

        driver.findElement(By.xpath("//input[@value='Done']")).click();
        System.out.println("Updated: " + driver.findElement(By.id("divpaxinfo")).getText());

        Thread.sleep(1000);
        driver.close();
        driver.quit();
    }
}