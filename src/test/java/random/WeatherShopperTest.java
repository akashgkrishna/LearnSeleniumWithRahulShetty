package random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WeatherShopperTest {


    @Test
    public static void buyLowestProductAccordingToTemp() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://weathershopper.pythonanywhere.com/");

        int temperature = Integer.parseInt(
                driver.findElement(
                        By.id("temperature")).getText().replaceAll("[^0-9-]", ""));

        System.out.println(temperature);
        if (temperature > 19) {
            driver.findElement(By.xpath("//button[text()='Buy sunscreens']")).click();
        } else {
            driver.findElement(By.xpath("//button[text()='Buy moisturizers']")).click();
        }

        Thread.sleep(3000);
        List<WebElement> priceElements = driver.findElements(
                By.xpath("//p[contains(text(),'Price:')]"));

        int lowest = Integer.parseInt(
                priceElements.get(0).getText().replaceAll("[^0-9-]", ""));

        for (int i = 1; i < priceElements.size(); i++) {
            int price = Integer.parseInt(
                    priceElements.get(i).getText().replaceAll("[^0-9-]", ""));
            if (lowest > price) {
                lowest = price;
            }
        }
        System.out.println("Lowest = " + lowest);

        driver.findElement(
                By.xpath("//*[contains(text(),'" + lowest + "')]/following-sibling::button")).click();

        driver.findElement(By.cssSelector("button[onclick='goToCart()']")).click();
        Thread.sleep(3000);


        ArrayList<String> cardDetails = getCardDetails(driver);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(3000);

        WebElement stripeIframe = driver.findElement(By.xpath("//iframe[contains(@name, 'stripe_checkout_app')]"));
        driver.switchTo().frame(stripeIframe);

        driver.findElement(By.id("email")).sendKeys(cardDetails.get(0));

//        WebElement cardNumber = driver.findElement(By.id("card_number"));
//        cardNumber.clear();
//        cardNumber.click();
//        cardNumber.sendKeys(cardDetails.get(1));
        // 1. Enter Card Number in 4-digit chunks
        WebElement cardNumber = driver.findElement(By.id("card_number"));
        String card = cardDetails.get(1); // e.g. "5163070812135213"

        cardNumber.sendKeys(card.substring(0, 4));
        Thread.sleep(150);
        cardNumber.sendKeys(card.substring(4, 8));
        Thread.sleep(150);
        cardNumber.sendKeys(card.substring(8, 12));
        Thread.sleep(150);
        cardNumber.sendKeys(card.substring(12, 16));
        Thread.sleep(200);


        // 2. Enter Expiry in 2-digit chunks (MM then YY)
        WebElement ccExp = driver.findElement(By.id("cc-exp"));
        String exp = cardDetails.get(2); // e.g. "0830"

        ccExp.sendKeys(exp.substring(0, 2)); // Month: "08"
        Thread.sleep(150);
        ccExp.sendKeys(exp.substring(2, 4)); // Year: "30"
        Thread.sleep(150);

        // 3. Enter CVC
        driver.findElement(By.id("cc-csc")).sendKeys(cardDetails.get(3));

//        driver.findElement(By.xpath("//input[@placeholder='ZIP Code']")).sendKeys(cardDetails.get(4));
        List<WebElement> zipFields = driver.findElements(By.id("billing-zip"));
        if (!zipFields.isEmpty() && zipFields.get(0).isDisplayed()) {
            zipFields.get(0).sendKeys(cardDetails.get(4));
        }

        driver.findElement(By.className("iconTick")).click();

        driver.switchTo().defaultContent();

        Thread.sleep(6000);

        String text = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(text, "PAYMENT SUCCESS");

        driver.quit();
    }

    public static ArrayList<String> getCardDetails(WebDriver driver) throws InterruptedException {
        String currentWindowHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://neapay.com/online-tools/credit-card-number-generator-validator.html");

        Thread.sleep(2000);
        Select cardType = new Select(
                driver.findElement(By.xpath("//select[@class='form-control']")));
        cardType.selectByVisibleText("MasterCard");

        driver.findElement(By.xpath("//button[text()='Generate Card']")).click();

        Thread.sleep(2000);

        String email = driver.findElement(
                        By.xpath("(//div[contains(@class, 'card-front')]//pre)[3]"))
                .getText().replaceAll(" ", "").toLowerCase().concat("@gmail.com");

        String cardNumber = driver.findElement(By.xpath("(//div[contains(@class, 'card-front')]//pre)[1]"))
                .getText().replaceAll("[^0-9-]", "");

        String expiryDate = driver.findElement(By.xpath("(//div[contains(@class, 'card-front')]//pre)[2]"))
                .getText().replaceAll("[^0-9-]", "");

        String cvv = driver.findElement(By.xpath("(//div[contains(@class, 'card-back')]//pre)"))
                .getText().replaceAll("[^0-9-]", "");

        ArrayList<String> cardDetails = new ArrayList<>();
        cardDetails.add(email);
        cardDetails.add(cardNumber);
        cardDetails.add(expiryDate);
        cardDetails.add(cvv);
        cardDetails.add("123456");

        for (String string : cardDetails) {
            System.out.println(string);
        }

        driver.close();
        driver.switchTo().window(currentWindowHandle);

        return cardDetails;
    }
}
