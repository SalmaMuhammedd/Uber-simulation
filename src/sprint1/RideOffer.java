
package sprint1;

import java.time.LocalTime;

public class RideOffer {
   
    String source, dest;
    double price;
    Driver driver;
    Customer customer;
    LocalTime offerTime;

   
    public RideOffer(String source, String dest, double price, Driver driver, Customer customer) {
        this.source = source;
        this.dest = dest;
        this.price = price;
        this.driver = driver;
        this.customer = customer;
        notifyCustomer();
    }
        
    
    public void notifyCustomer(){
       customer.offers.add(this);
    }

    @Override
    public String toString() {
        return "Ride Offer{" + "source=" + source + ", dest=" + dest + ", price=" + price + ", driver=" + driver + " Offer Time: "+ offerTime +'}';
    }

    
    
    
}
