package random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class WeatherShopperPractice1Test {

    private static final int TEMP_THRESHOLD = 19;

    // Locators
    private static final By TEMPERATURE = By.id("temperature");
    private static final By SUNSCREEN_BUTTON = By.xpath("//button[text()='Buy sunscreens']");
    private static final By MOISTURIZER_BUTTON = By.xpath("//button[text()='Buy moisturizers']");
    private static final By ALL_PRICES_ELEMENT = By.xpath("//div[@class='container']//p[contains(text(),'Price')]");
    private static final By CART_BUTTON = By.cssSelector("button[onclick='goToCart()']");
    private static final By CARD_TYPE = By.xpath("//select[@class='form-control']");
    private static final By GENERATE_CARD_BUTTON = By.xpath("//button[@name='generate']");
    private static final By FRONT_CARD = By.cssSelector(".card-front pre");
    private static final By BACK_CARD = By.cssSelector(".card-back pre");
    private static final By PAYMENT_PAGE_IFRAME = By.className("stripe_checkout_app");
    private static final By PAY_WITH_CARD_BUTTON = By.cssSelector("button[type='submit']");
    private static final By ENTER_CARD_EMAIL = By.id("email");
    private static final By ENTER_CARD_NUMBER = By.id("card_number");
    private static final By ENTER_CARD_EXPIRY = By.id("cc-exp");
    private static final By ENTER_CVV = By.id("cc-csc");
    private static final By ENTER_POSTCODE = By.id("billing-zip");
    private static final By PAY_BUTTON = By.className("iconTick");
    private static final By PAYMENT_SUCCESS_PAGE_HEADER = By.xpath("//h2");

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

        Map<String, String> cardDetails = gettingCardInfo("MasterCard");

        Thread.sleep(3000);
        enterCardDetails(cardDetails);
        clickOnPayButton(cardDetails);

        driver.switchTo().defaultContent();
        Thread.sleep(5000);

        Assert.assertEquals(getHeading(), "PAYMENT SUCCESS");


    }

    public Integer findTemperature() {
        return Integer.parseInt(
                driver.findElement(TEMPERATURE).getText()
                        .replaceAll("[^0-9-]", ""));
    }

    public void selectProductByWeather(Integer temperature) {
        System.out.println("TEMPERATURE IS: " + temperature);
        if (temperature > TEMP_THRESHOLD) {
            driver.findElement(SUNSCREEN_BUTTON).click();
            System.out.println("OPENING SUNSCREEN");
        } else {
            driver.findElement(MOISTURIZER_BUTTON).click();
            System.out.println("OPENING MOISTURIZER");
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
        System.out.println("LOWEST PRICE: " + lowestPrice);
        return lowestPrice;
    }

    public void addProductToCart(int price) {
        By addToCartButton = By.xpath(
                "//p[contains(text(),'" + price + "')]/following-sibling::button");

        String productName = driver.findElement(By.xpath(
                "//p[contains(text(),'" + price + "')]/preceding-sibling::*[1]")).getText();

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        System.out.println("CLICKED ON: " + productName);
    }

    public void clickOnCart() {
        wait.until(ExpectedConditions.elementToBeClickable(CART_BUTTON)).click();
        System.out.println("CLICKED ON CART BUTTON");
    }

    public Map<String, String> gettingCardInfo(String cardTypeName) throws InterruptedException {
        String currentWindowHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://neapay.com/online-tools/credit-card-number-generator-validator.html");

        Select cardType = new Select(
                driver.findElement(CARD_TYPE));

        cardType.selectByVisibleText(cardTypeName);

        driver.findElement(GENERATE_CARD_BUTTON).click();

        Thread.sleep(2000);
        List<WebElement> cardFrontDetails = driver.findElements(FRONT_CARD);


        String email = cardFrontDetails.get(2).getText()
                .replaceAll(" ", "")
                .toLowerCase()
                .concat("@gmail.com");

        String cardNumber = cardFrontDetails.get(0).getText()
                .replaceAll("[^0-9]", "");

        String[] expiry = cardFrontDetails.get(1).getText()
                .split("/");

        String cvv = driver.findElement(BACK_CARD).getText()
                .trim();

        driver.close();
        driver.switchTo().window(currentWindowHandle);

        return Map.ofEntries(
                Map.entry("email", email),
                Map.entry("cardNumber", cardNumber),
                Map.entry("month", expiry[0]),
                Map.entry("year", expiry[1]),
                Map.entry("cvv", cvv),
                Map.entry("postCode", "12345")
        );
    }

    public void switchToIframe() {
        WebElement iframe = driver.findElement(PAYMENT_PAGE_IFRAME);
        driver.switchTo().frame(iframe);
    }

    public void enterCardDetails(Map<String, String> cardDetails) throws InterruptedException {

        for (Map.Entry<String, String> element : cardDetails.entrySet()) {
            System.out.println(element.getKey() + " " + element.getValue());
        }

        driver.findElement(PAY_WITH_CARD_BUTTON).click();

        switchToIframe();

        driver.findElement(ENTER_CARD_EMAIL).sendKeys(cardDetails.get("email"));
        WebElement cardNumberInput = driver.findElement(ENTER_CARD_NUMBER);

        String cardNumber = cardDetails.get("cardNumber");

        cardNumberInput.sendKeys(cardNumber.substring(0, 4));
        Thread.sleep(150);
        cardNumberInput.sendKeys(cardNumber.substring(4, 8));
        Thread.sleep(150);
        cardNumberInput.sendKeys(cardNumber.substring(8, 12));
        Thread.sleep(150);
        cardNumberInput.sendKeys(cardNumber.substring(12, 16));
        Thread.sleep(150);

        driver.findElement(ENTER_CARD_EXPIRY).sendKeys(cardDetails.get("month"));
        Thread.sleep(150);

        driver.findElement(ENTER_CARD_EXPIRY).sendKeys(cardDetails.get("year"));
        Thread.sleep(150);

        driver.findElement(ENTER_CVV).sendKeys(cardDetails.get("cvv"));

    }

    public void clickOnPayButton(Map<String, String> cardDetails) {
        WebElement payButton = driver.findElement(PAY_BUTTON);

        payButton.click();

        WebElement postCodeInputBox = driver.findElement(ENTER_POSTCODE);

        if (postCodeInputBox.isDisplayed()) {
            postCodeInputBox.sendKeys(cardDetails.get("postCode"));
            payButton.click();
        }
    }

    public String getHeading() {
        return driver.findElement(PAYMENT_SUCCESS_PAGE_HEADER).getText();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
