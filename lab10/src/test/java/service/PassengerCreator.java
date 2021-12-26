package service;

import model.Passenger;

public class PassengerCreator {

    public static String TESTDATA_PASSENGER_NAME = "testdata.passenger.name";
    public static String TESTDATA_PASSENGER_BIRTH_DATE = "testdata.passenger.birth_date";
    public static String TESTDATA_PASSENGER_NATIONALITY = "testdata.passenger.nationality";
    public static String TESTDATA_PASSENGER_DOCUMENT = "testdata.passenger.document";
    public static String TESTDATA_PASSENGER_SERIES_AND_NUMBER = "testdata.passenger.series_and_number";

    public static Passenger withCredentialsFromProperty() {
        return new Passenger(TestDataReader.getTestData(TESTDATA_PASSENGER_NAME),
                TestDataReader.getTestData(TESTDATA_PASSENGER_BIRTH_DATE),
                TestDataReader.getTestData(TESTDATA_PASSENGER_NATIONALITY),
                TestDataReader.getTestData(TESTDATA_PASSENGER_DOCUMENT),
                TestDataReader.getTestData(TESTDATA_PASSENGER_SERIES_AND_NUMBER));
    }
}