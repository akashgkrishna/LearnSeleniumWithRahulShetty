package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Demonstrates advanced positional indexing (first, last, nth-last)
 * and exclude conditions using XPath and CSS Selectors.
 */
public class LocatorsPositionalAndFilter {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // ==========================================
        // 1. FIRST MATCHING ELEMENT
        // Target: First button inside header ("Home")
        // XPath: (//header//button)[1]
        // CSS:   header button:first-of-type
        // ==========================================
        String text1WithXpath = driver.findElement(
                By.xpath("(//header//button)[1]")).getText();
        System.out.println("1. First button (XPath): " + text1WithXpath);

        String text1WithCss = driver.findElement(
                By.cssSelector("header button:first-of-type")).getText();
        System.out.println("1. First button (CSS): " + text1WithCss + "\n");


        // ==========================================
        // 2. LAST MATCHING ELEMENT
        // Target: Last button inside header ("Signup")
        // XPath: (//header//button)[last()]
        // CSS:   header button:last-of-type
        // ==========================================
        String text2WithXpath = driver.findElement(
                By.xpath("(//header//button)[last()]")).getText();
        System.out.println("2. Last button (XPath): " + text2WithXpath);

        String text2WithCss = driver.findElement(
                By.cssSelector("header div > button:last-of-type")).getText();
        System.out.println("2. Last button (CSS): " + text2WithCss + "\n");


        // ==========================================
        // 3. 2ND FROM LAST ELEMENT
        // Target: 2nd button from the end inside header ("Login")
        // XPath: (//header//button)[last()-1]
        // CSS:   header button:nth-last-of-type(2)
        // ==========================================
        String text3WithXpath = driver.findElement(
                By.xpath("(//header//button)[last()-1]")).getText();
        System.out.println("3. 2nd from last button (XPath): " + text3WithXpath);

        String text3WithCss = driver.findElement(
                By.cssSelector("header button:nth-last-of-type(2)")).getText();
        System.out.println("3. 2nd from last button (CSS): " + text3WithCss + "\n");


        // ==========================================
        // 4. EXCLUDE CONDITION (:not / not())
        // Target: Header link <a> that does NOT open in a new tab (target!='_blank')
        // XPath: //header/a[not(@target='_blank')]
        // CSS:   header > a:not([target='_blank'])
        // ==========================================
        String href4WithXpath = driver.findElement(
                By.xpath("//header/a[not(@target='_blank')]")).getAttribute("href");
        System.out.println("4. Excluded link href (XPath): " + href4WithXpath);

        String href4WithCss = driver.findElement(
                By.cssSelector("header > a:not([target='_blank'])")).getAttribute("href");
        System.out.println("4. Excluded link href (CSS): " + href4WithCss + "\n");

        // Clean up session
        driver.close();
        driver.quit();
    }
}