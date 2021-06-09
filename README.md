# wallet-microservice
Wallet Microservice

Basic RestAPI with Spring Boot for debit transactions.


# API Technologies
1. Java 8
2. H2 Database
3. Maven 3.8.1

# Running Instructions
1.From root folder
``` 
mvn clean install
``` 

2.Start
``` 
mvn spring-boot:run
``` 

# API Endpoints
Http GET endpoints:
1. http://localhost:8080/api/wallets List all wallets with transactions per player

Http GET endpoints:
1. http://localhost:8080/api/transactions
``` 
{
"name":"Alican Sahin",
"balance":"12000"
}
``` 
2.http://localhost:8080/api/transactions
``` 
{
"unqTransactionId":"123",
"wallet_id":"1" ,
"debitAmount":"100"
}
``` 
# Features
1. Implemented JWT
2. Definitions of Player and Currency
