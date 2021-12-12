package page;

import model.TripData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @FindBy(xpath = "//div[@class='root KPSVc']/form//div[text()='Откуда']/..//input")
    private WebElement sourceInput;

    @FindBy(xpath = "//div[@class='root KPSVc']/form//div[text()='Куда']/..//input")
    private WebElement destinationInput;

    @FindBy(xpath = "//div[@class='root KPSVc']/form//div[text()='Туда']")
    private WebElement dateTo;

    @FindBy(xpath = "//div[@class='root KPSVc']/form//div[text()='Обратно']")
    private WebElement dateFrom;

    @FindBy(xpath = "//div[@class='root KPSVc']/form/button[@class='vHqxX z8gtM']")
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
        return getElement("//header//div[@class='WFYfN']/span[@class='text']").getText();
    }

    public String getTravelDate() {
        return getElement("//header//div[@class='kzvZe']/span[@class='ZlSD1']").getText();
    }

    public boolean isDateFromEmpty() {
        return getElement("//div[@class='root KPSVc']/form//div[@class='nAZcZ SGaYr' and text()='Обратно']").isDisplayed();
    }

    private WebElement getMatchingListElement(String name) {
        By elementWithNameLocator = By.xpath("//*[@class='EW8x1']//div[text() = '" + name + "']");
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
