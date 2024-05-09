
package sprint1;

import java.util.ArrayList;


public class RideOrganization {
    Ride ride;
    ApplicationSystem system;
    ArrayList <Driver> availableDrivers;
    
    public RideOrganization(Ride ride, ApplicationSystem system) {
        this.ride = ride;
        this.system = system;
        availableDrivers = new ArrayList<>();
       
    }

    public RideOrganization() {
        ride = null;
        system = null;
        availableDrivers = new ArrayList<>();
        
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
    public void matchDrivers(Ride ride, ApplicationSystem system){
        boolean flag = false;
        for(Driver driver : system.verifiedDrivers){
            for(String area : driver.favAreas)
            {
                if(area.equals(ride.source) && ride.numOfPassengers <= driver.availableSeats)
                {
                    availableDrivers.add(driver);
                    driver.updateRides(ride);
                    flag = true;
                    break;
                }
            }
        }
        if(!flag)
            System.out.println("No Available Drivers");
    }
    
    
    public void setSystem(ApplicationSystem system) {
        this.system = system;
    }
    
    public void getAvailableDrivers(){
        System.out.print(availableDrivers);
    }
    
    
    
}
