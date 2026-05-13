package steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.net.URL;
import java.time.Duration;

public class CarritoSteps {

    public static AndroidDriver driver;
    public static WebDriverWait wait;

    @Given("estoy en la aplicacion de SauceLabs")
    public void abrirAplicacion() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        // CAPABILITIES ESTILO ANTIGUO
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("automationName", "UiAutomator2");

        capabilities.setCapability("avd", "Pixel_4");
        capabilities.setCapability("avdLaunchTimeout", 60000);
        capabilities.setCapability("adbExecTimeout", 60000);

        capabilities.setCapability("app", "https://github.com/saucelabs/my-demo-app-android/releases/download/2.0.2/mda-2.0.2-23.apk");
        capabilities.setCapability("appWaitActivity", "com.saucelabs.mydemoapp.android.view.activities.MainActivity");

        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("noReset", false);

        // Primera corrida: false. Después cámbialo a true
        capabilities.setCapability("skipDeviceInitialization", false);
        capabilities.setCapability("skipServerInstallation", false);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validarGaleria() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/productRV")
        ));
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregarProducto(int cantidad, String producto) {

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[@text='" + producto + "']")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/addToCartButton")
        )).click();

        for (int i = 1; i < cantidad; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.id("com.saucelabs.mydemoapp.android:id/addToCartButton")
            )).click();
        }
    }

    @And("voy al carrito")
    public void irAlCarrito() {
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV")
        )).click();
    }

    @Then("valido que en el carrito se encuentre agregado")
    public void validarCarrito() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV")
        ));

        if (driver != null) {
            driver.quit();
        }
    }
}