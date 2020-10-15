package com.zelish.food.batch;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.zelish.food.dto.FoodTruckInfoExcelData;
import com.zelish.food.entity.FoodTruckInfoEntity;

@Configuration
@EnableBatchProcessing
public class WriteCsvToDbBatch {
	@Autowired
	private StepBuilderFactory sf;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private FoodTruckInfoWriter writer;

	@Bean
	public Step step() {
		return sf.get("csv-converter").<FoodTruckInfoExcelData, FoodTruckInfoEntity>chunk(50).reader(reader())
				.processor(processor()).writer(writer).build();
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job-converter").incrementer(new RunIdIncrementer()).start(step()).build();
	}

	@Bean
	public ItemReader<FoodTruckInfoExcelData> reader() {
		FlatFileItemReader<FoodTruckInfoExcelData> itemReader = new FlatFileItemReader<FoodTruckInfoExcelData>();
		itemReader.setResource(new ClassPathResource("static\\datasets_41689_1462330_mobile-food-facility-permit.csv"));

		itemReader.setLineMapper(new DefaultLineMapper<FoodTruckInfoExcelData>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer(",") {
					{
						setNames(new String[] { "locationId", "applicant", "facilityType", "cnn", "locationDescription",
								"address", "blocklot", "block", "lot", "permit", "status", "foodItems", "x", "y",
								"latitude", "longitude", "schedule", "dayHours", "noiSent", "approved", "received",
								"priorPermit", "expirationDate", "location", "firePrevention", "policeDist",
								"supervisor", "zipCodes", "neighbourHoods" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<FoodTruckInfoExcelData>() {
					{
						setTargetType(FoodTruckInfoExcelData.class);
					}
				});
			}
		});

		return itemReader;
	}

	@Bean
	public ItemProcessor<FoodTruckInfoExcelData, FoodTruckInfoEntity> processor() {
		return new ItemProcessor<FoodTruckInfoExcelData, FoodTruckInfoEntity>() {

			@Override
			public FoodTruckInfoEntity process(FoodTruckInfoExcelData item) throws Exception {
				FoodTruckInfoEntity entity = new FoodTruckInfoEntity();
				BeanUtils.copyProperties(item, entity);
				entity.setLocationId(Long.parseLong(item.getLocationId()));
				entity.setCnn(Long.parseLong(item.getCnn()));
//				entity.setLatitude(Long.parseLong(item.getLatitude()));
//				entity.setLongitude(Long.parseLong(item.getLongitude()));

//				entity.setNoiSent(dateConverter(item.getNoiSent()));
//				entity.setApproved(dateConverter(item.getApproved()));
//				entity.setReceived(dateConverter(item.getReceived()));
//				entity.setExpirationDate(dateConverter(item.getExpirationDate()));
				return entity;
			}
		};
	}

	private Date dateConverter(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		java.util.Date utilDate = sdf.parse(date);
		Date sqlDate = (Date) utilDate;

		return sqlDate;
	}
}
