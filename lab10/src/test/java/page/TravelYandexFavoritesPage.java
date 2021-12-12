package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelYandexFavoritesPage extends AbstractPage {

    public static String PAGE_URL = "https://travel.yandex.ru/favorites/";

    public TravelYandexFavoritesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected AbstractPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public boolean containsHotel(String hotelName) {
        By hotelLocator = By.xpath("//div[@class='MWUl_']//a[text()[contains(.,'" + hotelName + "')]]");
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(hotelLocator)).isDisplayed();
    }
}
