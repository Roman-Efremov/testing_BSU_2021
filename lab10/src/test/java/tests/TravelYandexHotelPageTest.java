package tests;

import model.TripData;
import org.testng.annotations.Test;
import page.TravelYandexFavoritesPage;
import page.TravelYandexHotelPage;
import service.TripDataCreator;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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
        boolean favoritesPage = new TravelYandexFavoritesPage(driver)
                .containsHotel(hotelName);

        assertTrue(favoritesPage, "hotel \"" + hotelName + "\" wasn't add to favorites");
    }

    @Test
    public void testSelectionOfDateToGreaterThanDateFrom() {
        TripData tripData = TripDataCreator.hotelWithDateToGreaterThanDateFrom();
        boolean isDateFromEmpty = new TravelYandexHotelPage(driver)
                .openPage()
                .enterDestination(tripData.getDestination())
                .enterDateTo(tripData.getDateTo())
                .isDateFromClickable(tripData.getDateFrom());

        assertFalse(isDateFromEmpty, "date from is not empty");
    }
}
