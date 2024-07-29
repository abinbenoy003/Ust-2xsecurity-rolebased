package com.ust.Ust_Projects.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String psnumber;
    private String username;
    private String password;
    private String email;
    private String location;
    private boolean active;
    private String roles;
}
