# Shopping cart

Shopping cart application is a simple web application where you can create online orders.
## Features

- Add remove and update items in to cart
- Create users
- View order details
- Checkout shopping cart
- DTO classes
- Business error handling

## Services
- customer service
    - User register
    - Get user by username
    - Get user by ID
- Cart service
    - Add item in to cart
    - Update item in cart
    - Delete item from cart
- Order service
    - Checkout shopping cart

## Deployment and run
### prerequisite
- install mysql in local
- install java 8
- Deploy MySQL databases(Import [SQL Script](https://github.com/heshawa/shopping-cart/blob/main/SQL%20scripts/all_3_dbs.sql))
- Postnam for invoke the apis
### run
- Checkout code from git
```sh
$git checkout 
```
#### Build and run
- Build and run [discovery-server](https://github.com/heshawa/shopping-cart/tree/main/discovery-server)
- Build and run 3 services ([customer](https://github.com/heshawa/shopping-cart/tree/main/customer), [shopping-cart](https://github.com/heshawa/shopping-cart/tree/main/shoppingcart), [payments](https://github.com/heshawa/shopping-cart/tree/main/payment))
- Build and run [demo-application](https://github.com/heshawa/shopping-cart/tree/main/demo-application)

#### Execute jar files
- navigate into service folders
- Execute build commands
    - mvn clean install -DskipTests
- You can find executable jar files in target folder
- Double click on each jar or run following command 
```sh
$java -jar cart.jar
$java -jar customer.jar
$java -jar orders.jar
```
**Note: services will be up on given urls**
- customer service - http://localhost:8083/customers
- Order service - http://localhost:8081/orders
- cart service - http://localhost:8082/cart
- Eureka server - http://localhost:8761
- Demo Application - http://localhost:8085/executeUserCartCreation

### Execute APIs

- navigate to api-collection folder and import the given [postman collection](https://github.com/heshawa/shopping-cart/blob/main/shopping-cart.postman_collection.json) into postman
- execute Apis which are imported inorder to run full use case

### Execute Scenario given in PDF
- Start demo-application
- Call http://localhost:8085/executeUserCartCreation to execute code
- Console will display shopping cart total value and details in shopping cart after updating shopping carts

## Skipped due to time restriction
- Password encrypion
- Authentcation and authorization
- Unit test
- Include product in shopping cart service
## Future enhancements
- Implemen authentication and authorization
- Payment options
- Password encryption
- Implement Product service
- Saved shopping cart to checkout later
- Integrate API manager to wrap API
- Event driven message sending


