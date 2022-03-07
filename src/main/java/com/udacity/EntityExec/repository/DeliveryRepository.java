package com.udacity.EntityExec.repository;

import com.udacity.EntityExec.data.Delivery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;

    /** persist, find, merge, and delete Delivery objects **/
    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }
    public Delivery find(Long id) {
        Delivery delivery = entityManager.find(Delivery.class, id);
        return delivery;
    }

    public Delivery merge(Delivery delivery) {
        Delivery delivery2 = entityManager.merge(delivery);
        return delivery2;
    }
    public void delete(Long id) {
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }
}
