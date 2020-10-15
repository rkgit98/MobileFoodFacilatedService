package com.zelish.food.repository;

import com.zelish.food.entity.FoodTruckInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruckInfoEntity, Long> {

    @Query("SELECT foodtruck FROM FoodTruckInfoEntity foodtruck WHERE " +
            "applicant like %:searchText% OR facilityType like %:searchText% OR status like %:searchText%")
    List<FoodTruckInfoEntity> searchByText(@Param("searchText") String searchText);

    @Query("SELECT foodtruck FROM FoodTruckInfoEntity foodtruck WHERE foodtruck.expirationDate < NOW()")
    List<FoodTruckInfoEntity> findByExpirationDateBefore();
}
