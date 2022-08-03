package com.example.digitalvideostoreapi.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository< CustomerModel, String>
{
    //this tells mongodb that there is need to be an implementation for this
    CustomerModel findByEmail(String emailId);
}
