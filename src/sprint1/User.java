package sprint1;

enum Type {
    Driver,
    Customer
}

public abstract class User {
    protected String username;
    protected String password;
    protected String mobile;
    protected boolean suspend;
    protected Type type;
    protected double balance;
    
    public User(String username, String password, String mobile, Type type) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.suspend = false;
        this.type = type;
       
    }
    
    public User() {
        this.username = "";
         this.password = "";
        this.mobile = "";
        this.suspend = false;

    }
    
    //public abstract void register(String username, String password, String mobile, String license, String nationalID, String availableSeats);

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSuspend(boolean suspend) {
        this.suspend = suspend;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean isSuspend() {
        return suspend;
    }

    public Type getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", mobile=" + mobile + ", suspend=" + suspend + ", type=" + type + '}';
    }

    
}
