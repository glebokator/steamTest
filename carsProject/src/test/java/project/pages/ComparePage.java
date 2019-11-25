package project.pages;

import framework.driver.Browser;
import framework.pageElements.Button;
import framework.pageElements.DropDownList;
import project.models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import static framework.logger.MyLogger.logger;

public class ComparePage extends MainForm{
    private DropDownList makersList = new DropDownList(By.id("make-dropdown"), "List of makers");
    private DropDownList modelsList = new DropDownList(By.id("model-dropdown"), "List of models");
    private DropDownList yearsList = new DropDownList(By.id("year-dropdown"), "List of years");
    private Button startComparingBtn = new Button(By.xpath("//button[@class='done-button']"), "Start Comparing");
    private static final By ADD_CAR_LOCATOR = By.className("add-car-icon");
    private static final By CAR_NAME_LOCATOR = By.className("listing-name");

    public ComparePage() {
        uniqueElement = startComparingBtn;
    }

    public void initiateCarComparison(Car car) {
        logger.debug("Adding first car to compare");
        makersList.selectItem(car.getMaker());
        modelsList.selectItem(car.getModel());
        yearsList.selectItem(Integer.toString(car.getYear()));
    }

    public void startComparing() {
        logger.debug("Starting to compare");
        startComparingBtn.click();
    }

    public void clickAddCar() {
        logger.debug("Adding another car for comparison");

        new Button(ADD_CAR_LOCATOR, "Add another car").click();
    }

    public ArrayList<String> getCarNames() {
        logger.debug("Getting car names from comparison");
        ArrayList<WebElement> carNames = (ArrayList<WebElement>) Browser.getDriver().findElements(CAR_NAME_LOCATOR);
        ArrayList<String> carNamesString = new ArrayList<>();

        for (WebElement name: carNames) {
            carNamesString.add(name.getText());
        }
        return carNamesString;
    }
}