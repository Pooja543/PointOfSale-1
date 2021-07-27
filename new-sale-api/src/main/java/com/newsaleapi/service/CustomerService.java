package com.newsaleapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.newsaleapi.vo.CustomerDetails;


@Component
public interface CustomerService {

	ResponseEntity<?> saveCustomerDetails(CustomerDetails details) throws Exception;

	ResponseEntity<?> getCustomerByMobileNumber(String mobileNumber);

}

