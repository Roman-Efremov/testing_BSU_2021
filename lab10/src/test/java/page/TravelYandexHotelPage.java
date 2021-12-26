package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static utils.DateUtils.formatDateToRussian;

public class TravelYandexHotelPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    public static String PAGE_URL = "https://travel.yandex.ru/hotels/";

    private static final String DESTINATION_INPUT_XPATH = "//div[@class='root cjvMw']//form//div[text()[contains(.,'Куда')]]/..//input";
    private static final String DATE_TO_XPATH = "//div[@class='root cjvMw']//form//div[text()='Заезд']";
    private static final String DATE_FROM_XPATH = "//div[@class='root cjvMw']//form//div[text()='Выезд']";
    private static final String FIND_BUTTON_XPATH = "//div[@class='root cjvMw']//form/button[@class='vHqxX z8gtM']";
    private static final String FIRST_ADD_TO_FAVORITE_BUTTON_XPATH = "//div[@class='PTAbI']//div[@class='TdBzS root_size_s latzZ']";
    private static final String FIRST_HOTEL_XPATH = "//div[@class='PTAbI']//div[@class='TdBzS root_size_s latzZ']/..//a";
    private static final String TRAVEL_LIST_NAME_XPATH = "//*[@class='EW8x1']//div[text()='%1$s']";
    private static final String TRAVEL_TABLE_DATE_XPATH = "//div[@class='aCtN8']//div[text()='%1$s']/..//span[text()='%2$s']/..";

    @FindBy(xpath = DESTINATION_INPUT_XPATH)
    private WebElement destinationInput;

    @FindBy(xpath = DATE_TO_XPATH)
    private WebElement dateTo;

    @FindBy(xpath = DATE_FROM_XPATH)
    private WebElement dateFrom;

    @FindBy(xpath = FIND_BUTTON_XPATH)
    private WebElement findButton;


    public TravelYandexHotelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public TravelYandexHotelPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public TravelYandexHotelPage enterDestination(String destination) {
        destinationInput.sendKeys(destination);
        getMatchingListElement(destination).click();
        return this;
    }

    public TravelYandexHotelPage enterDateTo(Date date) {
        String monthTo = capitalize(formatDateToRussian(date, "MMMM"));
        String dayTo = formatDateToRussian(date, "dd");
        dateTo.click();
        getMatchingTableDate(monthTo, dayTo).click();
        return this;
    }

    public TravelYandexHotelPage enterDateFrom(Date date) {
        String monthFrom = capitalize(formatDateToRussian(date, "MMMM"));
        String dayFrom = formatDateToRussian(date, "dd");
        dateFrom.click();
        getMatchingTableDate(monthFrom, dayFrom).click();
        return this;
    }

    public TravelYandexHotelPage submit() {
        findButton.click();
        return this;
    }

    public TravelYandexHotelPage addFirstHotelToFavorite() {
        getElement(FIRST_ADD_TO_FAVORITE_BUTTON_XPATH).click();
        logger.info("Add hotel \"" + getFirstHotelName() + "\" to favorite");
        return this;
    }

    public boolean isDateFromClickable(Date date) {
        String monthFrom = capitalize(formatDateToRussian(date, "MMMM"));
        String dayFrom = formatDateToRussian(date, "dd");
        try {
            getMatchingTableDate(monthFrom, dayFrom).click();
        } catch (ElementClickInterceptedException e) {
            return false;
        }
        return true;
    }

    public String getFirstHotelName() {
        String hotelName = getElement(FIRST_HOTEL_XPATH).getText();
        return hotelName.substring(0, hotelName.length() - 3);
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
