package com.newsaleapi.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
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
import com.newsaleapi.Entity.NewSaleEntity;
import com.newsaleapi.common.DSStatus;
import com.newsaleapi.mapper.DeliverySlipMapper;
import com.newsaleapi.mapper.NewSaleMapper;
import com.newsaleapi.repository.BarcodeRepository;
import com.newsaleapi.repository.DeliverySlipRepository;
import com.newsaleapi.repository.NewSaleRepository;
import com.newsaleapi.service.CustomerService;
import com.newsaleapi.service.NewSaleService;
import com.newsaleapi.vo.BarcodeVo;
import com.newsaleapi.vo.DeliverySlipVo;
import com.newsaleapi.vo.MessageVo;
import com.newsaleapi.vo.NewSaleVo;

/**
 * Service class contains all bussiness logics related to new sale , create
 * barcode , getting barcode details and create delivery slip
 * 
 * @author Manikanta Guptha
 *
 */
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

	@Autowired
	private CustomerService customerService;

	@Autowired
	private NewSaleRepository newSaleRepository;

	@Autowired
	private DeliverySlipMapper dsMapper;

	@Value("${savecustomer.url}")
	private String url;

	@Override
	public ResponseEntity<?> saveNewSaleRequest(NewSaleVo vo) {

		Random ran = new Random();

		NewSaleEntity entity = new NewSaleEntity();

		if (vo.getCustomerDetails() != null) {

			try {
				customerService.saveCustomerDetails(vo.getCustomerDetails());
			} catch (Exception e) {

				e.printStackTrace();
			}

			List<DeliverySlipVo> dlSlips = vo.getDlSlip();

			entity.setNatureOfSale(vo.getNatureOfSale());
			entity.setPayType(vo.getPayType());
			entity.setGrossAmount(dlSlips.stream().mapToLong(i -> i.getMrp()).sum());
			entity.setTotalPromoDisc(dlSlips.stream().mapToLong(i -> i.getPromoDisc()).sum());
			entity.setTotalManualDisc(vo.getTotalManualDisc());
			entity.setCreatedDate(LocalDate.now());

			Long net = dlSlips.stream().mapToLong(i -> i.getNetAmount()).sum() - vo.getTotalManualDisc();

			entity.setNetPayableAmount(net);

			entity.setBillNumber(
					"KLM/" + LocalDate.now().getYear() + LocalDate.now().getDayOfMonth() + "/" + ran.nextInt());

			List<String> dlsList = dlSlips.stream().map(x -> x.getDsNumber()).collect(Collectors.toList());

			List<DeliverySlipEntity> dsList = dsRepo.findByDsNumberIn(dlsList);

			if (dsList.size() == vo.getDlSlip().size()) {

				NewSaleEntity saveEntity = newSaleRepository.save(entity);

				dsList.stream().forEach(a -> {

					a.setNewsale(saveEntity);
					a.setLastModified(LocalDateTime.now());

					dsRepo.save(a);
				});

			} else {
				return new ResponseEntity<>("Please enter Valid delivery slips..", HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<>("Bill generated with number :" + entity.getBillNumber(), HttpStatus.OK);

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

	// Method for saving delivery slip
	@Override
	public ResponseEntity<?> saveDeliverySlip(DeliverySlipVo vo) {
		try {

			DeliverySlipEntity entity = dsMapper.convertDsVoToEntity(vo);

			DeliverySlipEntity savedEntity = dsRepo.save(entity);

			List<BarcodeVo> barVo = vo.getBarcode();
			List<String> barcodeList = barVo.stream().map(x -> x.getBarcode()).collect(Collectors.toList());

			List<BarcodeEntity> barcodeDetails = barcodeRepository.findByBarcodeIn(barcodeList);

			barcodeDetails.stream().forEach(a -> {

				a.setDeliverySlip(savedEntity);
				a.setLastModified(LocalDateTime.now());

				barcodeRepository.save(a);
			});
			MessageVo message = new MessageVo();
			message.setMessage("Successfully created deliverySlip with DS Number " + entity.getDsNumber());
			message.setNumber(entity.getDsNumber());

			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("error occurs while saving Delivery slip", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getDeliverySlipDetails(String dsNumber) {
		try {
			DeliverySlipEntity dsEntity = dsRepo.findByDsNumber(dsNumber);

			if (dsEntity != null) {
				if (!dsEntity.getBarcodes().isEmpty()) {
					DeliverySlipVo vo = dsMapper.convertDsEntityToVo(dsEntity);

					return new ResponseEntity<>(vo, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Barcode details not exists with given DS Number",
							HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<>("No record with DsNumber :" + dsNumber, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("error occurs while saving Delivery slip", HttpStatus.BAD_REQUEST);
		}

	}

}
