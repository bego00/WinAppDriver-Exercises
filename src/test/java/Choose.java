import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.list;

public class Choose {
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
    //Choose calc with find ClassName
    @Test
    public void ChooseCalculator(String locator){
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        List<WebElement> listOfElements = calcsession.findElementsByClassName("Microsoft.UI.Xaml.Controls.NavigationViewItem");
         System.out.println(listOfElements.size());
        for (int i = 0; i < listOfElements.size()+1; i++) {
            if(listOfElements.get(i).getAttribute("AutomationId").equals(locator));
            listOfElements.get(i).click();
            break;
        }

    }
    @Test
    public void SelectAnotherCalculator(){
        System.out.println("Selecting Another Calculator");
        ChooseCalculator("Scientific");
    }

    // Choose calc wiht Xpath
    @Test
    public void ChooseCalculatorXpath(String locator){
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        List<WebElement> listOfElements = calcsession.findElementsByXPath("//ListItem");
        for (int i = 0; i < listOfElements.size()+1; i++) {
            if(listOfElements.get(i).getAttribute("AutomationId").equals(locator));
            listOfElements.get(i).click();
            break;
        }
    }
    @Test
    public void SelectAnotherCalculatorXpath(){
    System.out.println("Selecting Another Calculator");
    ChooseCalculatorXpath("Scientific");
        }

    //choose calc with another way by Xpath
    @Test
    public void ChooseCalculatorXpathsimple(String locator){
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        calcsession.findElementByXPath("//ListItem[contains(@Name,\""+locator+"\")]").click();

        }

    @Test
    public void SelectAnotherCalculatorXpathsimple(){
        System.out.println("Selecting Another Calculator");
        ChooseCalculatorXpathsimple("Programmer");
    }
}
