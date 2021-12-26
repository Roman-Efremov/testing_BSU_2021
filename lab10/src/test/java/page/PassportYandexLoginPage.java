package page;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PassportYandexLoginPage extends AbstractPage {

    public static String PAGE_URL = "https://passport.yandex.ru/auth/list?mode=auth&retpath=https%3A%2F%2Ftravel.yandex.ru%2Fmy";

    private static final String LOGIN_XPATH = "//div[@class='passp-auth-content']//input[@data-t='field:input-login']";
    private static final String PASSWORD_XPATH = "//div[@class='passp-auth-content']//input[@data-t='field:input-passwd']";
    private static final String SUBMIT_BUTTON_XPATH = "//div[@class='passp-auth-content']//button[@data-t='button:action:passp:sign-in']";

    @FindBy(xpath = LOGIN_XPATH)
    private WebElement loginInput;

    @FindBy(xpath = SUBMIT_BUTTON_XPATH)
    private WebElement submitButton;

    public PassportYandexLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public PassportYandexLoginPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public TravelYandexMyPage login(User user) {
        loginInput.sendKeys(user.getUsername());
        submitButton.click();
        getElement(PASSWORD_XPATH).sendKeys(user.getPassword());
        submitButton.click();
        return new TravelYandexMyPage(driver);
    }
}
