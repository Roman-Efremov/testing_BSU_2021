package service;

import model.TripData;

import java.util.Date;

import static utils.DateUtils.*;

public class TripDataCreator {

    public static String TESTDATA_TRAIN_SOURCE = "testdata.train.source";
    public static String TESTDATA_TRAIN_DESTINATION = "testdata.train.destination";
    public static String TESTDATA_AVIA_SOURCE = "testdata.avia.source";
    public static String TESTDATA_AVIA_DESTINATION = "testdata.avia.destination";
    public static String TESTDATA_HOTEL_DESTINATION = "testdata.hotel.destination";


    public static TripData aviaWithDataFromProperty() {
        Date currentDate = getCurrentDate();
        Date nextDate = getNextDate(currentDate, 1);
        return new TripData(TestDataReader.getTestData(TESTDATA_AVIA_SOURCE),
                TestDataReader.getTestData(TESTDATA_AVIA_DESTINATION),
                currentDate,
                nextDate);
    }

    public static TripData aviaWithDateToGreaterThanDateFrom() {
        Date currentDate = getCurrentDate();
        Date nextDate = getNextDate(currentDate, 1);
        return new TripData(TestDataReader.getTestData(TESTDATA_AVIA_SOURCE),
                TestDataReader.getTestData(TESTDATA_AVIA_DESTINATION),
                nextDate,
                currentDate);
    }

    public static TripData trainWithDataFromProperty() {
        Date currentDate = getCurrentDate();
        Date nextDate = getNextDate(currentDate, 1);
        return new TripData(TestDataReader.getTestData(TESTDATA_TRAIN_SOURCE),
                TestDataReader.getTestData(TESTDATA_TRAIN_DESTINATION),
                currentDate,
                nextDate);
    }

    public static TripData hotelWithDataFromProperty() {
        Date currentDate = getCurrentDate();
        Date nextDate = getNextDate(currentDate, 1);
        return new TripData("",
                TestDataReader.getTestData(TESTDATA_HOTEL_DESTINATION),
                currentDate,
                nextDate);
    }

    public static TripData hotelWithDateToGreaterThanDateFrom() {
        Date currentDate = getCurrentDate();
        Date nextDate = getNextDate(currentDate, 1);
        return new TripData("",
                TestDataReader.getTestData(TESTDATA_HOTEL_DESTINATION),
                nextDate,
                currentDate);
    }
}
