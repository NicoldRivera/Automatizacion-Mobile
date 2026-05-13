package steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.net.URL;
import java.time.Duration;

public class Hooks {
    public static AndroidDriver driver;

    @Before
    public void setup() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Pixel_4_API_29")
                .setApp("src/test/resources/apps/Android-MyDemoAppRN.1.3.0.build-244.apk")
                .setAutomationName("UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}