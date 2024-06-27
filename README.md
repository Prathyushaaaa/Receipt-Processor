# Receipt-Processor
Receipt Processor

The application calculates reward points from a receipt. Built with Java and Spring Boot, it provides endpoints to process receipts and calculate points based on the receipts.  

The following rules collectively define how many points should be awarded to a receipt.  

   1. One point for every alphanumeric character in the retailer name.  
   2. 50 points if the total is a round dollar amount with no cents.  
   3. 25 points if the total is a multiple of 0.25.  
   4. 5 points for every two items on the receipt.  
   5. If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.  
   6. 6 points if the day in the purchase date is odd.  
   7. 10 points if the time of purchase is after 2:00pm and before 4:00pm.  
  
Prerequisites  
Before you begin, ensure you have the following installed on your system:  
Download and install Docker Desktop from Docker's official website.   
Download and install Git from Git's official website.  
Download and install Postman from Postman's official website.  
  
Setting Up the Environment  
To clone the repository, follow the below instructions:  
  
git clone https://github.com/Prathyushaaaa/Receipt-Processor.git  
cd Receipt-Processor  
  
Access the application:  

To understand how the points are evaluated, go through the following examples.  

Example 1  
```json
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}
```
Expected Output  
Total Points: 28  
Breakdown:  
     6 points - retailer name has 6 characters  
    10 points - 4 items (2 pairs @ 5 points each)  
     3 Points - "Emils Cheese Pizza" is 18 characters (a multiple of 3)  
                item price of 12.25 * 0.2 = 2.45, rounded up is 3 points  
     3 Points - "Klarbrunn 12-PK 12 FL OZ" is 24 characters (a multiple of 3)  
                item price of 12.00 * 0.2 = 2.4, rounded up is 3 points  
     6 points - purchase day is odd  
  + ---------  
  = 28 points  
  
Example 2  
```json
{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}
```
Expected Output  
Total Points: 109  
Breakdown:  
    50 points - total is a round dollar amount  
    25 points - total is a multiple of 0.25  
    14 points - retailer name (M&M Corner Market) has 14 alphanumeric characters
                note: '&' is not alphanumeric  
    10 points - 2:33pm is between 2:00pm and 4:00pm  
    10 points - 4 items (2 pairs @ 5 points each)  
  + ---------  
  = 109 points  
  
The application will be running on http://localhost:8080.  
API Endpoints  
Process Receipt Endpoint: /receipts/process  
Method: POST  
  
Get Points  
Endpoint: /receipts/{id}/points  
Method: GET  
Retrieves points for a specific receipt.  
  
Path Parameters:  
id (string): The ID of the receipt.  
  
Test on Postman:  
  
POST Request:  
URL: http://localhost:8080/receipts/process  
Body: Raw JSON (use the example JSON provided in the "Process Receipt" section).  
  
GET Request:  
URL: http://localhost:8080/receipts/{id}/points (replace {id} with the actual receipt ID obtained from the POST response)  
Method: GET  

Snapshots of the calculated reward points are as follows:  
POST Request  
<img width="959" alt="image" src="https://github.com/Prathyushaaaa/Receipt-Processor/assets/65707545/d1836c14-9350-4303-80b8-a41a68b574b8">  
GET Request  
<img width="959" alt="image" src="https://github.com/Prathyushaaaa/Receipt-Processor/assets/65707545/83ae8677-6496-48e7-a19c-a79a561a7371">
