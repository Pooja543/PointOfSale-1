package com.newsaleapi.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.newsaleapi.common.PaymentType;
import com.newsaleapi.common.SaleNature;
import com.newsaleapi.vo.CustomerDetails;
import com.newsaleapi.vo.DeliverySlipVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "newsale")
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class NewSaleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// private CustomerDetails customerDetails;

	private SaleNature natureOfSale;

	private PaymentType payType;

	private String custmerId;

	private Long grossAmount;

	private Long totalPromoDisc;

	private Long totalManualDisc;

	private float roundOff;

	private Long netPayableAmount;
	
	private Long taxId;

	/*
	 * @OneToMany(mappedBy = "newsale") private List<DeliverySlipEntity> dlSlip
	 */;

}
