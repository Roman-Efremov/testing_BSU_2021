package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.TravelYandexTrainsPage;

public class TravelYandexTrainsPageTests
{
    private WebDriver driver;

    @BeforeMethod
    public void browserSetup()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSearchResultForTrainTicketsWithoutEnteringDate()
    {
        String source = "Москва";
        String destination = "Минск";
        String date = "Сегодня";

        TravelYandexTrainsPage trainsPage = new TravelYandexTrainsPage(driver)
                .openPage()
                .enterFrom(source)
                .enterTo(destination)
                .submit();

        Assert.assertTrue(
                trainsPage.getSourceAndDestination().contains(source)
                        && trainsPage.getSourceAndDestination().contains(destination)
                        && trainsPage.getDateString().equals(date));
    }

    @AfterMethod
    public void quit()
    {
        driver.quit();
    }
}
