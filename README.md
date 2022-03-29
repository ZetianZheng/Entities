# Entities
Entities example with Hibernate

- [flower](src/main/java/com/udacity/EntityExec/data/Flower.java): 
    - @ID, @GeneratedValue
    - @Nationalized: 
- [delivery](src/main/java/com/udacity/EntityExec/data/Delivery.java)
    - @Type(type = "yes_no")
    - LocalDateTime
    - @Column(precision = 12, scale = 4)
## Hibernate Inheritance：

Create a Plant Entity. Create subclasses Shrub and Flower.


Move id, name, and price into the Plant class.


Add height and width to Shrub.


Use an inheritance strategy that stores all shared data in the ‘plant’ table and all unique data in other tables.


Add a List to Delivery to store flowers and shrubs.


Establish a bidirectional relationship between Delivery and store it in the ‘plant’ table in a column called ‘delivery_id’.


Add and update getter and setter methods.


[通过Hibernate实现JPA对象关系模型之继承映射策略 - 叶落](https://www.yelcat.cc/index.php/archives/1535/)
- [Plant](src/main/java/com/udacity/EntityExec/data/Plant.java)
  - @Inheritance(strategy = InheritanceType.JOINED)
  - @ManyToOne, @JoinColumn [@JoinColumn Annotation Explained | Baeldung](https://www.baeldung.com/jpa-join-column)
- [flower](src/main/java/com/udacity/EntityExec/data/Flower.java): 
    - extends Plant, same as Shrub
- [delivery](src/main/java/com/udacity/EntityExec/data/Delivery.java)
  - @OneToMany
  - Lazy loading: [Eager/Lazy Loading In Hibernate | Baeldung](https://www.baeldung.com/hibernate-lazy-eager-loading)

## DTO
[从实践中理解JsonView的作用和用法 - SegmentFault 思否](https://segmentfault.com/a/1190000023286635)  

We want to make a REST endpoint that allows users to retrieve the price for a plant with a specific name, but we don’t want to show them ids or any information about the Delivery that plant is scheduled for. For this assignment, we’ll solve the problem twice: once using a DTO and once using the @JSONView annotation.  

CreatePlantService.java and PlantController.java using the above code.


Create a class called PlantDTO that contains name and price variables.


Modify the PlantController class to convert the Plant Entity into a PlantDTO and return that from the getPlantDTO method.


Create a new class called Views. Create an interface in that class called Public.


Use the @JSONView annotation in Plant.java and PlantController.java so that getFilteredPlant only returns name and price.
- [PlantController](src/main/java/com/udacity/EntityExec/controller/PlantController.java)
  - convertPlantToDTO : copyProperties
  -  @JsonView(Views.Public.class) 
      - [从实践中理解JsonView的作用和用法 - SegmentFault 思否](https://segmentfault.com/a/1190000023286635)
- [PlantService](src/main/java/com/udacity/EntityExec/service/PlantService.java)
  - @Service
  ### DTO part:
- [Views](src/main/java/com/udacity/EntityExec/DTO/Views.java)
  - public interface Public { }
- [PlantDTO](src/main/java/com/udacity/EntityExec/DTO/PlantDTO.java)

## JPA Exercise 1
For this exercise, you should create a class that can add new Delivery objects to the database. It should support the following interface:
[DeliveryRepository](src/main/java/com/udacity/EntityExec/repository/DeliveryRepository.java)
```java
void persist(Delivery delivery);
Delivery find(Long id);
Delivery merge(Delivery delivery);
void delete(Long id);
```
Modify Delivery [delivery](src/main/java/com/udacity/EntityExec/data/Delivery.java) so that if it is removed, it will also remove any Plants associated with it at the same time.
Modify Plant [Plant](src/main/java/com/udacity/EntityExec/data/Plant.java) so that it will only query for Delivery objects when they are referenced, not every time the Plant is retrieved.

## JPA Exercise 2

Create the [DeliveryService](src/main/java/com/udacity/EntityExec/service/DeliveryService.java) and [DeliveryController](src/main/java/com/udacity/EntityExec/controller/DeliveryController.java) classes provided above


Use Postman to send the provided JSON to the /delivery endpoint and verify you get an id back (probably 1)


Create a @NamedQuery that returns all Deliveries for a specified Name. Place it in the appropriate Entity.  
- [Delivery](src/main/java/com/udacity/EntityExec/data/Delivery.java)


Create a method in DeliveryRepository that accepts a String name and returns a List of Delivery Entities using your @NamedQuery.
- [DeliveryRepository](src/main/java/com/udacity/EntityExec/repository/DeliveryRepository.java)

Create a data class called RecipientAndPrice that contains a String name and BigDecimal price.
- [RecipientAndPrice](src/main/java/com/udacity/EntityExec/DTO/RecipientAndPrice.java)


Create a method in DeliveryRepository that accepts a Long deliveryId and uses CriteriaBuilder 
to populate an instance of RecipientAndPrice that contains the recipient name 
for the provided deliveryId and the total price of all the plants in the order.
- [DeliveryRepository](src/main/java/com/udacity/EntityExec/repository/DeliveryRepository.java)
  - TODO: how to projection with CriteriaBuilder?

## JPA Exercise 3

Create a PlantRepository interface that extends JpaRepository.


Add one (or more!) methods that can return a Boolean indicating whether a specified plant id has been delivered.
- [Spring data jpa 复杂动态查询方式总结_缄默的果壳的博客-CSDN博客_jpa复杂查询](https://blog.csdn.net/qq_30054997/article/details/79420141)
- [Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)
  - Supported keywords inside method names(and, or, between...)

Add a method that returns all plants cheaper than a specified price.


Create service methods to:

- Save a plant
- Check if a plant has been delivered
- Find a list of plants cheaper than a specified price

### Flushing and transaction:
- TODO: transaction boundaries

## DataSource:
### DataSource Exercise 2:

Create a new class annotated with @Configuration: [DataSourceConfig](src/main/java/com/udacity/EntityExec/config/DataSourceConfig.java)


Add a @Bean method that returns a DataSource


Add a new property to your [application.properties](src/main/resources/application.properties) file that stores the url for your datasource


Update the method to retrieve the url from the properties file
```java
 @ConfigurationProperties(prefix = "com.udacity.datasource")
 ```

Programmatically set the user and password on the datasource object
[application.properties](src/main/resources/application.properties)

Run your application to make sure you can still connect

### DataSource Exercise 3 hibernate init
[注解@ConfigurationProperties使用方法 - 健人雄 - 博客园](https://www.cnblogs.com/tian874540961/p/12146467.html)

Update [application.properties](src/main/resources/application.properties) to always initialize your datasource and tell hibernate to create tables


Set the properties to show formatted SQL output


Run your application


Verify the tables were created for Plant and Delivery Entities

### Data Sources Exercise 4 Unit Test data sources 
Create a new test class or modify the default test class included from Spring Initializr
[ExercisesApplicationTests](src/test/java/com/udacity/EntityExec/ExercisesApplicationTests.java)

Add the @DataJpaTest annotation


Inject a TestEntityManager


Create a testPriceLessThan method that validates your PlantRepository.findByPriceLessThan method


Create a testDeliveryCompleted method that validates your PlantRepository.deliveryCompleted method


Run the tests to make sure they pass!

[JPA - EntityManager详解_流烟默的博客-CSDN博客](https://blog.csdn.net/J080624/article/details/78751411#:~:text=%E3%80%901%E3%80%91EntityManager%E4%BB%8B%E7%BB%8D,O%2FR%20%E6%98%A0%E5%B0%84%E7%9A%84%E7%AE%A1%E7%90%86%E3%80%82)