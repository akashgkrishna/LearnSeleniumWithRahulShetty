package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Demonstrates sibling, child, descendant, and parent navigation
 * in Selenium Java using both XPath and CSS Selectors.
 */
public class LocatorsParentSiblingChild {

    public static void main(String[] args) {

        // Initialize Chrome driver and maximize window
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // ==========================================
        // 1. DIRECT CHILD TRAVERSAL
        // XPath uses '/' | CSS uses '>'
        // ==========================================
        String text1WithXpath = driver.findElement(
                By.xpath("//header/div/button[1]")).getText();
        System.out.println("text1WithXpath: " + text1WithXpath);

        String text1WithCss = driver.findElement(
                By.cssSelector("header>div>button:nth-of-type(1)")).getText();
        System.out.println("text1WithCss: " + text1WithCss + "\n");


        // ==========================================
        // 2. IMMEDIATE FOLLOWING SIBLING
        // Target: Button directly after "Practice" (which is "Login")
        // XPath uses 'following-sibling::' | CSS uses '+'
        // ==========================================
        String text2WithXpath = driver.findElement(
                        By.xpath("//header/div/button[1]/following-sibling::button[1]"))
                .getText();
        System.out.println("text2WithXpath: " + text2WithXpath);

        String text2WithCss = driver.findElement(
                By.cssSelector("header>div>button+button")).getText();
        System.out.println("text2WithCss: " + text2WithCss + "\n");


        // ==========================================
        // 3. IMMEDIATE PRECEDING SIBLING
        // Target: Button right before "Signup" (which is "Login")
        // Note: Preceding sibling is supported in XPath ('preceding-sibling::') but NOT in standard CSS
        // ==========================================
        String text3WithXpath = driver.findElement(
                        By.xpath("//header/div/button[3]/preceding-sibling::button[1]"))
                .getText();
        System.out.println("text3WithXpath: " + text3WithXpath + "\n");


        // ==========================================
        // 4. DESCENDANT TRAVERSAL (ANY LEVEL DEEP)
        // Target: 4th Button ("Signup")
        // XPath uses '//' | CSS uses space ' '
        // ==========================================
        String text4WithXpath = driver.findElement(
                By.xpath("(//div//button)[4]")).getText();
        System.out.println("text4WithXpath: " + text4WithXpath);

        // Updated nth-of-type(4) so CSS matches XPath for the 4th button ("Signup")
        String text4WithCss = driver.findElement(
                By.cssSelector("header div button:nth-of-type(4)")).getText();
        System.out.println("text4WithCss: " + text4WithCss + "\n");


        // ==========================================
        // 5. ANY FOLLOWING SIBLING (ANY DISTANCE)
        // Target: Any button element following "Login" (retrieves "Signup")
        // XPath uses 'following-sibling::' | CSS uses '~'
        // ==========================================
        String text5WithXpath = driver.findElement(
                By.xpath("//button[text()='Login']/following-sibling::button")).getText();
        System.out.println("text5WithXpath: " + text5WithXpath);

        String text5WithCss = driver.findElement(
                By.cssSelector("div>button:nth-of-type(2)~button")).getText();
        System.out.println("text5WithCss: " + text5WithCss + "\n");


        // ==========================================
        // 6. PARENT TRAVERSAL (UPWARD MOVEMENT)
        // Target: Navigating up two levels from "Login" button using '/..'
        // Note: Moving up the DOM tree is unique to XPath
        // ==========================================
        String text6WithXpath = driver.findElement(
                By.xpath("//button[text()='Login']/../..")).getText();
        System.out.println("text6WithXpath: " + text6WithXpath);

        // Clean up session
        driver.close();
        driver.quit();
    }
}