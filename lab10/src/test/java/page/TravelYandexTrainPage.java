package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelYandexTrainPage extends AbstractPage {

    public static String PAGE_URL = "https://travel.yandex.ru/trains/";

    private static final String SOURCE_INPUT_XPATH = "//div[@class='root GSMZw']/form//div[text()='Откуда']/..//input";
    private static final String DESTINATION_INPUT_XPATH = "//div[@class='root GSMZw']/form//div[text()='Куда']/..//input";
    private static final String FIND_BUTTON_XPATH = "//div[@class='root GSMZw']/form/button[@class='vHqxX z8gtM']";
    private static final String SOURCE_AND_DESTINATION_XPATH = "//header//div[@class='WFYfN']/span[@class='text']";
    private static final String TRAVEL_DATE_XPATH = "//header//div[@class='kzvZe']/span[@class='ZlSD1']";
    private static final String DESTINATION_XPATH = "//div[@class='root GSMZw']/form//div[text()='Куда']/../div[@class='nAZcZ LRlEo' and text()='Куда']";
    private static final String TRAVEL_LIST_NAME_XPATH = "//*[@class='EW8x1']//div[text()='%1$s']";

    @FindBy(xpath = SOURCE_INPUT_XPATH)
    private WebElement sourceInput;

    @FindBy(xpath = DESTINATION_INPUT_XPATH)
    private WebElement destinationInput;

    @FindBy(xpath = FIND_BUTTON_XPATH)
    private WebElement findButton;

    public TravelYandexTrainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public TravelYandexTrainPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public TravelYandexTrainPage enterSource(String from) {
        sourceInput.sendKeys(Keys.CONTROL + "a");
        sourceInput.sendKeys(from);
        getMatchingListElement(from).click();
        return this;
    }

    public TravelYandexTrainPage enterDestination(String to) {
        destinationInput.sendKeys(to);
        getMatchingListElement(to).click();
        return this;
    }

    public TravelYandexTrainPage submit() {
        findButton.click();
        return this;
    }

    public String getSourceAndDestination() {
        return getElement(SOURCE_AND_DESTINATION_XPATH).getText();
    }

    public String getTravelDate() {
        return getElement(TRAVEL_DATE_XPATH).getText();
    }

    public boolean isDestinationEmpty() {
        return getElement(DESTINATION_XPATH).isEnabled();
    }

    private WebElement getMatchingListElement(String name) {
        By elementWithNameLocator = By.xpath(String.format(TRAVEL_LIST_NAME_XPATH, name));
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(elementWithNameLocator));
    }

//    private WebElement getElement(String xpath) {
//        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//    }
}
