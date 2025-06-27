package com.example.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.dto.CityDto;
import com.example.api.entity.CityEntity;
import com.example.api.entity.CountryEntity;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CityRepository;
import com.example.api.repository.CountryRepository;

@Service
@Transactional
public class CityService {
	private CityRepository cityRepository;
	private CountryRepository countryRepository;
	private AddressRepository addressRepository; 
	
	public CityService(CityRepository cityRepository, CountryRepository countryRepository, AddressRepository addressRepository) {
		this.cityRepository = cityRepository;
		this.countryRepository = countryRepository;
		this.addressRepository = addressRepository;
	}
	
	// 삭제
	public boolean delete(int cityId) {
		if(addressRepository.countByCityEntity(cityRepository.findById(cityId).orElse(null)) > 0) {
			System.out.println("자식 테이블에 외래키로 참조하는 행이 존재합니다.");
			return false;
		}
		
		cityRepository.deleteById(cityId);
		return true;
	}
	
	// 수정
	public void update(CityDto cityDto) {	
		CityEntity updateCityEntity = cityRepository.findById(cityDto.getCityId()).orElse(null);
		updateCityEntity.setCity(cityDto.getCity());
		
		updateCityEntity.setCountryEntity(countryRepository.findById(cityDto.getCountryId()).orElse(null));
	}
	
	// 입력
	public void save(CityDto cityDto) {
		// DTO -> Entity		
	    CityEntity saveCityEntity = new CityEntity();	    
	    saveCityEntity.setCity(cityDto.getCity());
	    
	    CountryEntity saveCountryEntity = countryRepository.findById(cityDto.getCountryId()).orElse(null);
	    saveCityEntity.setCountryEntity(saveCountryEntity);
	    
	    cityRepository.save(saveCityEntity);
	}
	
	// 전체 조회
	public List<CityEntity> findAll(){
		return cityRepository.findAll();
	}
}
