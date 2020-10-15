package com.zelish.food.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zelish.food.entity.FoodTruckInfoEntity;
import com.zelish.food.repository.FoodTruckRepository;

@Component
public class FoodTruckInfoWriter implements ItemWriter<FoodTruckInfoEntity> {
	@Autowired
	private FoodTruckRepository repo;

	@Override
	public void write(List<? extends FoodTruckInfoEntity> items) throws Exception {
		repo.saveAll(items);
	}
}
