package tests;

import model.TripData;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.TravelYandexTrainPage;
import service.TestDataReader;
import service.TripDataCreator;

import static org.testng.Assert.assertTrue;

public class TravelYandexTrainPageTest extends CommonConditions {

    protected static final String TESTDATA_DATE_TODAY = "testdata.date.today";

    @Test
    public void testSearchTrainTicketsWithoutEnteringDate() {
        TripData tripData = TripDataCreator.trainWithDataFromProperty();
        String expectedDate = TestDataReader.getTestData(TESTDATA_DATE_TODAY);

        TravelYandexTrainPage trainsPage = new TravelYandexTrainPage(driver)
                .openPage()
                .enterSource(tripData.getSource())
                .enterDestination(tripData.getDestination())
                .submit();
        String sourceAndDestination = trainsPage.getSourceAndDestination();

        assertTrue(sourceAndDestination.contains(tripData.getSource())
                        && sourceAndDestination.contains(tripData.getDestination())
                        && trainsPage.getTravelDate().equals(expectedDate));
    }

    @Test
    public void testSearchTrainTicketsWithoutEnteringDestination() {
        TripData tripData = TripDataCreator.trainWithDataFromProperty();

        boolean isDestinationEmpty = new TravelYandexTrainPage(driver)
                .openPage()
                .enterSource(tripData.getSource())
                .submit()
                .isDestinationEmpty();

        assertTrue(isDestinationEmpty, "destination is not empty");
    }
}
