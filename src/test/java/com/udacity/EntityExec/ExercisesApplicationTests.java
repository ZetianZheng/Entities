package com.udacity.EntityExec;

import com.udacity.EntityExec.data.Delivery;
import com.udacity.EntityExec.data.Plant;
import com.udacity.EntityExec.repository.PlantRepository;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.jupiter.api.Assertions;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class ExercisesApplicationTests {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PlantRepository plantRepository;

    @Test
    public void testPriceLessThan() {
        // generate two new plants
        Plant p = testEntityManager.persist(new Plant("Foo leaf", BigDecimal.valueOf(4.88)));
        testEntityManager.persist(new Plant("Bar weed",  BigDecimal.valueOf(5.88)));

        List<Plant> cheapPlants = plantRepository.findAllByPriceLessThan(BigDecimal.valueOf(5));
        Assertions.assertEquals(1, cheapPlants.size(), "size");
        Assertions.assertEquals(p.getId(), cheapPlants.get(0).getId(), "id");
    }

    @Test
    public void testDeliveryCompleted() {
        Plant test_plant = testEntityManager.persist(new Plant("Foo leaf", BigDecimal.valueOf(4.88)));
        Delivery test_delivery = testEntityManager.persist(new Delivery("John", "101 thr ln", LocalDateTime.now()));

        test_plant.setDelivery(test_delivery);
        test_delivery.setPlants(Lists.newArrayList(test_plant));

        Assertions.assertFalse(plantRepository.deliveryCompleted(test_plant.getId()));
        // TODO 这个值是如何在数据库更新的？（entity manager）
        // would you explain the flow how this data been update from object to sql?
        test_delivery.setCompleted(true);
        Assertions.assertTrue(plantRepository.deliveryCompleted(test_plant.getId()));
    }
}
