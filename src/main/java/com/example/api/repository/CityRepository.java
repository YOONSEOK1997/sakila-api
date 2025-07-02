package com.example.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.CityEntity;
import com.example.api.entity.CityMapping;
import com.example.api.entity.CountryEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
	Page<CityMapping> findAllBy(Pageable pageable);

	int countByCountryEntity(CountryEntity orElse);
	
}
