import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumIntro {
    public static void main(String[] args) {
        /*Invoking browser (Better as driver manager will automatically find chromedriver online)
        Slower as it will search online->download->then use */
        WebDriver driver = new ChromeDriver();

        // Another way
        // System.setProperty("webdriver.chrome.driver","Give path to chrome driver");

        // Go to particular url
        driver.get("https://rahulshettyacademy.com/");

        // Used to get title of the page
        System.out.println(driver.getTitle());

        // Used to check if we are in correct url
        System.out.println(driver.getCurrentUrl());

        System.out.println(driver.getPageSource());

        driver.quit();

    }
}
