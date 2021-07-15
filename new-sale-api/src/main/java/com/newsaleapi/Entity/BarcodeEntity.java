package com.newsaleapi.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "barcodes")
public class BarcodeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "barcode_seq")
	/*
	 * @SequenceGenerator(initialValue = 1, name = "barcode_seq", sequenceName =
	 * "barcode_sequence")
	 * 
	 * @Column(name = "barcode_id")
	 */
	private Long barcodIid;

	private String barcode;

	private String itemDesc;

	private int qty;

	private Long mrp;

	private Long promoDisc;

	private Long netAmount;

	private Long salesMan;

	private LocalDateTime createdDate;

	/*
	 * @ManyToOne // @JoinColumn(name = "ds_id") private DeliverySlipEntity
	 * delivery_slip;
	 */

}
