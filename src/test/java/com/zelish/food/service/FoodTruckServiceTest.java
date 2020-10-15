package com.zelish.food.service;

import com.zelish.food.constant.APP_CONSTANT;
import com.zelish.food.dto.FoodTruckInfoDto;
import com.zelish.food.entity.FoodTruckInfoEntity;
import com.zelish.food.exception.NoFoodTruckInfoAvailableException;
import com.zelish.food.model.FoodTruckInfo;
import com.zelish.food.repository.FoodTruckRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FoodTruckServiceTest {
    @Autowired
    private FoodTruckService foodTruckService;

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    @BeforeEach
    public void initialize() {
        seedData();
    }

    @Test
    public void testCreateFoodTruckInfo() {
        FoodTruckInfoDto foodTruckInfo = new PodamFactoryImpl().manufacturePojoWithFullData(FoodTruckInfoDto.class);
        Long locationId = foodTruckService.createFoodTruckInfo(foodTruckInfo);
        assertThat(foodTruckRepository.findAll().size()).isEqualTo(5);
        assertThat(locationId).isEqualTo(foodTruckInfo.getLocationId());
    }

    @Test
    public void testReadAllFoodTruckInfo() {
        seedData();
        Page<FoodTruckInfoEntity> foodTruckInfoEntities = foodTruckService.readAllFoodTruckInfo(PageRequest.of(0, 10));
        assertThat(Objects.nonNull(foodTruckInfoEntities)).isTrue();
        List<FoodTruckInfoEntity> foodTruckInfoEntityList = foodTruckInfoEntities.getContent();
        assertThat(CollectionUtils.isEmpty(foodTruckInfoEntityList)).isFalse();
        assertThat(foodTruckInfoEntityList.size()).isEqualTo(10);
    }

    @Test
    public void testUpdate() {
        long locationId = 125L;
        FoodTruckInfoDto entity = new PodamFactoryImpl().manufacturePojoWithFullData(FoodTruckInfoDto.class);
        entity.setLocationId(locationId);
        foodTruckService.createFoodTruckInfo(entity);

        String status = foodTruckService.update(entity, locationId);
        assertEquals(APP_CONSTANT.UPDATED_SUCCESSFULLY, status);
    }

    @Test
    public void testDelete() {
        long locationId = 120L;
        FoodTruckInfoDto entity = new PodamFactoryImpl().manufacturePojoWithFullData(FoodTruckInfoDto.class);
        entity.setLocationId(locationId);
        foodTruckService.createFoodTruckInfo(entity);

        String status = foodTruckService.delete(locationId);
        assertEquals(APP_CONSTANT.DELETED_SUCCESSFULLY, status);
    }

    @Test
    public void testSearch() {
        String searchText = "something";
        FoodTruckInfoDto entity = new PodamFactoryImpl().manufacturePojoWithFullData(FoodTruckInfoDto.class);
        entity.setApplicant(searchText);
        foodTruckService.createFoodTruckInfo(entity);

        List<FoodTruckInfo> entities = foodTruckService.search(searchText);
        assertThat(entities).hasSizeGreaterThan(0);
    }

    @Test
    public void testSearch_with_nomatch() {
        String searchText = "something";
        FoodTruckInfoDto entity = new PodamFactoryImpl().manufacturePojoWithFullData(FoodTruckInfoDto.class);
        entity.setApplicant(searchText);
        foodTruckService.createFoodTruckInfo(entity);

        assertThrows(NoFoodTruckInfoAvailableException.class, () -> {
            foodTruckService.search("abc");
        });
    }

    private void seedData() {
        FoodTruckInfoEntity foodTruckInfo1 = new PodamFactoryImpl()
                .manufacturePojoWithFullData(FoodTruckInfoEntity.class);
        foodTruckInfo1.setLocationId(1l);
        FoodTruckInfoEntity foodTruckInfo2 = new PodamFactoryImpl()
                .manufacturePojoWithFullData(FoodTruckInfoEntity.class);
        foodTruckInfo2.setLocationId(2l);
        FoodTruckInfoEntity foodTruckInfo3 = new PodamFactoryImpl()
                .manufacturePojoWithFullData(FoodTruckInfoEntity.class);
        foodTruckInfo3.setLocationId(3l);
        FoodTruckInfoEntity foodTruckInfo4 = new PodamFactoryImpl()
                .manufacturePojoWithFullData(FoodTruckInfoEntity.class);
        foodTruckInfo4.setLocationId(4l);
        List<FoodTruckInfoEntity> foodTruckInfoList = new ArrayList<>();
        foodTruckInfoList.add(foodTruckInfo1);
        foodTruckInfoList.add(foodTruckInfo2);
        foodTruckInfoList.add(foodTruckInfo3);
        foodTruckInfoList.add(foodTruckInfo4);
        foodTruckRepository.saveAll(foodTruckInfoList);
    }

    @AfterEach
    public void tearDown() {
        foodTruckRepository.deleteAll();
    }

}
