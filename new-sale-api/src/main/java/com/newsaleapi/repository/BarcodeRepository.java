package com.newsaleapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.newsaleapi.Entity.BarcodeEntity;

@Repository
public interface BarcodeRepository extends JpaRepository<BarcodeEntity, Long> {

	BarcodeEntity findByBarcode(String barCode);

	/*
	 * @Query(value = "select * from barcode where barcode=?") BarcodeEntity
	 * findByBarcodeNative(String barCode);
	 */
}
