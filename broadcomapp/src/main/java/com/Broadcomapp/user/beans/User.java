package com.Broadcomapp.user.beans;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;
	private String name;
	@OneToMany(mappedBy="user")
	private Set<Address> address;
	private String email;
	private String phoneNumber;
	private String fbId;
	private String instagraid;
	private String linkedinid;
	private String telegramid;
	private String whatsappid;
	private String gender;
	private Date dateOfBirth;

	public User() {

	}

	public User setUserid(Integer userid) {
		this.userid = userid;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}


	public Set<Address> getAddress() {
		return address;
	}

	public User setAddress(Set<Address> address) {
		this.address = address;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public User setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getFbId() {
		return fbId;
	}

	public User setFbId(String fbId) {
		this.fbId = fbId;
		return this;
	}

	public String getInstagraid() {
		return instagraid;
	}

	public User setInstagraid(String instagraid) {
		this.instagraid = instagraid;
		return this;
	}

	public String getLinkedinid() {
		return linkedinid;
	}

	public User setLinkedinid(String linkedinid) {
		this.linkedinid = linkedinid;
		return this;
	}

	public String getTelegramid() {
		return telegramid;
	}

	public User setTelegramid(String telegramid) {
		this.telegramid = telegramid;
		return this;
	}

	public String getWhatsappid() {
		return whatsappid;
	}

	public User setWhatsappid(String whatsappid) {
		this.whatsappid = whatsappid;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public User setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public User setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(userid, user.userid) && Objects.equals(getName(), user.getName()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && Objects.equals(getFbId(), user.getFbId()) && Objects.equals(getInstagraid(), user.getInstagraid()) && Objects.equals(getLinkedinid(), user.getLinkedinid()) && Objects.equals(getTelegramid(), user.getTelegramid()) && Objects.equals(getWhatsappid(), user.getWhatsappid()) && Objects.equals(getGender(), user.getGender()) && Objects.equals(getDateOfBirth(), user.getDateOfBirth());
	}

	@Override
	public int hashCode() {
		return Objects.hash(userid, getName(), getAddress(), getEmail(), getPhoneNumber(), getFbId(), getInstagraid(), getLinkedinid(), getTelegramid(), getWhatsappid(), getGender(), getDateOfBirth());
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + userid +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", fbId='" + fbId + '\'' +
				", instagraid='" + instagraid + '\'' +
				", linkedinid='" + linkedinid + '\'' +
				", telegramid='" + telegramid + '\'' +
				", whatsappid='" + whatsappid + '\'' +
				", gender='" + gender + '\'' +
				", dateOfBirth=" + dateOfBirth +
				'}';
	}
}
