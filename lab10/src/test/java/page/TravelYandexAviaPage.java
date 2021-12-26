package page;

import model.TripData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static utils.DateUtils.formatDateToRussian;

public class TravelYandexAviaPage extends AbstractPage {

    public static String PAGE_URL = "https://travel.yandex.ru/avia/";

    private static final String SOURCE_AND_DESTINATION_XPATH = "//header//div[@class='WFYfN']/span[@class='text']";
    private static final String TRAVEL_DATE_XPATH = "//header//div[@class='kzvZe']/span[@class='ZlSD1']";
    private static final String TRAVEL_LIST_NAME_XPATH = "//*[@class='EW8x1']//div[text() = '%1$s']";
    private static final String TRAVEL_TABLE_DATE_XPATH = "//div[@class='aCtN8']//div[text()='%1$s']/..//span[text()='%2$s']";
    private static final String SOURCE_INPUT_XPATH = "//div[@class='root KPSVc']/form//div[text()='Откуда']/..//input";
    private static final String DESTINATION_INPUT_XPATH = "//div[@class='root KPSVc']/form//div[text()='Куда']/..//input";
    private static final String DATE_TO_XPATH = "//div[@class='root KPSVc']/form//div[text()='Туда']";
    private static final String DATE_FROM_XPATH = "//div[@class='root KPSVc']/form//div[@class='nAZcZ SGaYr' and text()='Обратно']";
    private static final String FIND_BUTTON_XPATH = "//div[@class='root KPSVc']/form/button[@class='vHqxX z8gtM']";

    @FindBy(xpath = SOURCE_INPUT_XPATH)
    private WebElement sourceInput;

    @FindBy(xpath = DESTINATION_INPUT_XPATH)
    private WebElement destinationInput;

    @FindBy(xpath = DATE_TO_XPATH)
    private WebElement dateTo;

    @FindBy(xpath = DATE_FROM_XPATH)
    private WebElement dateFrom;

    @FindBy(xpath = FIND_BUTTON_XPATH)
    private WebElement findButton;

    public TravelYandexAviaPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public TravelYandexAviaPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public TravelYandexAviaPage fillForm(TripData tripData) {
        enterSource(tripData.getSource());
        enterDestination(tripData.getDestination());
        enterDateTo(tripData.getDateTo());
        enterDateFrom(tripData.getDateFrom());
        return this;
    }

    public TravelYandexAviaPage enterSource(String source) {
        sourceInput.sendKeys(Keys.CONTROL + "a");
        sourceInput.sendKeys(source);
        getMatchingListElement(source).click();
        return this;
    }

    public TravelYandexAviaPage enterDestination(String destination) {
        destinationInput.sendKeys(destination);
        getMatchingListElement(destination).click();
        return this;
    }

    public TravelYandexAviaPage enterDateTo(Date date) {
        String monthTo = capitalize(formatDateToRussian(date, "MMMM"));
        String dayTo = capitalize(formatDateToRussian(date, "dd"));
        dateTo.click();
        getMatchingTableDate(monthTo, dayTo).click();
        return this;
    }

    public TravelYandexAviaPage enterDateFrom(Date date) {
        String monthFrom = capitalize(formatDateToRussian(date, "MMMM"));
        String dayFrom = capitalize(formatDateToRussian(date, "dd"));
        dateFrom.click();
        getMatchingTableDate(monthFrom, dayFrom).click();
        return this;
    }

    public TravelYandexAviaPage submit() {
        findButton.click();
        return this;
    }

    public String getSourceAndDestination() {
        return getElement(SOURCE_AND_DESTINATION_XPATH).getText();
    }

    public String getTravelDate() {
        return getElement(TRAVEL_DATE_XPATH).getText();
    }

    public boolean isDateFromEmpty() {
        return getElement(DATE_FROM_XPATH).isDisplayed();
    }

    private WebElement getMatchingListElement(String name) {
        By elementWithNameLocator = By.xpath(String.format(TRAVEL_LIST_NAME_XPATH, name));
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(elementWithNameLocator));
    }

    private WebElement getMatchingTableDate(String month, String day) {
        By dateLocator = By.xpath(String.format(TRAVEL_TABLE_DATE_XPATH, month, day));
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(dateLocator));
    }

//    private WebElement getElement(String xpath) {
//        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//    }
}
