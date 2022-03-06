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

