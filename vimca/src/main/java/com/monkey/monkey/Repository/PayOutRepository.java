package com.monkey.monkey.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monkey.monkey.BrokerModels.PayOutRequest;

public interface PayOutRepository extends JpaRepository<PayOutRequest, Long> {

}
