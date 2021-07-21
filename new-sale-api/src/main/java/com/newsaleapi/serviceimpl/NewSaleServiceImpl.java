package com.newsaleapi.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.newsaleapi.Entity.BarcodeEntity;
import com.newsaleapi.Entity.DeliverySlipEntity;
import com.newsaleapi.common.DSStatus;
import com.newsaleapi.mapper.NewSaleMapper;
import com.newsaleapi.repository.BarcodeRepository;
import com.newsaleapi.repository.DeliverySlipRepository;
import com.newsaleapi.service.NewSaleService;
import com.newsaleapi.vo.BarcodeVo;
import com.newsaleapi.vo.CustomerDetails;
import com.newsaleapi.vo.DeliverySlipVo;

@Service
@Configuration
public class NewSaleServiceImpl implements NewSaleService {

	@Autowired
	private RestTemplate template;

	@Autowired
	private NewSaleMapper newSaleMapper;

	@Autowired
	private BarcodeRepository barcodeRepository;

	@Autowired
	private DeliverySlipRepository dsRepo;

	@Value("${savecustomer.url}")
	private String url;

	@Override
	public ResponseEntity<?> saveNewSaleRequest(CustomerDetails vo) {

		Object result = template.postForObject(url, vo, String.class);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> saveBarcode(BarcodeVo vo) {

		BarcodeEntity barcodeDetails = barcodeRepository.findByBarcode(vo.getBarcode());
		if (barcodeDetails == null) {

			BarcodeEntity barcode = newSaleMapper.convertBarcodeVoToEntity(vo);
			barcodeRepository.save(barcode);

			return new ResponseEntity<>("Barcode details saved successfully..", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Barcode with " + vo.getBarcode() + " is already exists",
					HttpStatus.BAD_GATEWAY);
		}
	}

	@Override
	public ResponseEntity<?> getBarcodeDetails(String barCode) {

		BarcodeEntity barcodeDetails = barcodeRepository.findByBarcode(barCode);

		if (barcodeDetails == null) {
			return new ResponseEntity<>("Barcode with number " + barCode + " is not exists", HttpStatus.BAD_GATEWAY);
		} else {
			BarcodeVo vo = newSaleMapper.convertBarcodeEntityToVo(barcodeDetails);
			return new ResponseEntity<>(vo, HttpStatus.OK);
		}
	}

	@Override
	public String saveDeliverySlip(DeliverySlipVo vo) {

		DeliverySlipEntity entity = new DeliverySlipEntity();
		List<BarcodeVo> barVo = vo.getBarcode();

		entity.setMrp(barVo.stream().mapToLong(i -> i.getMrp()).sum());
		entity.setPromoDisc(barVo.stream().mapToLong(i -> i.getPromoDisc()).sum());
		entity.setNetAmount(barVo.stream().mapToLong(i -> i.getNetAmount()).sum());
		entity.setSalesMan(vo.getSalesMan());
		entity.setQty(vo.getQty());
		entity.setType(vo.getType());
		entity.setCreatedDate(LocalDate.now());
		entity.setLastModified(LocalDateTime.now());
		entity.setStatus("Pending");

		DeliverySlipEntity savedEntity = dsRepo.save(entity);
		List<String> barcodeList = barVo.stream().map(x -> x.getBarcode()).collect(Collectors.toList());

		List<BarcodeEntity> barcodeDetails = barcodeRepository.findByBarcodeIn(barcodeList);

		barcodeDetails.stream().forEach(a -> {

			a.setDeliverySlip(savedEntity);
			a.setLastModified(LocalDateTime.now());

			barcodeRepository.save(a);
		});

		return "Successfully created Delivery slip ";

	}

}
