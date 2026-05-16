 GenZFits — Order Lifecycle Module

**ICT602 Software Engineering · Assessment 3 Part A · Group 6**

This is the execution of the Customer Order Lifecycle – the complicated module
that we will use as described in our Software Design Specification. It is written in
Architecture: java 21 with no external dependency and it is following N-Tier Architecture.
Layered architecture that is documented in section 2 of the SDS.
## Group Members

| Full Name                       | Student ID |
|---------------------------------|------------|
| Md Maruf                        | md68803    |
| Mohammed Ashrafujjaman Hera     | mo70898    |
| Mohammad Riham Hossain          | mo69176    |
| Pratikshya Ghimire              | pr57860    |

---

## Project Layout

```
src/main/java/com/genzfits/
├── Main.java                  -- entry point + dependency wiring + demo seed data
├── presentation/              -- Presentation Layer (CLI screens)
│   ├── ConsoleApp.java
│   └── ConsoleUI.java
├── service/                   -- Service Layer (business logic)
│   ├── AuthService.java
│   ├── ProductService.java
│   ├── CartService.java
│   ├── OrderService.java
│   └── PaymentService.java
├── data/                      -- Data Access Layer (repositories)
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   └── OrderRepository.java
├── model/                     -- Domain Model
│   ├── User.java               (abstract)
│   ├── Customer.java
│   ├── Seller.java
│   ├── Admin.java
│   ├── Address.java
│   ├── Product.java
│   ├── Category.java           (enum)
│   ├── ShoppingCart.java
│   ├── CartItem.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── OrderStatus.java        (enum)
│   ├── PaymentMethod.java      (abstract)
│   ├── CreditCardPayment.java
│   ├── AfterpayPayment.java
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

  If you do not have Java installed, get the [Eclipse Temurin 21
  download](https://adoptium.net/temurin/releases/) for your operating system.

The only requirement is that. No Maven, Gradle or Spring;
project can be compiled using the plain `javac`.

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

If the build succeeds the `out/` directory will contain the compiled
`.class` files mirroring the package structure under
`out/com/genzfits/...`.

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
| Email    | `demo@genzfits.com`    |
| Password | `demo1234`             |

A typical end-to-end flow:

1. Choose **1. Login** → enter the credentials above.
2. Choose **1. Browse products** → note any product ID (for example `P-001`).
3. Reply **y** to "Add to cart?" → enter the product ID and a quantity.
4. (Optional) Browse again and add a second product.
5. Choose **2. View cart** to confirm the line items and total.
6. Choose **3. Checkout**:
   - Enter a shipping address (any values are fine).
   - Choose **1. Credit card** (any 16-digit number works), or **2. Afterpay**.
7. The CLI prints a confirmation with order ID, status `PAID`, transaction ID,
   and shipping address.
8. Choose **4. View order history** to see the placed order.
9. Choose **6. Exit** to leave the application.

---

## Demo Catalogue (seeded on start-up)

| ID    | Name                       | Brand       | Price   | Stock | Category    |
|-------|----------------------------|-------------|---------|-------|-------------|
| P-001 | Slim-Fit Denim Jeans       | UrbanLine   | $89.95  | 25    | Clothing    |
| P-002 | Oversized Cotton Hoodie    | Streetwave  | $69.00  | 40    | Clothing    |
| P-003 | Classic White Sneakers     | PaceForm    | $129.50 | 15    | Footwear    |
| P-004 | Leather Crossbody Bag      | Maven&Co    | $159.00 | 10    | Bags        |
| P-005 | Gold Hoop Earrings         | Lumière     | $45.00  | 50    | Jewellery   |

---

## What Is *Not* Implemented

Per section 4 of the SDS, the chosen complex module is the Customer Order
Lifecycle (login → browse → cart → checkout → place order). The full
SRS scope is much larger; the following are **deliberately out of scope** for
this assignment and appear on the class diagram only as reference:

- Seller dashboard, sales analytics, and product management screens
- Admin moderation tooling
- Wishlist, reviews, and recommendation engine
- Real Stripe / Afterpay API calls (we simulate the gateway responses)
- Persistence to PostgreSQL (we use in-memory `HashMap` repositories)

These are the natural next steps for Assessment 4 / a follow-up project.

---

## Document Index

The companion deliverables for this assessment are:

- `report/GenZFits_SDS_Report.docx` — Software Design Specification (this is the
  main written deliverable).
- `diagrams/ClassDiagram.png` — the conceptual UML class diagram embedded in
  the report.
- `diagrams/ClassDiagram.svg` — vector source for the class diagram.
