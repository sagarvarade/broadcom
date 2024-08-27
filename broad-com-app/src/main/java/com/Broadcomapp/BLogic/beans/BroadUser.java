package com.Broadcomapp.BLogic.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "address")
public class BroadUser {
	@Id
	@SequenceGenerator(
			name="broad_user_sequence",
			sequenceName = "broad_user_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "broad_user_sequence"
	)
	private Long broadUserId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private String gender;
	private Date dateOfBirth;

	private String createdBy;
	private LocalDateTime createdDate;

	private String updatedBy;
	private LocalDateTime updatedDate;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name = "fk_add_id")
	private Address address;
}
