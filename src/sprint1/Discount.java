package sprint1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Discount {

    final double discountPercentage10 = 0.1;
    final double discountPercentage5 = 0.05;
    
    public void upgradeBalance(Ride ride, double discountPercentage){
        ride.getCustomer().balance += discountPercentage * ride.getPrice();
    }
    
    public void discountAreas(ArrayList<String> discountAreas, Ride ride) {
        if (discountAreas.contains(ride.destination)) {
            upgradeBalance(ride,  discountPercentage10);
        }
    }

    public void discountTwoPassengers(Ride ride) {
        if (ride.numOfPassengers >= 2) {
            upgradeBalance(ride, discountPercentage5);
        }
        
    }

    public void firstRideDiscount(Ride ride) {
        if (ride.getCustomer().Rides.isEmpty()) {
            upgradeBalance(ride,  discountPercentage10);
        }
    }

    public void birthDayDiscount(Ride ride) {
        LocalDate date = LocalDate.parse(ride.getCustomer().bithDate);
        if (date.getDayOfMonth() == LocalDate.now().getDayOfMonth() && date.getMonthValue() == LocalDate.now().getMonthValue()) {
            upgradeBalance(ride,  discountPercentage10);
        }
    }

    public void holidaysDiscount(ArrayList<LocalDate> publicHolidays, Ride ride) {
        for (LocalDate date : publicHolidays) {
            if (date.getDayOfMonth() == LocalDate.now().getDayOfMonth() && date.getMonthValue() == LocalDate.now().getMonthValue()) {
                upgradeBalance(ride, discountPercentage5);
                break;
            }
        }
    }

}
