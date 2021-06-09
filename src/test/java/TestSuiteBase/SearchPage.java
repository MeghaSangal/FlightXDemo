package TestSuiteBase;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static utils.Utility.*;

public class SearchPage extends SuiteBase {

    public static boolean selectTravelToFromPlaces(String flightType, String name) {
        try {
            WebElement element;
            if (flightType.equalsIgnoreCase(("origin"))) {
                element = getElementByName("Source_Dropdown_Name");
            } else {
                element = getElementByName("Destination_Dropdown_Name");
            }
            selectDropdownValue(element, name);
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return false;
        }

    }

    public static void findFlights(String departureCityName, String arrivalCityName) {
        if (departureCityName.equalsIgnoreCase(arrivalCityName)) {
            Logger.error("");
        }
        boolean isSourceSelected = selectTravelToFromPlaces("origin", departureCityName);
        boolean isTargetSelected = selectTravelToFromPlaces("destination", arrivalCityName);

        if (isSourceSelected && isTargetSelected) {
            WebElement findFlightsButton = getElementByXpath("Submit_Button");
            clickElement(findFlightsButton);
            Assert.assertEquals(Object.getProperty("Find_Flights_Header_Message").replace("<origin>", departureCityName).replace("<target>", arrivalCityName),
                    getElementByXpath("Header_Reserve").getText(), "Failed :");
        }
    }
}
