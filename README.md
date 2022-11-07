# hotel-booking

## Setting up Database
```
docker run --name mysql -d \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=password \
  --restart unless-stopped \
  mysql:8
```

### Logging into MySQL console
``docker exec -it mysql mysql -p``

### Create the Database
``CREATE DATABASE hotel_booking;``

## Running from Terminal

### Clean and Install artifacts.
``mvn clean && mvn install``

### Run the application
``mvn spring-boot:run -pl application``

### This will start application in
``http://localhost:8080``

## Create Order API
``http://localhost:8080/api/v1/order/create``

### Mandatory Fields
- checkInDate (Date - yyyyMMdd format)
- checkOutDate (Date - yyyyMMdd format)
- customerId (Integer - i.e., 1)
- roomId (Integer - i.e., 1)
- numberOfGuests (Integer - i.e., 1)

## Create Customer API
``http://localhost:8080/api/v1/customer/create``

### Mandatory Fields
- firstName (String)
- lastName (String)
- number (String - i.e., abc123)
- email (String - i.e., john@doe.com)