package com.Broadcomapp.user.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private BroadUser user;

    private String address;
    private String location;
    private String city;
    private String district;
    private String state;
    private String country;

}
