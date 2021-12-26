package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelYandexFavoritesPage extends AbstractPage {

    public static String PAGE_URL = "https://travel.yandex.ru/favorites/";

    private static final String HOTEL_XPATH = "//div[@class='MWUl_']//a[text()[contains(.,'%1$s')]]";

    public TravelYandexFavoritesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public TravelYandexFavoritesPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public boolean containsHotel(String hotelName) {
        By hotelLocator = By.xpath(String.format(HOTEL_XPATH, hotelName));
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(hotelLocator)).isDisplayed();
    }
}
