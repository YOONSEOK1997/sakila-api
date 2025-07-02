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

import com.example.api.dto.CountryDto;
import com.example.api.entity.CountryEntity;
import com.example.api.service.CountryService;

@RestController
@CrossOrigin
public class CountryController {
	private CountryService countryService;
	
	// 필드 주입 대신 생성자 주입을 사용
	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}
	
	// 삭제
	@DeleteMapping("/country/{countryId}")
	public ResponseEntity<String> deleteCountry(@PathVariable int countryId){
		boolean result = countryService.delete(countryId);	// 자식 테이블에 외래키로 참조하는 행이 있다면?
		if(result) {
			return new ResponseEntity<String>("삭제 성공", HttpStatus.OK); 			
		}
		
		return new ResponseEntity<String>("삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	// 수정
	@PatchMapping("/country")
	public ResponseEntity<String> updateCountry(@RequestBody CountryDto countryDto){
		countryService.update(countryDto);
		return new ResponseEntity<String>("수정 성공", HttpStatus.OK);
	}
	
	// 한행 조회
	@GetMapping("/countryOne/{countryId}")
	public ResponseEntity<CountryEntity> countryOne(@PathVariable int countryId){
		return new ResponseEntity<CountryEntity>(countryService.findById(countryId), HttpStatus.OK);
	}
	
	// 전체 조회
	@GetMapping("/countryList/{currentPage}")
	public ResponseEntity<Page<CountryEntity>> country(@PathVariable int currentPage){
		return new ResponseEntity<Page<CountryEntity>>(countryService.findAll(currentPage), HttpStatus.OK);
	}
	
	// 삽입
	@PostMapping("/country")
	public ResponseEntity<String> country(@RequestBody CountryDto countryDto){
		// // @RequestBody json 형태의 문자열 매개값을 CountryDto 타입으로 변환시킨다.
		countryService.save(countryDto);
		return new ResponseEntity<String>("입력 성공", HttpStatus.OK);
	}
}
