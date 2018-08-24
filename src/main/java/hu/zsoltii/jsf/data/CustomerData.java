package hu.zsoltii.jsf.data;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class CustomerData {

    private Customer customer;

    private boolean logged;
    private boolean registered;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
