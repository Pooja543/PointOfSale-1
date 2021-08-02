package com.newsaleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newsaleapi.Entity.DeliverySlipEntity;

@Repository
public interface DeliverySlipRepository extends JpaRepository<DeliverySlipEntity, Long> {

	List<DeliverySlipEntity> findByDsNumberIn(List<String> dlsList);

	DeliverySlipEntity findByDsNumber(String dsNumber);

	// List<DeliverySlipEntity> findByDsNumberIn(List<DeliverySlipVo> dlSlips);

}
