package TestSuiteBase;

import org.openqa.selenium.WebElement;
import static utils.Utility.*;

public class PurchasePage extends SuiteBase {

    /**
     * input details on the Purchase page
     */
    public static void enterDetails() {
        enterValue("inputName", "FirstName");
        enterValue("address", "12-Apartments, Richmond Road");
        enterValue("city", "Bangalore");
        enterValue("state", "karnataka");
        enterValue("zipCode", "123456");
        enterValue("creditCardNumber", "123456789012");
        enterValue("creditCardMonth", "12");
        enterValue("nameOnCard", "ABC");
        enterValue("creditCardYear", "2022");
    }

    /**
     * Enter value for input fields
     * @param fieldName
     * @param text
     */
    public static void enterValue(String fieldName, String text) {
        WebElement element = getElementById(fieldName);
        clickElement(element);
        enterElementValue(element, text);
    }

    /*
    Click Purchase button
     */
    public static void clickPurchase() {
        WebElement purchaseFlightButton = getElementByXpath("Submit_Button");
        clickElement(purchaseFlightButton);
    }
}
