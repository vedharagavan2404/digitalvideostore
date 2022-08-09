package com.example.digitalvideostoreapi.controllers;


import com.example.digitalvideostoreapi.CustomizedResponse;
import com.example.digitalvideostoreapi.models.CustomerModel;
import com.example.digitalvideostoreapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity getUsers()
    {

        var response = new CustomizedResponse( " A list of all customers ", customerService.getCustomers());

        return new ResponseEntity( response, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getAnUser(@PathVariable("id") String id){
        CustomizedResponse response = null;
        try {
            response = new CustomizedResponse(" User with id "+ id, Collections.singletonList(customerService.getACustomer(id)));
        } catch (Exception e) {
            response = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value ="/customers", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createCustomers(@RequestBody CustomerModel customer){
        CustomizedResponse response = null;
        try {
            response = new CustomizedResponse(" User created successfully ", Collections.singletonList(customerService.addCustomer(customer)));
        } catch (Exception e) {
            response = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
