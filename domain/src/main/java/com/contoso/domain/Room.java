package com.contoso.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "rooms")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
	@SequenceGenerator(name="room_generator", sequenceName = "room_seq", initialValue = 1)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "num_of_guests")
	private Integer numberOfGuests;
	
	@NotNull
	@Column(name = "current_price")
	private Long currentPrice;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotel;
	
	@JsonIgnore
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<Order> order;
}
