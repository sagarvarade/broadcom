package com.Broadcomapp.user.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="broad_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
