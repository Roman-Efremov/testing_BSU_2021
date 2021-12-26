package tests;

import model.Order;
import model.Passenger;
import model.User;
import org.testng.annotations.Test;
import page.PassportYandexLoginPage;
import service.OrderCreator;
import service.PassengerCreator;
import service.UserCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TravelYandexMyPageTest extends CommonConditions {

    @Test
    public void testSearchIncorrectOrder() {
        Order order = OrderCreator.withIncorrectCredentialsFromProperty();
        User user = UserCreator.withCredentialsFromProperty();

        boolean isOrderFound = new PassportYandexLoginPage(driver)
                .openPage()
                .login(user)
                .findOrder(order)
                .isOrderNotFoundMessageDisplayed();

        assertThat(isOrderFound, equalTo(true));
    }

    @Test
    public void testAddNewPassenger() {
        Passenger passenger = PassengerCreator.withCredentialsFromProperty();
        User user = UserCreator.withCredentialsFromProperty();

        boolean isPassengerAdded =  new PassportYandexLoginPage(driver)
                .openPage()
                .login(user)
                .navigateToPassengers()
                .addPassenger(passenger)
                .isPassengerAdded(passenger.getName());

        assertThat(isPassengerAdded, equalTo(true));
    }
}
