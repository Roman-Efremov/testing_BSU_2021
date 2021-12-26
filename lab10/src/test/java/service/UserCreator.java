package service;

import model.User;

public class UserCreator {

    public static String TESTDATA_USER_NAME = "testdata.user.name";
    public static String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withCredentialsFromProperty() {
        return new User(TestDataReader.getTestData(TESTDATA_USER_NAME),
                TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }
}
