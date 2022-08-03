package com.example.digitalvideostoreapi.services;

import com.example.digitalvideostoreapi.models.CustomerModel;
import com.example.digitalvideostoreapi.models.CustomerRepository;
import com.example.digitalvideostoreapi.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public CustomerModel addCustomer(CustomerModel customer) throws Exception
    {
        if(customer.getUserName().isEmpty() || customer.getEmail().isEmpty() || customer.getPassword().isEmpty())
        {
            throw new Exception("Please enter all the details");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        CustomerModel registeredUser = customerRepository.insert(customer);
        return registeredUser;
    }

    public Optional<CustomerModel> getACustomer(String id) throws Exception {
        Optional<CustomerModel> customer = customerRepository.findById(id);

        if(!customer.isPresent()){
            throw new Exception("There is no user with the id: "+id);
        }

        return customer;
    }

    public List<CustomerModel> getCustomers()
    {
        return customerRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        CustomerModel foundUser = customerRepository.findByEmail(email);

        String userN = foundUser.getUserName();
        String emailId = foundUser.getEmail();
        String password = foundUser.getPassword();
        return new User(emailId,password, new ArrayList<>());
    }
}
