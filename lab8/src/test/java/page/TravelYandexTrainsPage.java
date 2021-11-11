package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelYandexTrainsPage extends AbstractPage
{
    public static String TRAINS_PAGE_URL = "https://travel.yandex.ru/trains/";

    @FindBy(xpath = "//form[@class='RhypN XSMNz yIzkO Ks4Vt DE0Wm YHMga hgH6Z']//div[text()='Откуда']/..//input")
    private WebElement fromInput;

    @FindBy(xpath = "//form[@class='RhypN XSMNz yIzkO Ks4Vt DE0Wm YHMga hgH6Z']//div[text()='Куда']/..//input")
    private WebElement toInput;

    @FindBy(xpath = "//form[@class='RhypN XSMNz yIzkO Ks4Vt DE0Wm YHMga hgH6Z']/button[@class='vHqxX z8gtM']")
    private WebElement findButton;


    public TravelYandexTrainsPage(WebDriver driver)
    {
        super(driver);
    }

    public TravelYandexTrainsPage openPage()
    {
        driver.get(TRAINS_PAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@class='RhypN XSMNz yIzkO Ks4Vt DE0Wm YHMga hgH6Z']//div[text()='Откуда']/..//input")));
        return this;
    }

    public TravelYandexTrainsPage enterFrom(String from)
    {
        fromInput.sendKeys(Keys.CONTROL + "a");
        fromInput.sendKeys(from);
        WebElement listElement = getMatchingListElement(from);
        listElement.click();
        return this;
    }

    public TravelYandexTrainsPage enterTo(String to)
    {
        toInput.sendKeys(to);
        WebElement listElement = getMatchingListElement(to);
        listElement.click();
        return this;
    }

    public TravelYandexTrainsPage submit()
    {
        findButton.click();
        return this;
    }

    public String getSourceAndDestination()
    {
        return getElement("//header//div[@class='WFYfN']/span[@class='text']").getText();
    }

    public String getDateString()
    {
        return getElement("//header//div[@class='kzvZe']/span[@class='ZlSD1']").getText();
    }


    private WebElement getMatchingListElement(String name)
    {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.textToBe(By.xpath("//*[@class='EW8x1']/div[1]/div[1]"), name));
        return driver.findElement(By.xpath("//*[@class='EW8x1']/div[1]"));
    }

    private WebElement getElement(String xpath)
    {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }
}
