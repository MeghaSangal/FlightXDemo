package scenarios;

import TestSuiteBase.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.ExtentManager;

import static utils.Utility.*;

public class BaseTests extends SuiteBase {

    public ExtentReports extent;
    public ExtentTest test;

    public BaseTests() throws Exception {
        final String Path = System.getProperty("ReportDirectoryPath");
        extent = ExtentManager.Instance(Path, "BaseTests");
    }

    @AfterMethod
    public void writeToReport(){
        extent.flush();
    }

    @Test(priority = 0)
    public void homeLaunch() {
        test = extent.createTest("Home Launch","Verify the Launching of home page");
        test.log(Status.INFO,"Verifying the the success message of the home page as "+Object.getProperty("Welcome_Success_Message"));
        Assert.assertTrue(Object.getProperty("Welcome_Success_Message").equals(getElementByXpath("All_Headers").getText()),
                "Failed : ");
    }
    @Test(priority = 1)
    public void searchFlights() {
        test = extent.createTest("Search Flights","Searching the flights");
        test.log(Status.INFO,"Selection of flight : Portland to Rome ");
        SearchPage.findFlights("Portland","Rome");
        test.log(Status.PASS,"Departure and Arrival Flights are selected");
    }

    @Test(priority = 2)
    public void chooseFlight() throws InterruptedException {
        test = extent.createTest("Choose flight","Verify the Launching of home page");
        test.log(Status.INFO,"Selection of flight as per user price from Portland to Rome ");
        ReservePage.validateHeaders("Portland","Rome");
        ReservePage.chooseFlight("Price","215");
        test.log(Status.PASS,"Choose The Flight button clicked");
        ReservePage.validateHeadersOnPurchasePage("Portland","Rome",test);
        test.log(Status.PASS,"Successfully navigated to the Purchase screen");
    }

    @Test(priority = 3)
    public void purchaseFlight(){
        test = extent.createTest("Purchase flight","Making payment for the selected flight");
        PurchasePage.enterDetails();
        test.log(Status.PASS,"Entered all the required payment details for the selected flight");
        PurchasePage.clickPurchase();
        test.log(Status.PASS,"Purchase button clicked");
    }

    @Test(priority = 4)
    public  void confirmationFlight(){
        ConfirmationPage.verifyConfirmationMessage();
        test.log(Status.PASS,"Validation of the confirmation message done successfully");
        String confirmationId = ConfirmationPage.captureConfirmationId();
        test.log(Status.INFO,"Confirmation Id of the booked flight: "+confirmationId);
    }
}
