package com.example.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.AddressEntity;
import com.example.api.entity.AddressMapping;
import com.example.api.entity.CityEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
	Page<AddressMapping> findAllBy(Pageable pageable); 
	Long countByCityEntity(CityEntity cityEntity);
	// select count(*) from address city_id cityEntity.getCityId()
}
