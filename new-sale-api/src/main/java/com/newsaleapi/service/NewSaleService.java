package com.newsaleapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.newsaleapi.vo.BarcodeVo;
import com.newsaleapi.vo.CustomerDetails;
import com.newsaleapi.vo.DeliverySlipVo;
import com.newsaleapi.vo.NewSaleVo;


@Component
public interface NewSaleService {

	ResponseEntity<?> saveNewSaleRequest(NewSaleVo vo);

	ResponseEntity<?> saveBarcode(BarcodeVo vo);

	ResponseEntity<?> getBarcodeDetails(String barCode);

	String saveDeliverySlip(DeliverySlipVo vo);

}
