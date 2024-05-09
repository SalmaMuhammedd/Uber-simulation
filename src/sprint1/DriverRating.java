package sprint1;

public class DriverRating {
    Driver driver;
    Customer customer;

    public DriverRating(Driver driver, Customer customer) {
        this.driver = driver;
        this.customer = customer;
    }
    
    public void calculateAvg(){
        double sum = 0;
        if(!driver.ratings.isEmpty()){
            for(Customer customer :driver.ratings.keySet())
                sum += driver.ratings.get(customer);
        }
        this.driver.setAverageRating(sum/driver.ratings.size());
    }
    
    public void addRating(int rate){
        driver.ratings.put(customer, rate);
        calculateAvg();
    }
}
