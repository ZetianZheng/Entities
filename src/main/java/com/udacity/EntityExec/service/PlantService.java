package com.udacity.EntityExec.service;

import com.udacity.EntityExec.data.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name){
        return new Plant();
    }
}