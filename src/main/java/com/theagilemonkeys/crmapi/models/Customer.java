package com.theagilemonkeys.crmapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
public class Customer {
    @Id
    private String id;
    
    private String name;
    private String surname;
    private String photoSrc;
    
    @DBRef
    private User createdBy;
    
    @DBRef
    private User updatedBy;
}
