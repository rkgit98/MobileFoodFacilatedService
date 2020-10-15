package com.zelish.food.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zelish.food.dto.FoodTruckInfoDto;
import com.zelish.food.entity.FoodTruckInfoEntity;
import com.zelish.food.exception.NoFoodTruckInfoAvailableException;
import com.zelish.food.model.FoodTruckInfo;
import com.zelish.food.service.FoodTruckService;

@RestController
@RequestMapping("/foodTruck")
public class FoodTruckController {
	private static final Logger logger = LoggerFactory.getLogger(FoodTruckController.class);

	@Autowired
	private FoodTruckService foodTruckService;

	/**
	 * Rest Api for fetching all available food trcuk data based on page no
	 *
	 * @param pageable {page no and page size has to requested}
	 * @return all the records with page info
	 */
	@PostMapping(path = "/fetch", produces = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public Page<FoodTruckInfoEntity> fetchFoodTruckData(@RequestBody @PageableDefault Pageable pageable) {
		logger.info("** request received for reading records **");
		return foodTruckService.readAllFoodTruckInfo(pageable);
	}

	/**
	 * Rest Api for updating existing records based on the location id as id
	 *
	 * @param foodTruckInfo {@link FoodTruckInfo}}
	 * @param locationId    {identity}
	 * @return updatating status in plain format
	 */
	@PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.TEXT_PLAIN)
	public String updateFoodTruck(@RequestBody @NotNull final FoodTruckInfo foodTruckInfo,
			@PathVariable("id") long locationId) {
		logger.info("** request received for updating records **");
		FoodTruckInfoDto foodTruckInfoDto = new FoodTruckInfoDto();
		BeanUtils.copyProperties(foodTruckInfo, foodTruckInfoDto);
		return foodTruckService.update(foodTruckInfoDto, locationId);
	}

	/**
	 * Rest Api for deleting existing records based on the location id if location
	 * id is not available expect status as <code>Failed<code>
	 *
	 * @param locationId {identity}
	 * @return status of operation
	 */
	@DeleteMapping(value = "/delete/{locationId}", produces = MediaType.TEXT_PLAIN)
	public String deleteFoodTruck(@PathVariable("locationId") @NotBlank long locationId) {
		logger.info("* request received for deleting records *");
		return foodTruckService.delete(locationId);
	}

	/**
	 * Rest Api for creating new controller Accept {@link FoodTruckInfo} as part of
	 * body in json format
	 *
	 * @param foodTruckInfo Returns locationId for successfull creation
	 * @return
	 */
	@PostMapping(path = "/create")
	public Long createFoodTruck(@RequestBody @NotNull FoodTruckInfo foodTruckInfo) {
		logger.info("* request received for creating records *");
		FoodTruckInfoDto foodTruckInfoDto = new FoodTruckInfoDto();
		BeanUtils.copyProperties(foodTruckInfo, foodTruckInfoDto);
		return foodTruckService.createFoodTruckInfo(foodTruckInfoDto);
	}

	/**
	 * Rest Api for searching food truck record based on applicant,facility-type &
	 * status
	 *
	 * @return collection of records for matches Otherwise throw
	 *         {@link NoFoodTruckInfoAvailableException}
	 */
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON)
	public List<FoodTruckInfo> searchFoodTruckData(@RequestParam("searchText") String searchText) {
		logger.info("* request received for searching records *");
		return foodTruckService.search(searchText);
	}

	/**
	 * Rest Api for filtering records on expiration date
	 *
	 * @return
	 */
	@GetMapping(value = "/expired/records", produces = MediaType.APPLICATION_JSON)
	public List<FoodTruckInfo> getExpiredFoodTruck() {
		return foodTruckService.getExpiredRecords();
	}

}
