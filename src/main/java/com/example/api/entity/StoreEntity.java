package com.example.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "store")
@DynamicInsert
@Getter
@Setter
public class StoreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private int storeId;
	
	@Column(name = "manager_staff_id")
	private int managerStaffId;
	
	@Column(name = "last_update", insertable = false, updatable = false)
	private Timestamp lastUpdate;
	
	// 외래키 Store(다 or 일) : Address(일)
	@ManyToOne
	@JoinColumn(name = "address_id")
	private AddressEntity addressEntity;
}
