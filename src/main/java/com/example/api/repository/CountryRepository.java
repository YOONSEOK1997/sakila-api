package com.example.api.repository;


import com.example.api.entity.CountryEntity;
import com.example.api.entity.CountryMapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer>{
	 Page<CountryMapping> findAllBy(Pageable pageable);
}
