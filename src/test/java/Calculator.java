import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class Calculator {
    private WindowsDriver calcsession = null;
    private WebElement calcResult = null;


    @BeforeClass
    public void setup(){
        System.out.println("setup");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app","Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        try {
            calcsession = new WindowsDriver(new URL("http://127.0.0.1:4723"),capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown(){
        System.out.println("tearDown");
    }

    @BeforeMethod
    public void clear(){
        System.out.println("Clear");
        calcsession.findElementByName("Clear").click();
    }

    @Test
    public void addition(){
        System.out.println("addition ");
        calcsession.findElementByName("One").click();
        calcsession.findElementByName("Plus").click();
        calcsession.findElementByName("Two").click();
        calcsession.findElementByName("Equals").click();
        Assert.assertEquals(GetDispalyResult(),"3");
    }
    @Test
    public void subtraction(){
        System.out.println("subtraction ");
        calcsession.findElementByName("One").click();
        calcsession.findElementByName("Minus").click();
        calcsession.findElementByName("Two").click();
        calcsession.findElementByName("Equals").click();
        Assert.assertEquals(GetDispalyResult(),"-1");
    }

//    @Test
//    public void multiplication(){
//        System.out.println("multiplication ");
//    }
//    @Test
//    public void division(){
//        System.out.println("division ");
//    }

    public String GetDispalyResult(){
        calcResult = calcsession.findElementByAccessibilityId("CalculatorResults");
      return calcResult.getText().replace("Display is","").trim();

    }


}
