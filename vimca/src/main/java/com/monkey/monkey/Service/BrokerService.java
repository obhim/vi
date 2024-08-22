package com.monkey.monkey.Service;

import javax.persistence.EntityManager;


import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.monkey.BrokerModels.Broker;
import com.monkey.monkey.Repository.BrokerRepository;

@Service
public class BrokerService {
	
	    @Autowired
	    private EntityManager entityManager;

	    @Autowired
	    private BrokerRepository brokerRepository;

	    public Broker addBroker(Broker broker) {
	        Broker savedBroker = brokerRepository.save(broker);
	        return savedBroker;
	    }

	    public Broker getBrokerById(Long id, boolean isDeleted) {
	        try {
	            Broker brokerEntity = brokerRepository.getBrokerById(id, isDeleted);
	            return brokerEntity;
	        } catch (Exception e) {
	            return null;
	        }
	    }

	    public Broker getBrokerByMobileNumber(String phoneNumber, boolean isDeleted) {
	        try {
	            Broker brokerEntity = brokerRepository.getBrokerByMobileNumber(phoneNumber, isDeleted);
	            return brokerEntity;
	        } catch (Exception e) {
	            return null;
	        }
	    }

	    public Iterable<Broker> findAllBrokers(boolean isDeleted) {
	        Session session = entityManager.unwrap(Session.class);
	        Filter filter = session.enableFilter("deletedBrokerFilter");
	        filter.setParameter("isDeleted", isDeleted);
	        Iterable<Broker> brokerEntities = brokerRepository.findAll();
	        session.disableFilter("deletedBrokerFilter");
	        return brokerEntities;
	    }

	    public Broker deleteBrokerById(Long id) {
	        try {
	            Broker broker = brokerRepository.getBrokerById(id, false);
	            brokerRepository.deleteById(id);
	            return broker;
	        } catch (Exception e) {
	            return null;
	        }
	    }

	    public Broker getBrokerByEmailId(String email, boolean isDeleted) {
	        return brokerRepository.getBrokerByEmailId(email, isDeleted);
	    }
	

}
