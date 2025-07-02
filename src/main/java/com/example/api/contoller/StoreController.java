package com.example.api.contoller;

import java.util.List;

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

import com.example.api.dto.StoreDto;
import com.example.api.entity.StoreEntity;
import com.example.api.service.StoreService;

@RestController
@CrossOrigin
public class StoreController {
	private StoreService storeService;
	
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	// 전체 조회
	@GetMapping("/storeList{currentPage}")
	public ResponseEntity<Page<StoreEntity>> store(@PathVariable int currentPage){
		return new ResponseEntity<Page<StoreEntity>>(storeService.findAll(currentPage), HttpStatus.OK);
	}
	
	// 한행 조회
	@GetMapping("/storeOne/{storeId}")
	public ResponseEntity<StoreEntity> storeOne(@PathVariable int storeId){
		return new ResponseEntity<StoreEntity>(storeService.findById(storeId), HttpStatus.OK);
	}
	
	// 삽입
	@PostMapping("/store")
	public ResponseEntity<String> insertStore(@RequestBody StoreDto storeDto){
		storeService.save(storeDto);
		return new ResponseEntity<String>("입력 성공", HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/storeUpdate")
	public ResponseEntity<String> updateStore(@RequestBody StoreDto storeDto){
		storeService.update(storeDto);
		return new ResponseEntity<String>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/storeDelete/{storeId}")
	public ResponseEntity<String> deleteStore(@PathVariable int storeId){
		storeService.delete(storeId);
		return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
	}
}
