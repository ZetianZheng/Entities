package com.udacity.EntityExec.repository;

import com.udacity.EntityExec.data.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {
    //you can reference associations and attributes by chaining: Delivery.Completed
    //you can use Operators like And/Or, Lessthan/greaterthan, null/notnull
    // TODO: exists? I didn't see any key words "Exists" in [Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation),
    //  how this work? can we try isNotNull?
    Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean completed);

    @Query("select p.delivery.completed from Plant p where p.id = :id")
    Boolean deliveryCompleted(Long id);

    //to return a wrapper class, you may need to construct it as a projection
    @Query("select new java.lang.Boolean(p.delivery.completed) from Plant p where p.id = :plantId")
    Boolean deliveryCompletedBoolean(Long plantId);

    // returns all plants cheaper than a specified price.
    // findByAgeLessThan --》
    //… where x.age < ?1
    List<Plant> findAllByPriceLessThan(BigDecimal price);

}
