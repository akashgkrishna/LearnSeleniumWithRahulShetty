package random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class WeatherShopperPracticeTest {

    private static final int TEMP_THRESHOLD = 19;

    // Locators
    private static final By TEMPERATURE = By.id("temperature");
    private static final By SUNSCREEN_BUTTON = By.xpath("//button[text()='Buy sunscreens']");
    private static final By MOISTURIZER_BUTTON = By.xpath("//button[text()='Buy moisturizers']");
    private static final By ALL_PRICES_ELEMENT = By.xpath("//div[@class='container']//p[contains(text(),'Price')]");
    private static final By CART_BUTTON = By.cssSelector("button[onclick='goToCart()']");

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyBuyingLowestPricedProductByTemperature() throws InterruptedException {

        driver.get("https://weathershopper.pythonanywhere.com/");

        Integer temperature = findTemperature();
        selectProductByWeather(temperature);
        addProductToCart(findLowestPrice());
        clickOnCart();

//        Thread.sleep(3000);

    }

    public Integer findTemperature() {
        return Integer.parseInt(
                driver.findElement(TEMPERATURE).getText()
                        .replaceAll("[^0-9-]", ""));
    }

    public void selectProductByWeather(Integer temperature) {
        if (temperature > TEMP_THRESHOLD) {
            driver.findElement(SUNSCREEN_BUTTON).click();
        } else {
            driver.findElement(MOISTURIZER_BUTTON).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(ALL_PRICES_ELEMENT));
    }

    public int findLowestPrice() {
        List<WebElement> priceElements = driver.findElements(ALL_PRICES_ELEMENT);

        int lowestPrice = Integer.parseInt(
                priceElements.get(0).getText().replaceAll("[^0-9-]", ""));

        for (WebElement priceElement : priceElements) {
            int indexPrice = Integer.parseInt(
                    priceElement.getText().replaceAll("[^0-9-]", ""));

            if (indexPrice < lowestPrice) {
                lowestPrice = indexPrice;
            }
        }
        return lowestPrice;
    }

    public void addProductToCart(int price) {
        By addToCartButton = By.xpath(
                "//p[contains(text(),'" + price + "')]/following-sibling::button");

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void clickOnCart() {
        wait.until(ExpectedConditions.elementToBeClickable(CART_BUTTON)).click();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
