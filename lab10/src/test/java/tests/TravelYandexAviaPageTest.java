package tests;

import model.TripData;
import org.testng.annotations.Test;
import page.TravelYandexAviaPage;
import service.TripDataCreator;

import static org.apache.commons.lang3.StringUtils.chop;
import static org.testng.Assert.assertTrue;
import static utils.DateUtils.formatDateToRussian;

public class TravelYandexAviaPageTest extends CommonConditions {

    @Test
    public void testSearchAviaTicketsWithEnteringAllData() {
        TripData tripData = TripDataCreator.aviaWithDataFromProperty();
        TravelYandexAviaPage aviaPage = new TravelYandexAviaPage(driver)
                .openPage()
                .fillForm(tripData)
                .submit();
        String sourceAndDestination = aviaPage.getSourceAndDestination();
        String dates = aviaPage.getTravelDate();

        assertTrue(sourceAndDestination.contains(tripData.getSource())
                && sourceAndDestination.contains(tripData.getDestination())
                && dates.contains(chop(formatDateToRussian(tripData.getDateFrom(), "dd MMM")))
                && dates.contains(chop(formatDateToRussian(tripData.getDateTo(), "dd MMM"))));
    }

    @Test
    public void testSelectionOfDateToGreaterThanDateFrom() {
        TripData tripData = TripDataCreator.aviaWithDateToGreaterThanDateFrom();
        boolean isDateFromEmpty = new TravelYandexAviaPage(driver)
                .openPage()
                .enterDateFrom(tripData.getDateFrom())
                .enterDateTo(tripData.getDateTo())
                .isDateFromEmpty();

       assertTrue(isDateFromEmpty, "date from is not empty");
    }
}
