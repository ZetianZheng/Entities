package com.udacity.EntityExec.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.EntityExec.DTO.PlantDTO;
import com.udacity.EntityExec.DTO.Views;
import com.udacity.EntityExec.data.Plant;
import com.udacity.EntityExec.service.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @PostMapping
    public Long savePlant(@RequestBody Plant plant) {
        return plantService.savePlant(plant);
    }

    public PlantDTO getPlantDTO(String name){
        Plant plant = plantService.getPlantByName(name);

        return convertPlantToDTO(plant);
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }

    /**
     * copyProperties(Object source, Object target)
     * Copy the property values of the given source bean into the target bean.
     * bean for all cases where the property names are the same.
     * @param plant
     * @return
     */
    private PlantDTO convertPlantToDTO(Plant plant) {
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }

    @GetMapping("/delivered/{id}")
    public Boolean delivered(@PathVariable Long id) {
        return plantService.isDelivered(id);
    }

    @GetMapping("/under-price/{price}")
    @JsonView(Views.Public.class)
    public List<Plant> plantsCheaperThan(@PathVariable BigDecimal price) {
        return plantService.findPlantsBelowPrice(price);
    }
}