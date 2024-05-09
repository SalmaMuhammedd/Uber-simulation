package sprint1;

import java.time.LocalDate;
import java.util.ArrayList;

public class ApplicationSystem {

    protected ArrayList<User> users;
    protected ArrayList<Driver> pendingDriverList;
    protected ArrayList<Driver> verifiedDrivers;
    protected ArrayList<RideOffer> rideOffers;
    protected ArrayList<Ride> acceptedRides;
    protected ArrayList<LocalDate> publicHolidays;
    private static ApplicationSystem system;
    
    private ApplicationSystem() {
        users = new ArrayList<>();
        pendingDriverList = new ArrayList<>();
        verifiedDrivers = new ArrayList<>();
        rideOffers = new ArrayList<>();
        acceptedRides = new ArrayList<>();
        publicHolidays = new ArrayList<>();
        publicHolidays.add(LocalDate.of(2022, 01, 25));
        publicHolidays.add(LocalDate.of(2021, 12, 31));
        publicHolidays.add(LocalDate.of(2022, 10, 06));
        publicHolidays.add(LocalDate.of(2021, 05, 03));
    }
    
    public static ApplicationSystem getInstance(){
        if(system == null)
            system = new ApplicationSystem();
        
        return system;
    }
    public User login(String username, String password) {

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void setAllOffers() {
        for (User user : users) {
            if (user.getType() == Type.Customer) {
                Customer customer = (Customer) user;
                for (RideOffer offer : customer.offers) {
                    if (!rideOffers.contains(offer)) {
                        rideOffers.add(offer);
                    }
                }
            }
        }
    }

    public void setAllAcceptedRides() {
        for (User user : users) {
            if (user.getType() == Type.Customer) {
                Customer customer = (Customer) user;
                for (Ride accepted : customer.Rides) {
                    if (!acceptedRides.contains(accepted)) {
                        acceptedRides.add(accepted);
                    }
                }
            }
        }
    }

    public boolean decreaseAvailableSeats(Ride ride) {
        if (ride.getDriver().availableSeats >= ride.numOfPassengers) {
            ride.getDriver().availableSeats -= ride.numOfPassengers;
            return true;
        }
        return false;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addPendingDriver(Driver driver) {
        pendingDriverList.add(driver);
    }

    public ArrayList<User> getUsers() {
        System.out.println(users);
        return users;
    }

    public void getVerifiedDrivers() {
        System.out.println(verifiedDrivers);

    }

    public void addVerifiedDriver(Driver driver) {
        verifiedDrivers.add(driver);
    }

    public ArrayList<Driver> getPendingDriverList() {
        System.out.println(pendingDriverList);
        return pendingDriverList;
    }

}
