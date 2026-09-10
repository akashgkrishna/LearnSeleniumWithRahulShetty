package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

/**
 * Test suite demonstrating Selenium locator techniques including
 * ID, Name, CSS Selectors, XPath, and dynamic text extraction.
 */
public class Locators {

    public static void main(String[] args) throws InterruptedException {

        // Setup & Driver Initialization
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/locatorspractice/");

        String username = "Krishna";

        // Step 1: Attempt invalid login using different locator options

        /* Standard By.id alternative:
           driver.findElement(By.id("inputUsername")).sendKeys(username); */

        // Active: CSS ID shortcut (#)
        driver.findElement(By.cssSelector("input#inputUsername")).sendKeys(username);

        /* Standard By.name alternative:
           driver.findElement(By.name("inputPassword")).sendKeys("Krishna");

           XPath contains() partial match alternative:
           driver.findElement(By.xpath("//input[contains(@placeholder,'Pass')]")).sendKeys("Krishna"); */

        // Active: CSS partial attribute match (*=)
        driver.findElement(By.cssSelector("input[placeholder*='Pass']")).sendKeys("Krishna");

        driver.findElement(By.className("submit")).click();
        String text = driver.findElement(By.cssSelector("p.error")).getText();
        System.out.println(text);

        // Step 2: Open "Forgot Password" form, submit details, and extract temporary password
        driver.findElement(By.linkText("Forgot your password?")).click();
        Thread.sleep(3000); // Wait for form slide-in animation
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("johnnybravo@gmail.com");
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("6123456789");
        driver.findElement(By.xpath("//button[@class='reset-pwd-btn']")).click();

        String password = getString(driver);

        // Step 3: Return to main login and authenticate with extracted password
        driver.findElement(By.className("go-to-login-btn")).click();
        Thread.sleep(2000); // Wait for login page transition
        driver.findElement(By.id("inputUsername")).sendKeys(username);
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys(password);
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.className("signInBtn")).click();

        // Step 4: Verify successful login text and dynamic username heading
        Thread.sleep(2000); // Wait for dashboard elements to populate
        String text1 = driver.findElement(By.tagName("p")).getText();
        System.out.println(text1);
        Assert.assertEquals(text1, "You are successfully logged in.");

        String usernameActual = driver.findElement(By.xpath("//div[@class='login-container']/h2")).getText();
        Assert.assertEquals(usernameActual, "Hello " + username + ",");

        // Step 5: Log out and close browser session
        driver.findElement(By.xpath("//button[text()='Log Out']")).click();
        driver.close();
        driver.quit();
    }

    /**
     * Helper method to parse text and extract the temporary password between single quotes.
     *
     * @param driver active WebDriver instance
     * @return the extracted password string
     */
    private static String getString(WebDriver driver) {
        String passwordText = driver.findElement(By.cssSelector("form p")).getText();
        String[] split = passwordText.split("'");
        String password = split[1];
        System.out.println("Extracted Password: " + password);
        return password;
    }
}