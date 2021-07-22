package com.newsaleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newsaleapi.common.CommonRequestMappigs;
import com.newsaleapi.service.NewSaleService;
import com.newsaleapi.vo.BarcodeVo;
import com.newsaleapi.vo.CustomerDetails;
import com.newsaleapi.vo.DeliverySlipVo;

/**
 * Controller class for accepting all the requests which are related to
 * CustomerSaving API, NewSale API, Create delivery slip API
 * 
 * @author Manikanta Guptha
 *
 */

@RestController
@CrossOrigin
@RequestMapping(CommonRequestMappigs.NEW_SALE)
public class NewSaleController {

	@Autowired
	private NewSaleService newSaleService;

	// Method for saving new sale items...
	@PostMapping(CommonRequestMappigs.SALE)
	public ResponseEntity<?> saveNewSale(@RequestBody CustomerDetails vo) {

		ResponseEntity<?> message = newSaleService.saveNewSaleRequest(vo);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// Method for create new Barcode..
	@PostMapping(CommonRequestMappigs.CREATE_BARCODE)
	public ResponseEntity<?> saveBarcode(@RequestBody BarcodeVo vo) {

		ResponseEntity<?> result = newSaleService.saveBarcode(vo);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// Method for getting Barcode details from Barcode table using Barcode number
	@GetMapping(CommonRequestMappigs.GET_BARCODE_DETAILS)
	public ResponseEntity<?> getBarcodeDetails(@RequestParam String barCode) {

		ResponseEntity<?> barCodeDetails = newSaleService.getBarcodeDetails(barCode);

		return new ResponseEntity<>(barCodeDetails, HttpStatus.OK);

	}

	// Method for creating Delivery slip usinng List of Barcodes..
	@PostMapping(CommonRequestMappigs.CREATE_DS)
	public ResponseEntity<?> saveDeliverySlip(@RequestBody DeliverySlipVo vo) {

		String saveDs = newSaleService.saveDeliverySlip(vo);

		return new ResponseEntity<>(saveDs, HttpStatus.OK);

	}

}
