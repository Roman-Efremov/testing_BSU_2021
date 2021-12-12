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

    @FindBy(xpath = "//div[@class='root GSMZw']/form//div[text()='Откуда']/..//input")
    private WebElement sourceInput;

    @FindBy(xpath = "//div[@class='root GSMZw']/form//div[text()='Куда']/..//input")
    private WebElement destinationInput;

    @FindBy(xpath = "//div[@class='root GSMZw']/form/button[@class='vHqxX z8gtM']")
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
        return getElement("//header//div[@class='WFYfN']/span[@class='text']").getText();
    }

    public String getTravelDate() {
        return getElement("//header//div[@class='kzvZe']/span[@class='ZlSD1']").getText();
    }

    public boolean isDestinationEmpty() {
        return getElement("//div[@class='root GSMZw']/form//div[text()='Куда']/../div[@class='nAZcZ LRlEo' and text()='Куда']")
                .isEnabled();
    }

    private WebElement getMatchingListElement(String name) {
        By elementWithNameLocator = By.xpath("//*[@class='EW8x1']//div[text()='" + name + "']");
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(elementWithNameLocator));
    }

    private WebElement getElement(String xpath) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
}
