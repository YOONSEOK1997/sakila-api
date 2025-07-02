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

import com.example.api.dto.AddressDto;
import com.example.api.entity.AddressEntity;
import com.example.api.service.AddressService;

@RestController
@CrossOrigin
public class AddressController {
	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	// 삭제
	@DeleteMapping("/address/{addressId}")
	public ResponseEntity<String> updateAddress(@PathVariable int addressId){
		addressService.delete(addressId);
		return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/address")
	public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto){
		addressService.update(addressDto);
		return new ResponseEntity<String>("수정 성공", HttpStatus.OK);
	}
	
	// 한행 조회
	@GetMapping("/addressOne/{addressId}")
	public ResponseEntity<AddressEntity> addressOne(@PathVariable int addressId){
		return new ResponseEntity<AddressEntity>(addressService.findById(addressId), HttpStatus.OK);
	}
	
	// 전체 조회
	@GetMapping("/addressList/{currentPage}")
	public ResponseEntity<Page<AddressEntity>> address(@PathVariable int currentPage){
		return new ResponseEntity<Page<AddressEntity>>(addressService.findAll(currentPage), HttpStatus.OK);
	}
	
	// 삽입
	@PostMapping("/address")
	public ResponseEntity<String> address(@RequestBody AddressDto addressDto){
		addressService.save(addressDto);
		return new ResponseEntity<String>("삽입 성공", HttpStatus.OK);
	}
}
