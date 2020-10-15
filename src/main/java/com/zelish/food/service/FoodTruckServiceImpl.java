package com.zelish.food.service;

import com.zelish.food.constant.APP_CONSTANT;
import com.zelish.food.dto.FoodTruckInfoDto;
import com.zelish.food.entity.FoodTruckInfoEntity;
import com.zelish.food.exception.NoFoodTruckInfoAvailableException;
import com.zelish.food.model.FoodTruckInfo;
import com.zelish.food.repository.FoodTruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zelish.food.constant.APP_CONSTANT.*;

@Service
public class FoodTruckServiceImpl implements FoodTruckService {
    private static final Logger logger = LoggerFactory.getLogger(FoodTruckServiceImpl.class);

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<FoodTruckInfoEntity> readAllFoodTruckInfo(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            pageable = PageRequest.of(0, APP_CONSTANT.Default_Page_Size);
        }
        return foodTruckRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public String update(final FoodTruckInfoDto foodTruckInfoDto, long locationId) {
        if (Objects.isNull(foodTruckInfoDto)) {
            logger.error("<< No data to update");
            return FAILED_TO_UPDATE;
        } else if (!foodTruckRepository.existsById(locationId)) {
            logger.warn(" record not exist .create record");
            return FAILED_TO_UPDATE;
        }

        FoodTruckInfoEntity foodTruckInfoEntity = new FoodTruckInfoEntity();
        BeanUtils.copyProperties(foodTruckInfoDto, foodTruckInfoEntity);
        FoodTruckInfoEntity savedEntity = foodTruckRepository.save(foodTruckInfoEntity);

        if (Objects.nonNull(savedEntity)) {
            return APP_CONSTANT.UPDATED_SUCCESSFULLY;
        } else {
            return FAILED_TO_UPDATE;
        }
    }

    @Override
    public String delete(final long locationId) {
        if (!foodTruckRepository.existsById(locationId)) {
            logger.error("<< data not present for the provided location id : {}", locationId);
            return FAILED_TO_DELETE;
        }
        foodTruckRepository.deleteById(locationId);
        return DELETED_SUCCESSFULLY;
    }

    @Override
    public List<FoodTruckInfo> search(final String searchText) {
        List<FoodTruckInfoEntity> entities = foodTruckRepository.searchByText(searchText);

        if (Objects.nonNull(entities) && entities.size() > 0) {
            List<FoodTruckInfo> foodTruckInfos = entities.stream().map(entity -> {
                FoodTruckInfo foodTruckInfo = new FoodTruckInfo();
                BeanUtils.copyProperties(entity, foodTruckInfo);
                return foodTruckInfo;
            }).collect(Collectors.toList());
            return foodTruckInfos;
        } else {
            throw new NoFoodTruckInfoAvailableException();
        }
    }

    @Override
    public List<FoodTruckInfo> getExpiredRecords() {
        List<FoodTruckInfoEntity> entities = foodTruckRepository.findByExpirationDateBefore();

        if (CollectionUtils.isEmpty(entities)) {
            throw new NoFoodTruckInfoAvailableException();
        }
        return entities.stream()
                .map(entity -> {
                    FoodTruckInfo foodTruckInfo = new FoodTruckInfo();
                    BeanUtils.copyProperties(entity, foodTruckInfo);
                    return foodTruckInfo;
                }).collect(Collectors.toList());
    }

    @Override
    public Long createFoodTruckInfo(final FoodTruckInfoDto foodTruckInfoDto) {
        FoodTruckInfoEntity entity = new FoodTruckInfoEntity();
        BeanUtils.copyProperties(foodTruckInfoDto, entity);
        return foodTruckRepository.save(entity).getLocationId();
    }
}
