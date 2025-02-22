# Currency Converter API  

## 📌 Overview  
This Spring Boot application fetches real-time exchange rates and performs currency conversion using an external API.

## 🚀 Technologies Used  
- Java 17  
- Spring Boot  
- Maven  
- RestTemplate  

## 🔥 API Endpoints  

### ⿡ Get Exchange Rates  
*GET /api/rates?base=USD*  
Fetches exchange rates for the given base currency (default: USD).  

Response Example:
```json
{
    "base": "USD",
    "rates": {
        "EUR": 0.92,
        "INR": 82.50
    }
}

```
# Currency Converter API  

## 📌 Overview  
This Spring Boot application fetches real-time exchange rates and performs currency conversion using an external API.

## 🚀 Technologies Used  
- Java 17  
- Spring Boot  
- Maven  
- RestTemplate  

## 🔥 API Endpoints  

### 1. Get Exchange Rates  
**GET /api/rates?base=USD**  
Fetches exchange rates for the given base currency (default: USD).  

Response Example:
```
{
    "base": "USD",
    "rates": {
        "EUR": 0.92,
        "INR": 82.50
    }
}
```
### 2. Convert Currency

**POST /api/convert**
Converts an amount from one currency to another.

Request Body Example:
```
{
    "from": "USD",
    "to": "EUR",
    "amount": 100
}
```

❌ Error Handling

    Invalid currency → Returns "Invalid base currency" or "Invalid target currency".
    API failure → Returns "Exchange rate service unavailable".

🛠 How to Run Locally
1. Install Prerequisites

    Install JDK 17
    Install Maven

2. Clone the Repository
   
   git clone [(https://github.com/Vinayak6019/Currency-Converter-API-Integration)]
   cd currency-converter

   
📄 API Documentation

📌 Postman Collection: 🔗 View API Documentation [https://documenter.getpostman.com/view/40940048/2sAYdcqrXS]
  



