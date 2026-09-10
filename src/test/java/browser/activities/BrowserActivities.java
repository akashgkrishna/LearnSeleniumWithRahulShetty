package browser.activities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Demonstrates basic browser navigation activities in Selenium,
 * including URL loading, history navigation (back/forward), and driver cleanup.
 */
public class BrowserActivities {

    public static void main(String[] args) throws InterruptedException {

        // Setup driver and maximize browser window
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigates to the URL and waits until the page is completely loaded (built-in synchronization)
        driver.get("https://www.google.com/");
        System.out.println("1. " + driver.getTitle());

        // Navigates to another URL while maintaining browser history
        driver.navigate().to("https://rahulshettyacademy.com/locatorspractice/");
        System.out.println("2. " + driver.getTitle());

        // Navigates back to the previous page (Google.com)
        driver.navigate().back();
        System.out.println("3. " + driver.getTitle());

        // Navigates forward to the next page (Rahul Shetty Academy)
        driver.navigate().forward();
        System.out.println("4. " + driver.getTitle());

        // Pause execution briefly to observe navigation
        Thread.sleep(2000);

        // Terminate the WebDriver session and close all browser windows
        driver.quit();
    }
}