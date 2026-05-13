package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.net.URL;
import static org.junit.Assert.assertTrue;

public class CarritoSteps {
    private AppiumDriver driver;
    private WebDriverWait wait;

    @Given("estoy en la aplicacion de SauceLabs")
    public void abrirAplicacion() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setAutomationName("UiAutomator2");
        options.setApp("https://github.com/saucelabs/my-demo-app-android/releases/download/2.0.2/mda-2.0.2-23.apk");
        options.setAppWaitActivity("com.saucelabs.mydemoapp.android.view.activities.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validarGaleria() {
        WebElement producto = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']")));
        assertTrue(producto.isDisplayed());
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregarProducto(int unidades, String producto) {
        WebElement btnProducto = driver.findElement(By.xpath("//android.widget.TextView[@text='" + producto + "']/..//android.widget.ImageView"));
        btnProducto.click();

        for(int i=1; i<unidades; i++){
            driver.findElement(By.xpath("//android.widget.TextView[@text='+']")).click();
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='Add To Cart']")).click();
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Displays number of items in your cart']")).click();
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void validarCarrito() {
        WebElement carrito = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.saucelabs.mydemoapp.android:id/cartTV")));
        assertTrue(carrito.isDisplayed());
        driver.quit();
    }
}
