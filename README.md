# hotel-booking

## Running from terminal

Clean and Install artifacts.

``mvn clean && mvn install``


Run the application

``mvn spring-boot:run -pl application``

This will start application in ``http://localhost:8080``

## Create Order API

``http://localhost:8080/api/v1/order/createorder``

### Mandatory Fields

- checkInDate (Date - yyyyMMdd format)
- checkOutDate (Date - yyyyMMdd format)
- customerId (Integer - i.e., 1)
- roomId (Integer - i.e., 1)
- numberOfGuests (Integer - i.e., 1)
