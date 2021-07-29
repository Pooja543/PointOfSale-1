package com.newsaleapi.vo;

import lombok.Data;

@Data
public class TaxVo {
	
	private long id;
	private String taxLabel;
	private float sgst;
	private float cgst;
	private float igst;
	private float cess;

}
