package model;

import java.util.Objects;

public class Order {

    private String number;
    private String contactData;

    public Order(String numberOfOrder, String contactData) {
        this.number = numberOfOrder;
        this.contactData = contactData;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContactData() {
        return contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return number.equals(order.number) &&
                contactData.equals(order.contactData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, contactData);
    }

    @Override
    public String toString() {
        return "Order{" +
                "numberOfOrder=" + number +
                ", contactData='" + contactData + '\'' +
                '}';
    }
}
