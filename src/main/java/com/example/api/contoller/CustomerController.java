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

import com.example.api.dto.CustomerDto;
import com.example.api.entity.CustomerEntity;
import com.example.api.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	// 전체 조회
	@GetMapping("/customerList/{currentPage}")
	public ResponseEntity<Page<CustomerEntity>> customer(@PathVariable int currentPage){
		return new ResponseEntity<Page<CustomerEntity>>(customerService.findAll(currentPage), HttpStatus.OK);
	}
	
	// 한행 조회
	@GetMapping("/customerOne/{customerId}")
	public ResponseEntity<CustomerEntity> customerOne(@PathVariable int customerId){
		return new ResponseEntity<CustomerEntity>(customerService.findById(customerId), HttpStatus.OK);
	}
	
	// 삽입
	@PostMapping("/customer")
	public ResponseEntity<String> insertCustomer(@RequestBody CustomerDto customerDto){
		customerService.save(customerDto);
		return new ResponseEntity<String>("입력 성공", HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/customerUpdate")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto){
		customerService.update(customerDto);
		return new ResponseEntity<String>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/customerDelete/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId){
		customerService.delete(customerId);
		return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
	}
}
