package com.newsaleapi.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.newsaleapi.Entity.BarcodeEntity;
import com.newsaleapi.Entity.DeliverySlipEntity;
import com.newsaleapi.vo.BarcodeVo;
import com.newsaleapi.vo.DeliverySlipVo;

@Component
public class NewSaleMapper {

	public BarcodeEntity convertBarcodeVoToEntity(BarcodeVo vo) {

		BarcodeEntity entity = new BarcodeEntity();

		BeanUtils.copyProperties(vo, entity);

		/*
		 * entity.setBarcode(vo.getBarcode()); entity.setItemDesc(vo.getItemDesc());
		 * entity.setMrp(vo.getMrp()); entity.setNetAmount(vo.getNetAmount());
		 * entity.setPromoDisc(vo.getPromoDisc()); entity.setQty(vo.getQty());
		 * entity.setSalesMan(vo.getSalesMan());
		 */
		entity.setCreatedDate(LocalDateTime.now());

		return entity;
	}

	public BarcodeVo convertBarcodeEntityToVo(BarcodeEntity entity) {

		BarcodeVo vo = new BarcodeVo();

		BeanUtils.copyProperties(entity, vo);

		/*
		 * vo.setBarcode(entity.getBarcode()); vo.setItemDesc(entity.getItemDesc());
		 * vo.setMrp(entity.getMrp()); vo.setNetAmount(entity.getNetAmount());
		 * vo.setPromoDisc(entity.getPromoDisc()); vo.setQty(entity.getQty());
		 * vo.setSalesMan(entity.getSalesMan());
		 */

		return vo;
	}

	public DeliverySlipEntity convertDSVoToEntity(DeliverySlipVo vo) {
		
		
		
		return null;
	}

	/*
	 * public DeliverySlipEntity convertDsVoToEntity(DeliverySlipVo vo) {
	 * 
	 * DeliverySlipEntity entity = new DeliverySlipEntity();
	 * 
	 * // List<String> barcodes= Arrays.asList("BAR123","BAR1234");
	 * 
	 * entity.setDsId(10l); entity.setQty(vo.getQty());
	 * entity.setSalesMan(vo.getSalesMan()); entity.setBarcode(vo.getBarcodes()); //
	 * entity.setBarcodes(barcodes);
	 * 
	 * return entity; }
	 */

	/*
	 * public DeliverySlipEntity convertDsVoToEntity(DeliverySlipVo vo) {
	 * 
	 * DeliverySlipEntity entity = new DeliverySlipEntity();
	 * 
	 * entity.setQty(vo.getQty()); entity.setSalesMan(vo.getSalesMan());
	 * entity.setType(vo.getType()); entity.setDsId(10l); List<BarcodeEntity>
	 * bcEntity = new ArrayList<>();
	 * 
	 * for (BarcodeVo bc : vo.getBarcode()) {
	 * 
	 * BarcodeEntity ds = new BarcodeEntity(); ds.setBarcode(bc.getBarcode());
	 * ds.setBarcodIid(20l); ds.setItemDesc(bc.getItemDesc());
	 * ds.setMrp(bc.getMrp()); ds.setNetAmount(bc.getNetAmount());
	 * ds.setPromoDisc(bc.getPromoDisc()); ds.setQty(bc.getQty());
	 * ds.setSalesMan(bc.getSalesMan());
	 * 
	 * bcEntity.add(ds); } //entity.setBarcodes(bcEntity);
	 * 
	 * return entity; }
	 */
}
