package com.Broadcomapp.user.beans;

import jakarta.persistence.*;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BroadUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	private String email;
	private String phoneNumber;
	private String fbId;
	private String instagraid;
	private String linkedinid;
	private String telegramid;
	private String whatsappid;
	private String gender;
	private Date dateOfBirth;
}
