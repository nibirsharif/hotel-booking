package com.contoso.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
	@SequenceGenerator(name="order_generator", sequenceName = "order_seq", initialValue = 1)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "check_in_date")
	private Date checkInDate;
	
	@NotNull
	@Column(name = "check_out_date")
	private Date checkOutDate;
	
	@NotNull
	@Column(name = "total_price")
	private Long totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;
}
