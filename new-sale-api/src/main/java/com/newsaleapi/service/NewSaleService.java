package com.newsaleapi.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.newsaleapi.vo.BarcodeVo;
import com.newsaleapi.vo.CustomerDetails;
import com.newsaleapi.vo.DeliverySlipVo;
import com.newsaleapi.vo.NewSaleVo;
import com.newsaleapi.vo.TaxVo;


@Component
public interface NewSaleService {

	ResponseEntity<?> saveNewSaleRequest(CustomerDetails vo);

	ResponseEntity<?> saveBarcode(BarcodeVo vo);

	ResponseEntity<?> getBarcodeDetails(String barCode);

	ResponseEntity<?> saveDeliverySlip(DeliverySlipVo vo);
	
	List<TaxVo> getNewSaleWithTax() throws JsonMappingException, JsonProcessingException;

}
