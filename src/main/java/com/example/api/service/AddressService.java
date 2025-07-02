package com.example.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.dto.AddressDto;
import com.example.api.entity.AddressEntity;
import com.example.api.entity.CityEntity;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CityRepository;

@Service
@Transactional
public class AddressService {
	
	private AddressRepository addressRepository;
	private CityRepository cityRepository;
	
	public AddressService(AddressRepository addressRepository, CityRepository cityRepository){
		this.addressRepository = addressRepository;
		this.cityRepository = cityRepository;
	}
	
	// 삭제
	public void delete(int addressId) {		
		addressRepository.deleteById(addressId);
	}
	
	// 수정
	public void update(AddressDto addressDto) {
		AddressEntity updateAddressEntity = addressRepository.findById(addressDto.getAddressId()).orElse(null);
		updateAddressEntity.setAddress(addressDto.getAddress());
		updateAddressEntity.setAddress2(addressDto.getAddress2());
		updateAddressEntity.setDistrict(addressDto.getDistrict());
		updateAddressEntity.setPhone(addressDto.getPhone());
		updateAddressEntity.setPostalCode(addressDto.getPostalCode());
		updateAddressEntity.setCityEntity(cityRepository.findById(addressDto.getCityId()).orElse(null));
	}
	
	// 입력
	public void save(AddressDto addressDto) {
		AddressEntity saveAddressEntity = new AddressEntity();
		saveAddressEntity.setAddress(addressDto.getAddress());
		saveAddressEntity.setAddress2(addressDto.getAddress2());
		saveAddressEntity.setDistrict(addressDto.getDistrict());
		saveAddressEntity.setPhone(addressDto.getPhone());
		saveAddressEntity.setPostalCode(addressDto.getPostalCode());
		
		CityEntity saveCityEntity = cityRepository.findById(addressDto.getCityId()).orElse(null);
		saveAddressEntity.setCityEntity(saveCityEntity);
		
		addressRepository.save(saveAddressEntity);
	}
	
	// 전체 조회
	public Page<AddressEntity> findAll(int currentPage){
		final int pageSize = 10;
		int pageNum = (currentPage - 1);
		Sort sort = Sort.by("addressId").ascending();
		PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);
		return addressRepository.findAll(pageable);
	}
	
	// 한행 조회
	public AddressEntity findById(int addressId) {
		return addressRepository.findById(addressId).orElse(null);
	}
}
