package steps;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CarritoSteps {

    AndroidDriver driver;
    WebDriverWait wait;

    public CarritoSteps() {
        // Usa el driver que inicializas en Hooks.java
        this.driver = Hooks.driver;
        // Espera explícita de 15 segundos para todos los elementos
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Given("que estoy en la pantalla de productos")
    public void estoyEnPantallaProductos() {
        // Espera a que cargue la lista de productos
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Products")
        ));
    }

    @When("agrego el producto {string} al carrito")
    public void agregoProductoAlCarrito(String nombreProducto) {
        // Espera explícita a que el botón sea clickeable
        WebElement btnAgregar = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Add To Cart button")
        ));
        btnAgregar.click();
    }

    @When("entro al carrito")
    public void entroAlCarrito() {
        WebElement iconoCarrito = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Cart")
        ));
        iconoCarrito.click();
    }

    @Then("debería ver {string} en el carrito")
    public void validarProductoEnCarrito(String nombreProducto) {
        // Espera a que el producto aparezca en la lista del carrito
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@text='" + nombreProducto + "']")
        ));
    }

    @Then("la cantidad debería ser {string}")
    public void validarCantidad(String cantidadEsperada) {
        WebElement cantidad = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='counter plus button']")
        ));
        String cantidadActual = cantidad.getText();

        if (!cantidadActual.equals(cantidadEsperada)) {
            throw new AssertionError("Se esperaba cantidad " + cantidadEsperada + " pero fue " + cantidadActual);
        }
    }
}