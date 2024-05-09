
package sprint1;

import java.time.LocalTime;
import java.util.ArrayList;

public class Customer extends User{
    
    String bithDate;
    RideOrganization rideOrg;
    ArrayList<RideOffer> offers;
    protected ArrayList<Ride> Rides;
    
    public Customer(String username, String password, String mobile, Type type) {
        super(username, password, mobile, type);
        offers = new ArrayList<>();
        this.Rides = new ArrayList<>();
        this.balance = 1000;
    }
    
    public Customer(){
        super();
        offers = new ArrayList<>();
        this.Rides = new ArrayList<>();
        this.balance = 1000;
    } 

    public void register(String username, String password, String mobile, String bithDate) {
        this.username = username;
        this.password = password;  
        this.mobile = mobile;    
        this.bithDate = bithDate;  
        this.suspend = false;
        this.type = Type.Customer;
    }
    
    public void requestRide(String source, String destination,int numOfPassengers, ApplicationSystem system){
        RideOrganization rideOrg = new RideOrganization(new Ride(source,destination,numOfPassengers ,this), system);
        this.rideOrg = rideOrg;
        this.rideOrg.matchDrivers(rideOrg.ride, system);
    }


    public ArrayList<RideOffer> getOffers() {
        System.out.println(offers);
        return offers;
    }

    public void setOffers(ArrayList<RideOffer> offers) {
        this.offers = offers;
    }
    
    public void pay(Ride ride){
        balance -= ride.getPrice();
    }
    
    public void acceptRide(Ride ride){
        pay(ride);
        Rides.add(ride);
        ride.acceptTime = LocalTime.now();
        this.offers = null;
        this.offers = new ArrayList<>();
        
    }
    
    public void rateDriver(int rate, Ride ride,  ArrayList<Ride> found){
        Driver driver = (Driver) ride.driverPrice.keySet().toArray()[0];
        DriverRating rating = new DriverRating(driver ,this);
        
        rating.addRating(rate);
                
        found.add(ride);
        driver.rideComplete(ride);
        driver.availableSeats += ride.numOfPassengers;

    }
   
    @Override
    public String toString() {
        return "Customer{" + this.username +'}';
    }
    
    
}
