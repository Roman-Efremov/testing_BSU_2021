package model;

import java.util.Objects;

public class Passenger {

    private String name;
    private String birthDate;
    private String nationality;
    private String document;
    private String seriesAndNumber;

    public Passenger(String name, String birthDate, String nationality, String document, String seriesAndNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.document = document;
        this.seriesAndNumber = seriesAndNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getSeriesAndNumber() {
        return seriesAndNumber;
    }

    public void setSeriesAndNumber(String seriesAndNumber) {
        this.seriesAndNumber = seriesAndNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return name.equals(passenger.name) &&
                birthDate.equals(passenger.birthDate) &&
                nationality.equals(passenger.nationality) &&
                document.equals(passenger.document) &&
                seriesAndNumber.equals(passenger.seriesAndNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, nationality, document, seriesAndNumber);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", nationality='" + nationality + '\'' +
                ", document='" + document + '\'' +
                ", seriesAndNumber='" + seriesAndNumber + '\'' +
                '}';
    }
}
