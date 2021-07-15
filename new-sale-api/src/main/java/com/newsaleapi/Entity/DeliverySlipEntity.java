package com.newsaleapi.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "delivery_slip")
public class DeliverySlipEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dsId;
	/*
	 * @SequenceGenerator(initialValue = 1, name = "ds_seq", sequenceName =
	 * "ds_sequence")
	 * 
	 * @Column(name = "ds_id")
	 */

	private int qty;

	private String type;

	private Long salesMan;

	private String barcode;

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
