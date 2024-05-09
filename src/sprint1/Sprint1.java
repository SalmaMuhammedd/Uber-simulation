package sprint1;

import java.util.ArrayList;
import java.util.Scanner;

public class Sprint1 {
    
    public static void main(String[] args) {
    	
        ApplicationSystem system = ApplicationSystem.getInstance();
        Admin admin = new Admin(system);

        try (Scanner scan = new Scanner(System.in)) {
        	
			while(true){
			    System.out.println("1- Register\n2- Login\n3- Admin\n4- Exit");
			    int choice = scan.nextInt();

			    if(choice == 1){
			        int registrationChoice;
			        System.out.println("1- Driver\n2- Customer");
			        
			        registrationChoice = scan.nextInt();
			        
			        if(registrationChoice == 1){
			        	Scanner scann = new Scanner(System.in);
			        	
			            Driver driver = new Driver();
			            
			            System.out.print("Enter Driver Username: ");
			            String username = scann.nextLine();
			            
			            System.out.print("Enter Driver Password: ");
			            String password = scann.nextLine();
			            
			            System.out.print("Enter Driver Mobile: ");
			            String mobile = scann.nextLine();
			            
			            System.out.print("Enter Driver Licence: ");
			            String license = scann.nextLine();
			            
			            System.out.print("Enter Driver National-ID: ");
			            String nationalID = scann.nextLine();
			            
			            System.out.print("Enter Available Seats: ");
			            int availableSeats = scann.nextInt();
			            
			            driver.register( username,  password,  mobile,  license,  nationalID,  availableSeats);
			            system.addPendingDriver(driver);
			            
			        } else if (registrationChoice == 2) {
			            Customer customer = new Customer();
			            
			            Scanner scann = new Scanner(System.in);
			            
			            System.out.print("Enter Customer Username: ");
			            String username = scann.nextLine();
			            
			            System.out.print("Enter Customer Password: ");
			            String password = scann.nextLine();
			            
			            System.out.print("Enter Customer Mobile: ");
			            String mobile = scann.nextLine();
			            
			            System.out.print("Enter Customer Birthdate ('YYYY-MM-DD'): ");
			            String bithDate = scann.nextLine();
			            
			            customer.register( username,  password,  mobile,  bithDate);
			            system.addUser(customer);
			            
			        } else 
			            System.out.println("invalid choice");
			        
			    }
			    else if (choice == 2){
			    	
			        System.out.print("Enter username: ");
			        String username = scan.next();

			        System.out.print("Enter password: ");
			        String password = scan.next();
			        
			        User user = system.login( username,  password);
			    	if(user  == null) {
			            System.out.println("User not registred");
			            
			        } else if (user.isSuspend()) {
			            System.out.println("User is suspend");
			
			        } else if (user.getType() == Type.Customer) {
			             Customer customer = (Customer) user;
			             
			             Scanner scann = new Scanner(System.in);
			             System.out.println("Welcome: " + customer.getUsername());
			             while (true) {
			                 System.out.println("1- Request Ride\n2- Get Offers\n3- Rate Driver To complete The Ride\n4-Show Balance\n5- Exit");
			                 int customerChoice = scan.nextInt();
			                 switch (customerChoice) {

			                     case 1:
			                         System.out.println("Enter Source And Destination And Number Of Passengers With You");
			                         String source = scann.next();
			                         String destination = scann.next();
			                         int numOfPassengers = scann.nextInt();
			                         customer.requestRide(source, destination, numOfPassengers+1, system);
			                         break;

			                     case 2:

			                         customer.getOffers();
			                         if (!customer.offers.isEmpty()) {
			                             System.out.println("Choose number of offer");
			                             int offerChoice = scann.nextInt();
			                             Ride ride;

			                             ride = new Ride(customer.offers.get(offerChoice - 1).source, customer.offers.get(offerChoice - 1).dest, customer.offers.get(offerChoice - 1).price, customer.offers.get(offerChoice - 1).driver, customer,customer.offers.get(offerChoice - 1).customer.rideOrg.ride.numOfPassengers);
			                             if(!system.decreaseAvailableSeats(ride)){
			                                 System.out.println("No Available Seats");
			                                 break;
			                             }

			                             customer.offers.get(offerChoice - 1).driver.Rides.add(ride);
			                             Driver chosenDriver = customer.offers.get(offerChoice - 1).driver;
			                             admin.discounts(ride);
			                             customer.acceptRide(ride);
			                             
			                             chosenDriver.arrived(ride);
			                             system.setAllAcceptedRides();
			                             

			                         } else {
			                             System.out.println("There are no offers available");
			                         }

			                         break;

			                     case 3:
			                     	
			                         ArrayList<Ride> found = new ArrayList<>();
			                         if(!customer.Rides.isEmpty()){
			                             for(Ride ride : customer.Rides){
			                             	
			                             	System.out.println("Provide a star rating to the driver from 1 to 5 (1 worst, 5 Best)");
			                                ride.getDriverPrice();
			                             	int rate = scann.nextInt();
			                             	customer.rateDriver( rate,  ride,  found);

			                             }
			                             customer.Rides.removeAll(found);
			                         }
			                         
			                         else{
			                             System.out.println("Your Rides are Empty!");
			                         }
			                     	
			                         
			                         break;

			                     case 4:
			                         System.out.println("Balance: " + customer.getBalance());
			                         break;

			                     case 5:
			                         break;

			                     default:
			                         System.out.println("Invalid Choice");
			                         break;
			                 }
			                 if (customerChoice == 5) {
			                     break;
			                 }
			             } 	
			             
			             
			             //------------
			             
			             
			        } else if (user.getType() == Type.Driver) {
			            Driver driver = (Driver) user;
			            
			            
			            Scanner scann = new Scanner(System.in);
			            System.out.println("Welcome: " + driver.getUsername());
			            while (true) {
			                System.out.println("1- Add Favorite Areas\n2- Requested rides\n3- List Your Ratings\n4- Show Balance\n5- Exit");
			                int driverChoice = scann.nextInt();
			                switch (driverChoice) {
			                    case 1: {
			                        String area;
			                        System.out.println("After finishing adding areas enter finish");

			                        while (true) {
			                            area = scann.next();
			                            if (area.equals("finish")) {
			                                break;
			                            }
			                            driver.addArea(area);
			                        }
			                        break;
			                    }

			                    case 2:
			                        System.out.println("Driver's rides:");
			                        System.out.println(driver.getRequestedRides());
			                        
			                        ArrayList<Ride> found = new ArrayList<>();
			                        
			                        for(Ride ride : driver.requestedRides){
			                        	System.out.print("Suggest a price from " + ride.source +" to " + ride.destination + ": ");
			                        	double price = scann.nextDouble();
			                        	driver.suggestPrice(price, found, ride);
			                        }
			                        driver.requestedRides.removeAll(found);
			                        
			                        
			                        system.setAllOffers();
			                        break;

			                    case 3:
			                        if (driver.ratings.isEmpty()) {
			                            System.out.println("You have no ratings");
			                        } else {
			                            driver.getRatings();
			                        }
			                        break;
			                    case 4:
			                        System.out.println("Balance: " + driver.getBalance());
			                        break;
			                    case 5:
			                        break;
			                    default:
			                        System.out.println("Invalid input");
			                        break;

			                }
			                if (driverChoice == 5) {
			                    break;
			                }
			            }
			            //----------------
			            

			        }
			        
			    } else if(choice == 3) {
			    	
			    	
			        Scanner scann = new Scanner(System.in);
			        System.out.println("Welcome Admin");
			        while (true) {
			            System.out.println("1-List All Pending Drivers\n2- Suspend User\n3-Get All Offers\n4-Get All Accepted Rides\n5-Show Arriving To User Location Event\n6-Show Arriving To User Destination Event\n7-Exit");

			            int adminChoice = scann.nextInt();

			            if (adminChoice == 1) {
			                admin.getPendingDriverList();
			                System.out.println("1-Verify Drivers\n2- Back");
			                int listChoice = scann.nextInt();

			                switch (listChoice) {
			                    case 1:
			                        admin.verify();
			                        break;
			                    case 2:
			                        break;
			                    default:
			                        System.out.println("invaild choice");

			                }
			            } else if (adminChoice == 2) {
			            	system.getUsers();
			            	System.out.println("Choose the number of the user to suspend: ");
			            	int indexChoice = scann.nextInt();
			            	
			                admin.suspendUser(indexChoice);
			            } else if (adminChoice == 3) {
			                admin.getAllOffers();
			            } else if (adminChoice == 4) {
			                admin.getAllAcceptedRides();
			            } else if (adminChoice == 5) {
			                admin.getArrivingEvent();
			            } else if (adminChoice == 6) {
			                admin.getDestinationEvent();
			            } else {
			                break;
			            }
			        }
			    
			    //------------
			        
			    } else if (choice == 4) 
			        break;
			}
		}
        
       
    }
    
}
