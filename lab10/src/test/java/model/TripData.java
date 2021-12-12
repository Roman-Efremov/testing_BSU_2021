package model;

import java.util.Date;
import java.util.Objects;


public class TripData {

    private String source;
    private String destination;
    private Date dateTo;
    private Date dateFrom;

    public TripData(String source, String destination, Date dateTo, Date dateFrom) {
        this.source = source;
        this.destination = destination;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
    }

    public TripData(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripData)) return false;
        TripData tripData = (TripData) o;
        return Objects.equals(source, tripData.source) &&
                Objects.equals(destination, tripData.destination) &&
                Objects.equals(dateTo, tripData.dateTo) &&
                Objects.equals(dateFrom, tripData.dateFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, dateTo, dateFrom);
    }

    @Override
    public String toString() {
        return "TripData{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                '}';
    }
}
