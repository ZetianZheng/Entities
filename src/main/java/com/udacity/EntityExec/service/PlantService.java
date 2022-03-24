package com.udacity.EntityExec.service;

import com.udacity.EntityExec.data.Plant;
import com.udacity.EntityExec.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;
    public Plant getPlantByName(String name){
        return new Plant();
    }

    public Long savePlant(Plant plant){
        return plantRepository.save(plant).getId();
    }

    public boolean isDelivered(Long id){
        return plantRepository.deliveryCompleted(id);
//        return plantRepository.existsPlantByIdAndDeliveryCompleted(id, true);
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price){
        return plantRepository.findAllByPriceLessThan(price);
    }
}