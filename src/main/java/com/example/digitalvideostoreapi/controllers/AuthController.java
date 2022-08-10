package com.example.digitalvideostoreapi.controllers;

import com.example.digitalvideostoreapi.CustomizedResponse;
import com.example.digitalvideostoreapi.models.CustomerModel;
import com.example.digitalvideostoreapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@CrossOrigin(origins = "https://digital-video-store-frontend.herokuapp.com")
@RestController
public class AuthController
{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value ="/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody CustomerModel user)
    {

        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            var response = new CustomizedResponse(" User logged in successfully ",
                    Collections.singletonList(customerService.getUserId(user.getEmail())));
            System.out.println(customerService.getUserId(user.getEmail()));
            return new ResponseEntity(response, HttpStatus.OK);

        }
        catch (BadCredentialsException ex)
        {
            var response = new CustomizedResponse(" Incorrect email/password", null);
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }
}
