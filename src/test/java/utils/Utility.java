package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;
import static TestSuiteBase.SuiteBase.*;

public class Utility {

    public static boolean checkExists(WebElement element) {
        return element.isDisplayed() && element.isEnabled();
    }

    public static WebElement getElementByXpath(String key) {
        try {
            By locator = By.xpath(Object.getProperty(key));
            return driver.findElement(locator);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public static WebElement getElementByStringXpath(String key) {
        try {
            By locator = By.xpath(key);
            return driver.findElement(locator);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public static void clickElement(String key) {
        try {
            WebElement element = getElementByXpath(key);
            if (element != null && checkExists(element)) {
                element.click();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    public static void clickElement(WebElement element) {
        try {
            if (element != null && checkExists(element)) {
                element.click();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    public static void enterElementValue(WebElement element, String value) {
        try {
            if (element != null && checkExists(element)) {
                element.clear();
                element.sendKeys(value);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    public static List<WebElement> getElementsByXpath(String key) {
        try {
            By locator = By.xpath(Object.getProperty(key));
            return driver.findElements(locator);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public static List<String> getListElements(List<WebElement> listOfElements) {
        List<String> textList = new ArrayList<String>();
        for (WebElement ele : listOfElements) {
            String txt = ele.getText();
            if (txt.contains("$")) {
                txt = txt.replace("$", "");
            }
            textList.add(txt);
        }
        return textList;
    }

    public static int getIndex(List<String> cols, String name) {
        int counter = 0;
        int index = 0;
        for (String col : cols) {
            if (col.equalsIgnoreCase(name)) {
                index = counter;
            }
            counter++;
        }
        return index;
    }

    public static List<Integer> convertStringToInteger(List<String> values) {
        List<Integer> textList = new ArrayList<Integer>();
        for (String val : values) {
            int value = (int) (Math.floor(Double.parseDouble(val)));
            textList.add(new Integer(value));
        }
        return textList;
    }

    public static WebElement getElementByName(String key) {
        try {
            By locator = By.name(Object.getProperty(key));
            return driver.findElement(locator);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public static WebElement getElementById(String id_name) {
        try {
            By locator = By.id(id_name);
            return driver.findElement(locator);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public static void selectDropdownValue(WebElement element, String value) {
        try {
            if (element != null && checkExists(element)) {
                Select select = new Select(element);
                select.selectByVisibleText(value);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
}