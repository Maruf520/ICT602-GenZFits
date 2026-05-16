 GenZFits — Project Lifecycle

**ICT602 Software Engineering · Assessment 3 Part A · Group 6**

Welcome to our GenZFits project. This is a simple project runs via CLI. This is simple explanation of our complicated module that we described in our Software Design Specification report. 
It is written in Architecture: java 21 with no external dependency and it is following N-Tier Architecture.
Layered architecture of our project is well documented in section 2 of the SDS report.
## Group Members

| Full Name                       | Student ID |
|---------------------------------|------------|
| Md Maruf                        | 68803      |
| Mohammed Ashrafujjaman Hera     | 70898      |
| Mohammad Riham Hossain          | 69176      |
| Pratikshya Ghimire              | 57860      |

---

## Project Layout
This is the Layout structure of our project
```
src/main/java/com/genzfits/
├── Main.java                  
├── presentation/              
│   ├── ConsoleApp.java
│   └── ConsoleUI.java
├── service/                   
│   ├── AuthService.java
│   ├── ProductService.java
│   ├── CartService.java
│   ├── OrderService.java
│   └── PaymentService.java
├── data/                      
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   └── OrderRepository.java
├── model/                     
│   ├── User.java               
│   ├── Customer.java
│   ├── Admin.java
│   ├── Address.java
│   ├── Product.java           
│   ├── ShoppingCart.java
│   ├── CartItem.java
│   ├── Order.java
│   ├── OrderStatus.java        
│   ├── PaymentMethod.java      
│   └── PaymentResult.java
└── util/
    └── PasswordHasher.java
```

---

## Prerequisites

- **JDK 21 or newer.** Verify with:

  ```bash
  java -version
  ```




---

## Build

From the project root (the directory that contains the `src` folder):

### macOS / Linux

```bash
mkdir -p out
find src/main/java -name "*.java" | xargs javac -d out
```

### Windows (PowerShell)

```powershell
mkdir out 2>$null
$files = Get-ChildItem -Recurse -Filter *.java src/main/java | ForEach-Object { $_.FullName }
javac -d out $files
```

---

## Run

```bash
java -cp out com.genzfits.Main
```

The CLI starts at the login menu.

---

## Demo Walk-Through

A demo customer is automatically seeded for the marketer to use to test the order
flow without registering.

| Field    | Value                  |
|----------|------------------------|
| Email    | `maruf@genzfits.com`    |
| Password | `maruf1234`             |

A typical end-to-end flow:

1. Choose **1. Login** → enter the credentials above.
2. Choose **1. Browse products** → note any product ID (for example `P-001`).
3. Reply **y** to "Add to cart?" → enter the product ID and a quantity.
4. (Optional) Browse again and add a second product.
5. Choose **2. View cart** to confirm the line items and total.
6. Choose **3. Checkout**:
   - Enter a shipping address (any values are fine).
   - Choose **1. Card** (any 16-digit number works), or **2. Afterpay**.
7. The CLI prints a confirmation with order ID, status `PAID`, transaction ID,
   and shipping address.
8. Choose **4. View order history** to see the placed order.
9. Choose **6. Exit** to leave the application.

