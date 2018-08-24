package hu.zsoltii.jsf.service;

import hu.zsoltii.jsf.data.Customer;
import hu.zsoltii.jsf.db.model.User;
import hu.zsoltii.jsf.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public void login(Customer customer) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            customer.setId(user.getId());
            customer.setName(user.getName());
        } else {
            customer.setPassword(null);
            customer.setName(null);
            customer.setId(null);
            throw new IllegalStateException("Username or Password is not match!");
        }
    }

    public void register(Customer customer) {
        Optional<User> userOptional = userRepository.findByUsername(customer.getUsername());

        if(userOptional.isPresent()) {
            throw new IllegalStateException("Username is exist. Please use an other username or login");
        } else {
            User user = new User();
            user.setName(customer.getName());
            user.setPassword(customer.getPassword());
            user.setUsername(customer.getUsername());

            user = userRepository.save(user);
            customer.setId(user.getId());
        }
    }
}
