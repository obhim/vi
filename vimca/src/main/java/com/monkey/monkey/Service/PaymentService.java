package com.monkey.monkey.Service;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.monkey.Repository.PayemntRepository;
import com.monkey.monkey.models.Payment;
@Service
public class PaymentService {
    
    @Autowired
    private PayemntRepository paymentRepository;
    
    @Autowired
    private EntityManager entityManager;

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id, boolean isDeleted) {
        try {
            // Assuming there's a method in the repository to fetch by ID and deleted status
            Payment payment = paymentRepository.getPaymentById(id, isDeleted);
            return payment;
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<Payment> findAllPayments(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedPaymentFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Payment> payments = paymentRepository.findAll();
        session.disableFilter("deletedPaymentFilter");
        return payments;
    }

    public Payment deletePaymentById(Long id, boolean isDeleted) {
        try {
            Payment payment = paymentRepository.getPaymentById(id, isDeleted);
            paymentRepository.deleteById(id);
            return payment;
        } catch (Exception e) {
            return null;
        }
    }

    public void update(Payment payment) {
        paymentRepository.save(payment);
    }
}
