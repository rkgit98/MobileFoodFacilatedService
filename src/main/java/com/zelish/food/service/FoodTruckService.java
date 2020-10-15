package com.zelish.food.service;

import com.zelish.food.dto.FoodTruckInfoDto;
import com.zelish.food.entity.FoodTruckInfoEntity;
import com.zelish.food.model.FoodTruckInfo;
import org.jvnet.hk2.annotations.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public interface FoodTruckService {
    /**
     * @param pageable
     * @return
     */
    Page<FoodTruckInfoEntity> readAllFoodTruckInfo(Pageable pageable);

    /**
     * @param foodTruckInfoDto
     * @return
     */
    String update(FoodTruckInfoDto foodTruckInfoDto, long locationId);

    /**
     * @param locationId
     * @return
     */
    String delete(long locationId);

    /**
     * @param foodTruckInfoDto
     * @return
     */
    Long createFoodTruckInfo(FoodTruckInfoDto foodTruckInfoDto);

    /**
     * @param searchText
     * @return
     */
    List<FoodTruckInfo> search(String searchText);

    /**
     * @return
     */
    public List<FoodTruckInfo> getExpiredRecords();
}
