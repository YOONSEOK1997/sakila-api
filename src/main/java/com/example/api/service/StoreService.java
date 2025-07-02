package com.example.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.dto.StoreDto;
import com.example.api.entity.StoreEntity;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.StoreRepository;

@Service
@Transactional
public class StoreService {
	private StoreRepository storeRepository;
	private AddressRepository addressRepository;
	
	public StoreService(StoreRepository storeRepository,AddressRepository addressRepository) {
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
	}
	
	// 전체 조회
	public Page<StoreEntity> findAll(int currentPage){
		final int pageSize = 10;
		int pageNum = (currentPage - 1);
		Sort sort = Sort.by("storeId").ascending();
		PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);
		return storeRepository.findAll(pageable);
	}
	
	// 한행 조회
	public StoreEntity findById(int storeId) {
		return storeRepository.findById(storeId).orElse(null);
	}
	
	// 삽입
	public void save(StoreDto storeDto) {
		StoreEntity saveStoreEntity = new StoreEntity();
		saveStoreEntity.setManagerStaffId(storeDto.getManagerStaffId());
		saveStoreEntity.setAddressEntity(addressRepository.findById(storeDto.getAddressId()).orElse(null));
		storeRepository.save(saveStoreEntity);
	}
	
	// 수정
	public void update(StoreDto storeDto) {
		StoreEntity saveStoreEntity = storeRepository.findById(storeDto.getStoreId()).orElse(null);
		saveStoreEntity.setManagerStaffId(storeDto.getManagerStaffId());
		saveStoreEntity.setAddressEntity(addressRepository.findById(storeDto.getAddressId()).orElse(null));
	}
	
	// 삭제
	public void delete(int storeId) {
		storeRepository.deleteById(storeId);
	}
}
