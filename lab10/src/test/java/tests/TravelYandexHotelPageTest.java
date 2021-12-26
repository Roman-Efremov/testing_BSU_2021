package tests;

import model.TripData;
import org.testng.annotations.Test;
import page.TravelYandexFavoritesPage;
import page.TravelYandexHotelPage;
import service.TripDataCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TravelYandexHotelPageTest extends CommonConditions {

    @Test
    public void testAddHotelToFavorite() {
        TripData tripData = TripDataCreator.hotelWithDataFromProperty();
        TravelYandexHotelPage hotelPage = new TravelYandexHotelPage(driver)
                .openPage()
                .enterDestination(tripData.getDestination())
                .enterDateTo(tripData.getDateTo())
                .enterDateFrom(tripData.getDateFrom())
                .submit()
                .addFirstHotelToFavorite();
        String hotelName = hotelPage.getFirstHotelName();
        boolean isFavoritesPageContainsHotel = new TravelYandexFavoritesPage(driver).containsHotel(hotelName);

        assertThat(isFavoritesPageContainsHotel, equalTo(true));
    }

    @Test
    public void testSelectionOfDateToGreaterThanDateFrom() {
        TripData tripData = TripDataCreator.hotelWithDateToGreaterThanDateFrom();
        boolean isDateFromEmpty = new TravelYandexHotelPage(driver)
                .openPage()
                .enterDestination(tripData.getDestination())
                .enterDateTo(tripData.getDateTo())
                .isDateFromClickable(tripData.getDateFrom());

        assertThat(isDateFromEmpty, equalTo(false));
    }
}
