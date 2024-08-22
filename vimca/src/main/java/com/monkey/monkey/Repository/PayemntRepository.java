package com.monkey.monkey.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monkey.monkey.models.Payment;

public interface PayemntRepository extends JpaRepository<Payment, Long> {

	@Query("select d from Payment d where d.paymentId=:id AND d.deleted=:isDeleted")
	Payment getPaymentById(Long id, boolean isDeleted);

}
