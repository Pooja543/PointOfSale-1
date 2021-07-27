package com.newsaleapi.common;

public enum PaymentType {

	Cash(1, "Cash"), Card(2, "Card"), RTSlip(3, "RTSlip"), LoyaltyPoints(4, "LoyaltyPoints"),
	OtherPayments(5, "OtherPayments"), UPI(6, "UPI");

	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	private String type;

	private PaymentType(int num, String type) {

		this.num = num;
		this.type = type;

	}

}
