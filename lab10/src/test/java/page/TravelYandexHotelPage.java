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

    @FindBy(xpath = "//div[@class='root cjvMw']//form//div[text()[contains(.,'Куда')]]/..//input")
    private WebElement destinationInput;

    @FindBy(xpath = "//div[@class='root cjvMw']//form//div[text()='Заезд']")
    private WebElement dateTo;

    @FindBy(xpath = "//div[@class='root cjvMw']//form//div[text()='Выезд']")
    private WebElement dateFrom;

    @FindBy(xpath = "//div[@class='root cjvMw']//form/button[@class='vHqxX z8gtM']")
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
        String dayTo = capitalize(formatDateToRussian(date, "dd"));
        dateTo.click();
        getMatchingTableDate(monthTo, dayTo).click();
        return this;
    }

    public TravelYandexHotelPage enterDateFrom(Date date) {
        String monthFrom = capitalize(formatDateToRussian(date, "MMMM"));
        String dayFrom = capitalize(formatDateToRussian(date, "dd"));
        dateFrom.click();
        getMatchingTableDate(monthFrom, dayFrom).click();
        return this;
    }

    public TravelYandexHotelPage submit() {
        findButton.click();
        return this;
    }

    public TravelYandexHotelPage addFirstHotelToFavorite() {
        getElement("//div[@class='PTAbI']//div[@class='TdBzS root_size_s latzZ']").click();
        logger.info("Add hotel \"" + getFirstHotelName() + "\" to favorite");
        return this;
    }

    public boolean isDateFromClickable(Date date) {
        String monthFrom = capitalize(formatDateToRussian(date, "MMMM"));
        String dayFrom = capitalize(formatDateToRussian(date, "dd"));
        try {
            getMatchingTableDate(monthFrom, dayFrom).click();
        } catch (ElementClickInterceptedException e) {
            return false;
        }
        return true;
    }

    public String getFirstHotelName() {
        String hotelName =  getElement("//div[@class='PTAbI']//div[@class='TdBzS root_size_s latzZ']/..//a")
                .getText();
        return hotelName.substring(0, hotelName.length() - 3);
    }

    private WebElement getMatchingListElement(String name) {
        By elementWithNameLocator = By.xpath("//*[@class='EW8x1']//div[text()='" + name + "']");
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(elementWithNameLocator));
    }

    private WebElement getMatchingTableDate(String month, String day) {
        By dateLocator = By.xpath("//div[@class='aCtN8']//div[text()='" + month + "']/..//span[text()='" + day + "']");
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(dateLocator));
    }

    private WebElement getElement(String xpath) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
}
