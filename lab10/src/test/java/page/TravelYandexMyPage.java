package page;

import model.Order;
import model.Passenger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class TravelYandexMyPage extends AbstractPage {

    public static String PAGE_URL = "https://travel.yandex.ru/my";

    private static final String PASSENGERS_BUTTON_XPATH = "//div[@class='RMnjb Yq_UW']//button[text()='Пассажиры']";
    private static final String FIND_ORDER_LINK_XPATH = "//div[@class='oa0id wrap_desktop']//a[text()[contains(.,'найти его по')]]";
    private static final String ORDER_NUMBER_INPUT_XPATH = "//div[@class='formContainer']//input[@name='ordersSearch.prettyOrderId']";
    private static final String ORDER_DATA_INPUT_XPATH = "//div[@class='formContainer']//input[@name='ordersSearch.userPhoneOrEmail']";
    private static final String FIND_ORDER_BUTTON_XPATH = "//div[@class='formContainer']//div[@class='ky5BD']/button";
    private static final String ORDER_NOT_FOUND_MESSAGE_XPATH = "//div[@class='formContainer']//div[@class='ky5BD']/../div[@class='l7DLo']";
    private static final String ADD_PASSENGER_BUTTON_XPATH = "//div[@class='nmT3B']//span[@class='Button2-Text']/..";
    private static final String PASSENGER_NAME_INPUT_XPATH = "//div[@class='wydDK peZ35 f84Nt vLyJ1']//input[@name='firstName']";
    private static final String PASSENGER_BIRTH_DATE_INPUT_XPATH = "//div[@class='wydDK peZ35 f84Nt vLyJ1']//input[@id='passenger-birthDate']";
    private static final String PASSENGER_GENDER_BUTTON_XPATH = "//div[@class='wydDK peZ35 f84Nt vLyJ1']//input[@name='gender']/..";
    private static final String PASSENGER_NATIONALITY_BUTTON_XPATH = "//div[@class='wydDK peZ35 f84Nt vLyJ1']//button[@name='citizenship']";
    private static final String PASSENGER_NATIONALITY_LIST_ELEMENT_XPATH = "//div[@class='JlHeo' and @title='%1$s']";
    private static final String PASSENGER_DOCUMENT_BUTTON_XPATH = "//div[@class='wydDK peZ35 f84Nt vLyJ1']//button[@id='passenger-type']";
    private static final String PASSENGER_DOCUMENT_LIST_ELEMENT_XPATH = "//div[@class='GPpR4' and @title[contains(.,'%1$s')]]";
    private static final String PASSENGER_SERIES_AND_NUMBER_INPUT_XPATH = "//div[@class='wydDK peZ35 f84Nt vLyJ1']//input[@id='passenger-number']";
    private static final String PASSENGER_FORM_SUBMIT_BUTTON_XPATH = "//div[@class='wydDK peZ35 f84Nt vLyJ1']//button[@type='submit']";
    private static final String ADDED_PASSENGER_XPATH = "//div[@class='ZGgxj']//div[text()[contains(.,'%1$s')]]";

    public TravelYandexMyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public TravelYandexMyPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public TravelYandexMyPage navigateToPassengers() {
        getElement(PASSENGERS_BUTTON_XPATH).click();
        return this;
    }

    public TravelYandexMyPage addPassenger(Passenger passenger) {
        getElement(ADD_PASSENGER_BUTTON_XPATH).click();
        getElement(PASSENGER_NAME_INPUT_XPATH).sendKeys(passenger.getName());
        getElement(PASSENGER_BIRTH_DATE_INPUT_XPATH).sendKeys(passenger.getBirthDate());
        getElement(PASSENGER_GENDER_BUTTON_XPATH).click();
        getElement(PASSENGER_NATIONALITY_BUTTON_XPATH).click();
        getElement(String.format(PASSENGER_NATIONALITY_LIST_ELEMENT_XPATH, passenger.getNationality())).click();
        getElement(PASSENGER_DOCUMENT_BUTTON_XPATH).click();
        getElement(String.format(PASSENGER_DOCUMENT_LIST_ELEMENT_XPATH, passenger.getDocument())).click();
        getElement(PASSENGER_SERIES_AND_NUMBER_INPUT_XPATH).sendKeys(passenger.getSeriesAndNumber());
        getElement(PASSENGER_FORM_SUBMIT_BUTTON_XPATH).click();
        return this;
    }

    public boolean isPassengerAdded(String name) {
        return getElement(String.format(ADDED_PASSENGER_XPATH, name)).isDisplayed();
    }

    public TravelYandexMyPage findOrder(Order order) {
        getElement(FIND_ORDER_LINK_XPATH).click();
        getElement(ORDER_NUMBER_INPUT_XPATH).sendKeys(order.getNumber());
        getElement(ORDER_DATA_INPUT_XPATH).sendKeys(order.getContactData());
        getElement(FIND_ORDER_BUTTON_XPATH).click();
        return this;
    }

    public boolean isOrderNotFoundMessageDisplayed() {
        return getElement(ORDER_NOT_FOUND_MESSAGE_XPATH).isDisplayed();
    }
}
