package com.Broadcomapp.warehouse.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String address;
	private Integer capacity;
	private Integer occupiedCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getOccupiedCount() {
		return occupiedCount;
	}

	public void setOccupiedCount(Integer occupiedCount) {
		this.occupiedCount = occupiedCount;
	}

	public Warehouse() {
		super();
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", name=" + name + ", address=" + address + ", capacity=" + capacity
				+ ", occupiedCount=" + occupiedCount + "]";
	}

}
