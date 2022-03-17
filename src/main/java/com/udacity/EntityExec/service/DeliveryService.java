package com.udacity.EntityExec.service;

import com.udacity.EntityExec.DTO.RecipientAndPrice;
import com.udacity.EntityExec.data.Delivery;
import com.udacity.EntityExec.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository.getBill(deliveryId);
    }
    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }
}