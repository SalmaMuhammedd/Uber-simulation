package sprint1;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Ride {

    String source, destination;
    Customer customer;
    HashMap<Driver, Double> driverPrice;
    int numOfPassengers;
    LocalTime acceptTime;
    long duration;
    LocalTime arrivingTime;
    LocalTime destTime;

    public Ride(String source, String destination,int numOfPassengers, Customer customer) {
        this.source = source;
        this.destination = destination;
        this.customer = customer;
        this.numOfPassengers = numOfPassengers;
        this.driverPrice = new HashMap<Driver, Double>();

    }

    public Ride(String source, String destination, double price, Driver driver, Customer customer, int numOfPassengers) {
        this.source = source;
        this.destination = destination;
        this.customer = customer;
        this.numOfPassengers = numOfPassengers;
        driverPrice = new HashMap<>();
        driverPrice.put(driver, price);
        this.duration = 10;
    }

    public Ride() {
        this.source = "";
        this.destination = "";
        this.customer = null;
        this.driverPrice = new HashMap<>();
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public HashMap<Driver, Double> getDriverPrice() {
        for (Map.Entry<Driver, Double> entry : driverPrice.entrySet()) {
            System.out.println(entry.getKey() + ", Price= " + entry.getValue());
        }
        return driverPrice;
    }

    public Driver getDriver() {
        Driver driver = new Driver();
        for (Map.Entry<Driver, Double> entry : driverPrice.entrySet()) {
            driver = (entry.getKey());
        }
        return driver;
    }
    public double getPrice() {
        double price = 0;
        for (Map.Entry<Driver, Double> entry : driverPrice.entrySet()) {
            price = (entry.getValue());
        }
        return price;
    }

    @Override
    public String toString() {
        String ride = "Ride{" + " Customer Name: " + customer.username + ", source=" + source + ", destination=" + destination + ", Acceptance Time: " + acceptTime + '}';
        return ride;
    }

}
