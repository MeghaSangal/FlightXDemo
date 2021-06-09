package TestSuiteBase;

import org.testng.Assert;
import java.util.List;
import static utils.Utility.*;

public class ConfirmationPage extends SuiteBase {

    /*
     * Verify Confirmation Message on Purchase page
     */
    public static void verifyConfirmationMessage() {
        Assert.assertTrue(Object.getProperty("Purchase_Success_Message").equals(getElementByXpath("All_Headers").getText()),
                "Failed : ");
    }

    /*
    Capture the confirmation Id on the purchase Page
     */
    public static String captureConfirmationId() {
        List<String> confirmationListValues = getListElements(getElementsByXpath("Fields_ConfirmationPage"));
        String xpath = "";
        for (String value : confirmationListValues) {
            if (value.equalsIgnoreCase("Id")) {
                xpath = Object.getProperty("Fields_ConfirmationPage") + "/following::td";
                break;
            }
        }
        return getElementByStringXpath(xpath).getText();
        // Logger.info("Confirmation Id is "+confirmationId);
    }
}
