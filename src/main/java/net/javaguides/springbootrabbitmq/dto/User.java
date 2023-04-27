package net.javaguides.springbootrabbitmq.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
public class User {

    private int id;
    private String firstName;
    private String lastName;
}
