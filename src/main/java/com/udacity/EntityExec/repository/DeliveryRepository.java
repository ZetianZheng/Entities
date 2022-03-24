package com.udacity.EntityExec.repository;

import com.udacity.EntityExec.DTO.RecipientAndPrice;
import com.udacity.EntityExec.data.Delivery;
import com.udacity.EntityExec.data.Plant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

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

    public List<Delivery> findAllDeliveries(String name) {
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findAllByName", Delivery.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
        Root<Plant> root = query.from(Plant.class);
        // TODO: how to projection with CriteriaBuilder?
        query.select(
                cb.construct(
                        RecipientAndPrice.class,
                        root.get("delivery").get("name"),
                        cb.sum(root.get("price"))))
                .where(cb.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(query).getSingleResult();
    }
}
