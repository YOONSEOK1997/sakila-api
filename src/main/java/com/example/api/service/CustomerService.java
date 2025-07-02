package com.example.api.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.dto.CustomerDto;
import com.example.api.entity.CustomerEntity;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.StoreRepository;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository;
	private StoreRepository storeRepository;
	private AddressRepository addressRepository;
	
	public CustomerService(CustomerRepository customerRepository, StoreRepository storeRepository,AddressRepository addressRepository){
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
	}
	
	// 전체 조회
	public Page<CustomerEntity> findAll(int currentPage){
		final int pageSize = 10;
		int pageNum = (currentPage - 1);
		Sort sort = Sort.by("customerId").ascending();
		PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);
		return customerRepository.findAll(pageable);
	}
	
	// 한행 조회
	public CustomerEntity findById(int customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}
	
	// 삽입
	public void save(CustomerDto customerDto) {
		CustomerEntity saveCustomerEntity = new CustomerEntity();
		saveCustomerEntity.setStoreEntity(storeRepository.findById(customerDto.getStoreId()).orElse(null));
		saveCustomerEntity.setFirstName(customerDto.getFirstName());
		saveCustomerEntity.setLastName(customerDto.getLastName());
		saveCustomerEntity.setEmail(customerDto.getEmail());
		saveCustomerEntity.setAddressEntity(addressRepository.findById(customerDto.getAddressId()).orElse(null));
		
		customerRepository.save(saveCustomerEntity);
	}
	
	// 수정
	public void update(CustomerDto customerDto) {
		CustomerEntity updateCustomerEntity = customerRepository.findById(customerDto.getCustomerId()).orElse(null);
		
		updateCustomerEntity.setStoreEntity(storeRepository.findById(customerDto.getStoreId()).orElse(null));
		updateCustomerEntity.setFirstName(customerDto.getFirstName());
		updateCustomerEntity.setLastName(customerDto.getLastName());
		updateCustomerEntity.setEmail(customerDto.getEmail());
		updateCustomerEntity.setAddressEntity(addressRepository.findById(customerDto.getAddressId()).orElse(null));
		
	}
	
	// 삭제
	public void delete(int customerId) {
		customerRepository.deleteById(customerId);
	}
}
