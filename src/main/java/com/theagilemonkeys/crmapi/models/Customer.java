package com.theagilemonkeys.crmapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    @Id
    private String id;
    
    @NotEmpty(message = "Customer's name is required")
    private String name;
    
    @NotEmpty(message = "Customer's surname is required")
    private String surname;
    
    private String photoSrc;
    
    @DBRef
    private UserEntity createdBy;
    
    @DBRef
    private UserEntity updatedBy;
}
