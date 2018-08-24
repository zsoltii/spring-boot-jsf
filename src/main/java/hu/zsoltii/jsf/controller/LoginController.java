package hu.zsoltii.jsf.controller;

import hu.zsoltii.jsf.data.Customer;
import hu.zsoltii.jsf.data.CustomerData;
import hu.zsoltii.jsf.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController extends SpringBeanAutowiringSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CustomerData customerData;

    @Autowired
    private LoginService loginService;

    public void resetLogin() {
        customerData.setLogged(false);
        customerData.setRegistered(false);

        customerData.setCustomer(null);
    }

    public void preLogin() {
        resetLogin();

        customerData.setCustomer(new Customer());
    }

    public void login() {
        try {
            loginService.login(customerData.getCustomer());
            customerData.setLogged(true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login:", "Login is success."));
        } catch (Exception e) {
            LOGGER.error("login", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login error:", e.getMessage()));
        }
    }

    public void preRegistration() {
        preLogin();
    }

    public void register() {
        try {
            loginService.register(customerData.getCustomer());
            customerData.setRegistered(true);
            customerData.setLogged(true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration:", "Registration is success and you are logged in."));
        } catch (Exception e) {
            LOGGER.error("register", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration error:", e.getMessage()));
        }
    }
}
