package com.example.api.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "city")
@Data
public class CityEntity {	// city - 일대다 - country
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private int cityId;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "last_update", insertable = false, updatable = false)
	private Timestamp lastUpdate;
	
	// 다(city)대일(country)
	@ManyToOne 
	@JoinColumn(name = "country_id") // 단방향(city에서 country) 
	private CountryEntity countryEntity;
}