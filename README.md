# MobileFoodFacilatedService
This is a project for tracking food truck info are in service
---

# Basic Description About Project
---
As part of this project, various rest api has been exposed in `distributed and interoperable manner`
so that any client can access above mentioned service.client could be web app,mobile app,third party app.
The main agenda here to perform **CRUD** operation on `FoodTruckInfo` records
Apart from this searching rest api to filter data and get expired food truck service provider

## Possible Operations Provided
1. Creating FoodTruckInfo Data
2. Updating existing FoodTruckInfo Data
3. Deleting FoodTruckInfo Data
4. Searching FoodTruckInfo Data based on applicant,facility-type,status
5. Tracking all the expired food truck services

# Steps to run this project
1. Run **MobileFoodFacilatedProjectApplication** class available in com.zelish.food
While running this spring boot mail class, One batch job will gets executed which in turn read the csv file
and convert that into entity object and persist in h2 inmemory db

2. All api the resource has been there in **FoodTruckController** in com.zelish.food.controller
Here all the rest api available for various operation.

## url for possible operation
---
1. [fetching all foodtruck records](http://localhost:8082/zelish/foodTruck/fetch) |*http://localhost:8082/zelish/foodTruck/fetch*
2. [updating foodtruck records](http://localhost:8082/zelish/foodTruck/update/locationId) |*http://localhost:8082/zelish/foodTruck/update/locationId*
3. [deleting foodtruck records](http://localhost:8082/zelish/foodTruck/delete/locationId) |*http://localhost:8082/zelish/foodTruck/delete/locationId*
4. [creating new foodtruck records](http://localhost:8082/zelish/foodTruck/create) |*http://localhost:8082/zelish/foodTruck/create*
5. [searching all foodtruck records](http://localhost:8082/zelish/foodTruck/search) |*http://localhost:8082/zelish/foodTruck/search*
6. [get expired foodtruck records](http://localhost:8082/zelish/foodTruck/expired/records) |*http://localhost:8082/zelish/foodTruck/expired/records*

## Database Access Url
---
as i have used in memory db the url is
[db url](http://localhost:8082/zelish/h2-console) *http://localhost:8082/zelish/h2-console*

## Rest Api docs url
---
as i have used swagger for creating rest api docs, url is
[docs url](http://localhost:8082/zelish/swagger-ui.html) *http://localhost:8082/zelish/swagger-ui.html*
