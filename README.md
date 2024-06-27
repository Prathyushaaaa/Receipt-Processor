# Receipt-Processor
Receipt Processor

The application calculates reward points from a receipt. Built with Java and Spring Boot, it provides endpoints to process receipts and calculate points based on the receipts.

The following rules collectively define how many points should be awarded to a receipt.

   One point for every alphanumeric character in the retailer name.
   50 points if the total is a round dollar amount with no cents.
   25 points if the total is a multiple of 0.25.
   5 points for every two items on the receipt.
   If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
   6 points if the day in the purchase date is odd.
   10 points if the time of purchase is after 2:00pm and before 4:00pm.

Prerequisites
Before you begin, ensure you have the following installed on your system:

Docker
Windows & macOS: Download and install Docker Desktop from Docker's official website. 

Linux: Use the following commands to install Docker:
sh
Copy code
sudo apt-get update
sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io

Verify the installation by running:

sh
Copy code
docker --version

Git
Windows & macOS: Download and install Git from Git's official website.

Linux: Use the following command to install Git:
sh
Copy code
sudo apt-get update
sudo apt-get install git

Verify the installation by running:

sh
Copy code
git --version
Setting Up the Environment

Clone the repository:
For Windows, open cmd and follow the instruciotns below
git clone https://github.com/Prathyushaaaa/Receipt-Processor.git
cd Receipt-Processor

For MacOS/Linux, follow the instructions below

sh
Copy code
git clone https://github.com/Prathyushaaaa/Receipt-Processor.git
cd Receipt-Processor
Build the Docker image:

sh
Copy code
docker build -t receipt-processor .
Run the Docker container:

sh
Copy code
docker run -p 8080:8080 receipt-processor
Access the application:

Examples
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
