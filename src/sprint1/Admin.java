
package sprint1;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    ApplicationSystem system;
    Discount discount;
    ArrayList<String> discountAreas;

    public Admin(ApplicationSystem system) {
        this.system = system;
        discount = new Discount();
        discountAreas = new ArrayList<>();
        discountAreas.add("giza");
        discountAreas.add("cairo");
    }
    
    public void verify(){
        for(Driver driver: system.pendingDriverList){
            driver.verified = true;
            system.users.add(driver);
            system.verifiedDrivers.add(driver);
            system.pendingDriverList = null;
            system.pendingDriverList = new ArrayList<>();
        }
    }
    
    public void suspendUser(int indexChoice){
        
        system.users.get(indexChoice-1).setSuspend(true);
        
    }
    
    public ArrayList<Driver> getPendingDriverList() {
        return system.getPendingDriverList();
    }
    
    public void getAllOffers(){
        for(RideOffer ride : system.rideOffers)
            System.out.println(ride);
    }
    
    public void getAllAcceptedRides(){
        
        for(Ride ride : system.acceptedRides){
            System.out.println(ride);
        }
            
    }
    
    public void getArrivingEvent(){
        
        for(Ride ride : system.acceptedRides){
            System.out.println("Arriving Time: " + ride.arrivingTime+", Driver Name: "+ ride.getDriver().getUsername() +", Customer Name: "+ ride.customer.getUsername());
        }
    }
    
    public void getDestinationEvent(){
        for(Ride ride : system.acceptedRides){
            System.out.println("Destination Time: " + ride.destTime +", Driver Name: "+ ride.getDriver().getUsername() +", Customer Name: "+ ride.customer.getUsername());
        }
    }
    
    public void discounts(Ride ride) {
       discount.birthDayDiscount(ride);
       discount.discountAreas(discountAreas, ride);
       discount.discountTwoPassengers(ride);
       discount.firstRideDiscount(ride);
       discount.holidaysDiscount(system.publicHolidays, ride);
    }
}
