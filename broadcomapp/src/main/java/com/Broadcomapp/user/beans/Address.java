package com.Broadcomapp.user.beans;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private User user;

    private String address;
    private String location;
    private String city;
    private String district;
    private String state;
    private String country;

    public Long getId() {
        return id;
    }

    public Address setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Address setUser(User user) {
        this.user = user;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Address setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Address setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Address setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(getId(), address1.getId()) && Objects.equals(getUser(), address1.getUser()) && Objects.equals(getAddress(), address1.getAddress()) && Objects.equals(getLocation(), address1.getLocation()) && Objects.equals(getCity(), address1.getCity()) && Objects.equals(getDistrict(), address1.getDistrict()) && Objects.equals(getState(), address1.getState()) && Objects.equals(getCountry(), address1.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getAddress(), getLocation(), getCity(), getDistrict(), getState(), getCountry());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", user=" + user +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
