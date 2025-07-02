package com.example.api.contoller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.CityDto;
import com.example.api.entity.CityEntity;
import com.example.api.service.CityService;

@RestController
@CrossOrigin
public class CityController {
	private CityService cityService;
	
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}
	
	// 삭제
	@DeleteMapping("/city/{cityId}")
	public ResponseEntity<String> deleteCity(@PathVariable int cityId){
		
		boolean result = cityService.delete(cityId);
		if(result) {
			return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("입력 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 수정
	@PatchMapping("/city")
	public ResponseEntity<String> updateCity(@RequestBody CityDto cityDto){
		cityService.update(cityDto);
		return new ResponseEntity<String>("수정 성공", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	// 조회
	@GetMapping("/cityList/{currentPage}")
	public ResponseEntity<Page<CityEntity>> city(@PathVariable int currentPage){
		return new ResponseEntity<Page<CityEntity>>(cityService.findAll(currentPage), HttpStatus.OK);
	}
	
	// 한행 조회
	@GetMapping("/cityOne/{cityId}")
	public ResponseEntity<CityEntity> cityOne(@PathVariable int cityId){
		return new ResponseEntity<CityEntity>(cityService.findById(cityId), HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/city")
	public ResponseEntity<String> city(@RequestBody CityDto cityDto){
		cityService.save(cityDto);
		return new ResponseEntity<String>("입력 성공", HttpStatus.OK);
	}
}
