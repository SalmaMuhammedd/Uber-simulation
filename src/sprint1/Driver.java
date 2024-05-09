
package sprint1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver extends User{
    
    protected String nationalID;
    protected String license;
    protected boolean verified;
    protected double averageRating;
    protected int availableSeats;
    protected ArrayList<String> favAreas;
    protected ArrayList<Ride> requestedRides;
    protected ArrayList<Ride> Rides;
    protected HashMap<Customer, Integer> ratings;
    
    public Driver() {
        super();
        this.nationalID = "";
        this.license = "";
        this.averageRating = 0;
        this.verified = false;
        this.favAreas = new ArrayList<>();
        this.requestedRides = new ArrayList<>();
        this.Rides = new ArrayList<>();
        this.ratings = new HashMap<>();
        this.balance = 0;
    }
    

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getNationalID() {
        return nationalID;
    }

    public String getLicense() {
        return license;
    }

    public boolean isVerified() {
        return verified;
    }
    
    public void addArea(String area){
        favAreas.add(area);
    }
    
    public void updateRides(Ride ride){
        
        requestedRides.add(ride); 
    }
    
    public ArrayList<Ride> getRequestedRides(){
        return requestedRides;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public HashMap<Customer, Integer> getRatings() {
        
        for (Map.Entry<Customer, Integer> entry : ratings.entrySet())
        {
            System.out.println(entry.getKey() + ", Their rating= " + entry.getValue());
        }
        return ratings;
    }

    public void setRatings(HashMap<Customer, Integer> ratings) {
        this.ratings = ratings;
    }
    
    //-------------------------------
    public void suggestPrice(double price, ArrayList<Ride> found, Ride ride){
        ride.driverPrice.put(this, price);
        RideOffer rideOffer = new RideOffer(ride.source, ride.destination, price, this, ride.customer);
        rideOffer.offerTime = LocalTime.now();
        found.add(ride);

    }
    
    public void arrived(Ride ride){
        ride.arrivingTime = LocalTime.now();
    }
    public void rideComplete(Ride ride) {
         ride.destTime = ride.arrivingTime.plusMinutes(ride.duration);
         balance += ride.getPrice();
    } 
    
    public void setAvailableSeats(int availableSeats){
        this.availableSeats = availableSeats;
    }
    
    public int getAvailableSeats(){
        return availableSeats;
    }
    
    
    public void register(String username, String password, String mobile, String license, String nationalID, int availableSeats) {       
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.license = license;   
        this.nationalID = nationalID;     
        this.availableSeats = availableSeats;   
        this.suspend = false;   
        this.verified = false;  
        this.type = Type.Driver;
    }

    @Override
    public String toString() {
        return "{" + "Name= "+ this.username +", averageRating=" + averageRating + '}';
    }

    
    
    
    
}
