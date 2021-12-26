package service;

import model.Order;

public class OrderCreator {

    public static String TESTDATA_ORDER_NUMBER = "testdata.order.number";
    public static String TESTDATA_ORDER_CONTACT_DATA = "testdata.order.contact_data";

    public static Order withIncorrectCredentialsFromProperty() {
        return new Order(TestDataReader.getTestData(TESTDATA_ORDER_NUMBER),
                TestDataReader.getTestData(TESTDATA_ORDER_CONTACT_DATA));
    }
}
