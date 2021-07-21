package com.newsaleapi.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "delivery_slip")
public class DeliverySlipEntity  {

	
	@Id
	@GeneratedValue
	private Long dsId;

	private int qty;

	private String type;

	private Long mrp;

	private Long promoDisc;

	private Long netAmount;

	private String status;
	
	private Long salesMan;

	private LocalDate createdDate;

	private LocalDateTime lastModified;

	@OneToMany(targetEntity = BarcodeEntity.class, mappedBy = "deliverySlip", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<BarcodeEntity> barcodes;

	/*
	 * @OneToMany(mappedBy = "delivery_slip") private List<BarcodeEntity> barcodes;
	 */

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "newsale_id", nullable = false) private NewSaleEntity
	 * newsale;
	 */

}
