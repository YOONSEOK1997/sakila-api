package com.example.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.StoreEntity;
import com.example.api.entity.StoreMapping;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{
	Page<StoreMapping> findAllBy(Pageable pageable);
}
