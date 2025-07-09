Here is a **professional and complete `README.md`** for your `VAT Payment Backend` project, based on your actual Java code (Spring Boot with DTO, entity, controller, service, and repository). This includes:

* ✅ Project overview
* ✅ API endpoints
* ✅ Sample Postman data (request and response)
* ✅ Database schema
* ✅ Local setup
* ✅ Technologies used

---

### 📄 `README.md`

```markdown
# 💼 VAT Payment Backend

This is a Spring Boot backend service for managing VAT-related payments in a government taxation system. It supports creation and listing of payments through RESTful APIs and stores data in a PostgreSQL database.

> 🚀 **Live Deployment**: [http://vat-payment.onrender.com/payments](http://vat-payment.onrender.com/payments)

---

## 📦 Features

- Create manual VAT payments
- Fetch all payments
- Data persistence via PostgreSQL
- Input handled through DTO mapping

---

## 📂 API Endpoints

### 🔹 `POST /payments`

Create a new VAT payment.

#### ✅ Request Header:
```

Content-Type: application/json

````

#### 🧪 Sample JSON Body:
```json
{
  "tin": "TN1234567890",
  "suspenseName": "ABC Pvt Ltd",
  "regType": "Regular",
  "paymentWith": "Bank Transfer",
  "ackNo": "ACK202507091234",
  "amountToBePaid": 15000.00,
  "headOfAccount": "0040-VAT-Receipts",
  "paidTowards": "VAT Arrears",
  "mode": "Online",
  "courtFeeNo": "CF202507091",
  "paymentDate": "2025-07-09",
  "micrCode": "MICR11001123",
  "remarks": "Quarter 2 VAT payment",
  "amount": 12000.00,
  "interest": 2000.00,
  "penalty": 1000.00
}
````

#### 📤 Sample Success Response:

```json
{
  "paymentId": 1,
  "tin": "TN1234567890",
  "suspenseName": "ABC Pvt Ltd",
  "regType": "Regular",
  "paymentWith": "Bank Transfer",
  "ackNo": "ACK202507091234",
  "amountToBePaid": 15000.00,
  "headOfAccount": "0040-VAT-Receipts",
  "paidTowards": "VAT Arrears",
  "mode": "Online",
  "courtFeeNo": "CF202507091",
  "paymentDate": "2025-07-09",
  "micrCode": "MICR11001123",
  "remarks": "Quarter 2 VAT payment",
  "amount": 12000.00,
  "interest": 2000.00,
  "penalty": 1000.00,
  "timestamp": "2025-07-09T12:30:00",
  "createdAt": "2025-07-09T12:30:00"
}
```

---

### 🔹 `GET /payments`

Fetch all saved payment records.

#### 📤 Sample Response:

```json
[
  {
    "paymentId": 1,
    "tin": "TN1234567890",
    "suspenseName": "ABC Pvt Ltd",
    "regType": "Regular",
    "paymentWith": "Bank Transfer",
    "ackNo": "ACK202507091234",
    "amountToBePaid": 15000.00,
    "headOfAccount": "0040-VAT-Receipts",
    "paidTowards": "VAT Arrears",
    "mode": "Online",
    "courtFeeNo": "CF202507091",
    "paymentDate": "2025-07-09",
    "micrCode": "MICR11001123",
    "remarks": "Quarter 2 VAT payment",
    "amount": 12000.00,
    "interest": 2000.00,
    "penalty": 1000.00,
    "timestamp": "2025-07-09T12:30:00",
    "createdAt": "2025-07-09T12:30:00"
  }
]
```

---

## 🧾 Database Schema

```sql
CREATE TABLE payments (
  payment_id         BIGSERIAL PRIMARY KEY,
  tin                VARCHAR(20) NOT NULL,
  suspense_name      VARCHAR(100),
  reg_type           VARCHAR(20),
  payment_with       VARCHAR(50),
  ack_no             VARCHAR(20) REFERENCES acknowledgements(ack_no),
  amount_to_be_paid  NUMERIC(12,2),
  head_of_account    VARCHAR(50),
  paid_towards       VARCHAR(50),
  mode               VARCHAR(30),
  court_fee_no       VARCHAR(50),
  payment_date       DATE,
  micr_code          VARCHAR(20),
  remarks            TEXT,
  amount             NUMERIC(12,2),
  interest           NUMERIC(12,2),
  penalty            NUMERIC(12,2),
  timestamp          TIMESTAMP,
  created_at         TIMESTAMP DEFAULT now()
);

CREATE INDEX idx_payments_tin ON payments(tin);
CREATE INDEX idx_payments_date ON payments(payment_date);
```

---

## ⚙️ Local Setup Instructions

### ✅ Prerequisites:

* Java 17+
* Maven
* PostgreSQL

### 🧪 Steps:

1. **Clone the repository**

   ```bash
   git clone https://github.com/GaneshRehan/vat_portal_backend_nic.git
   cd vat_portal_backend_nic
   ```

2. **Configure the DB connection**

   Edit `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/vat_payment
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Run the app**

   ```bash
   mvn spring-boot:run
   ```

4. **Test the endpoints in Postman:**

   * `POST http://localhost:8080/payments`
   * `GET  http://localhost:8080/payments`

---

## 🧰 Technologies Used

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Lombok
* Render.com (deployment)

---
