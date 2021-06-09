package TestSuiteBase;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static utils.Utility.*;
import java.util.List;

public class ReservePage extends SuiteBase {

    static List<String> colHeaders = null;

    /*
    Validate column headers for Reserve Page containing departure and arrival city names
     */
    public static void validateHeaders(String departureCityName, String arrivalCityName) {
        colHeaders = getListElements(getElementsByXpath("Headers_ReservePage"));
        int counter = 0;
        for (String headerName : colHeaders) {
            if (headerName.contains("Departs")) {
                Assert.assertTrue(headerName.contains(departureCityName), "Header Name doesnot contain the departure city name");
                counter++;
            }
            if (headerName.contains("Arrives")) {
                Assert.assertTrue(headerName.contains(arrivalCityName), "Header Name doesnot contain the arrival city name");
                counter++;
            }
        }
        if (counter != 2) {
            Assert.fail("Header names donot contain the city names");
        }
    }

    /*
    Gets Index for which flight to choose based on closest price specified by the user
     */
    public static int getChooseFlightIndex(String headerName, String priveVal) {
        int price = Integer.parseInt((priveVal));
        colHeaders = getListElements(getElementsByXpath("Headers_ReservePage"));
        int index = getIndex(colHeaders, headerName);
        String xpathValue = Object.getProperty("Price_Column_Value");
        xpathValue = xpathValue.replace("<index>", String.valueOf(index + 1));
        List<WebElement> elements = driver.findElements(By.xpath(xpathValue));
        List<String> listOfElements = getListElements(elements);
        List<Integer> priceColumnTextValues = convertStringToInteger(listOfElements);
        List<Integer> priceColumnTextValues_Original = priceColumnTextValues;
        boolean isFound = false;
        int priceSelected = 0;
        for (int i = 0; i < priceColumnTextValues.size(); i++) {
            if (priceColumnTextValues.get(i + 1).intValue() > price && price > priceColumnTextValues.get(i).intValue()) {
                priceSelected = priceColumnTextValues.get(i);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            Assert.fail("The Price value doesnot exist in the given range available on the site");
        }

        int indexForChooseFlightBtn = 0;
        for (int i = 0; i < priceColumnTextValues_Original.size(); i++) {
            String txt = String.valueOf(priceColumnTextValues_Original.get(i));
            if (txt.equals(String.valueOf(priceSelected))) {
                indexForChooseFlightBtn = i;
                break;
            }
        }
        return indexForChooseFlightBtn;
    }

    /*
    Choose the flight based on the closest price range as specified by the user
     */
    public static void chooseFlight(String headerName, String price) throws InterruptedException {
        int index = getChooseFlightIndex(headerName, price);
        String xpathValue = Object.getProperty("Choose_Flight_Button");
        xpathValue = xpathValue.replace("<index>", String.valueOf(index + 1));
        Thread.sleep(5000);
        getElementByStringXpath(xpathValue).click();
    }

    /*
    Validate the headers
     */
    public static void validateHeadersOnPurchasePage(String departureCityName, String arrivalCityName, ExtentTest test) {
        boolean isHeaderMatch = Object.getProperty("Purchase_Header_Message").replace("<origin>", departureCityName).replace("<target>", arrivalCityName).equals( getElementByXpath("Header_Purchase").getText());
        if(isHeaderMatch){
            test.log(Status.PASS,"Headers contain the appropriate departure and arrival City names");
        }
        else{
            test.log(Status.FAIL,"Headers doesnot contain the appropriate departure and arrival City names");
        }
    }

}
