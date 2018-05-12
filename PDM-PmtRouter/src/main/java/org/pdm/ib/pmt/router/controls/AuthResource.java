package org.pdm.ib.pmt.router.controls;

import org.pdm.ib.pmt.router.command.UserCommand;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.entities.User;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.pdm.ib.pmt.router.repos.UserRepository;
import org.pdm.ib.pmt.router.response.AuthorizeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthResource {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/authorize")
    public AuthorizeResponse authorize(@RequestBody UserCommand user) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (!userOptional.isPresent()) {
            return AuthorizeResponse.builder().isAuthorized(false)
                    .referenceId(null)
                    .username(null)
                    .build();
        } else {
            User userEntity = userOptional.get();
            Optional<Customer> customerOptional = customerRepository.findById(userEntity.getId());
            Customer customer = customerOptional.get();
            return AuthorizeResponse.builder()
                    .isAuthorized(true)
                    .referenceId(userEntity.getReferenceId())
                    .username(userEntity.getUsername())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .accessToken(userEntity.getAccessToken())
                    .build();
        }
    }
}
