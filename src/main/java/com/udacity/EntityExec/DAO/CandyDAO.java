package com.udacity.EntityExec.DAO;

import com.udacity.EntityExec.data.Candy;

import java.util.List;

public interface CandyDAO {
    List<Candy> getAllCandy();
    void addToDeliveryById(Long candy_id, Long delivery_id);
    List<Candy> findByDelivery(Long delivery_id);
}
