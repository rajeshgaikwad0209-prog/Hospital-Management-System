## 📌 Advanced Hospital Management System (Kotlin - Console Based)

A beginner-to-intermediate level, console-based Hospital Management System written in Kotlin. This project demonstrates object-oriented programming concepts with enhanced features like billing, admission tracking, doctor workload limits, and long-stay alerts. It provides a structured and realistic simulation of hospital operations using a menu-driven interface.

---

## ✨ Features

* **Doctor Management:** Register, view, and delete doctors with specialty and contact details.
* **Patient Management:** Register, view, search, and delete patients with illness and age details.
* **Admission System:** Admit patients under doctors with real-time tracking.
* **Discharge System:** Discharge patients with automatic bill generation and summary.
* **Billing System:** Calculates bill based on number of days stayed.
* **Doctor Workload Limit:** Each doctor can handle a maximum of 5 active patients.
* **Admission History:** View complete history of all admissions and discharges.
* **Long Stay Alerts:** Alerts if a patient is admitted for 7 or more days with projected bill.
* **Search Functionality:** Search patients by name or illness.
* **Day Simulation:** Advance days to simulate hospital timeline and track patient stays.
* **Statistics Tracking:** Tracks total patients treated by each doctor and total bills paid by patients.
* **Menu-Driven Interface:** Clean and easy console navigation.

---

## ⚙️ Prerequisites

To run this project on your local machine, you need:

* Java Development Kit (JDK)
* Kotlin Compiler

---

## ▶️ How to Run Locally

1. **Save the Kotlin file** (e.g., `HospitalApp.kt`)

2. **Open Command Prompt / Terminal**

3. **Navigate to the folder**

```bash
cd path/to/your/folder
```

4. **Compile the Kotlin file**

```bash
kotlinc HospitalApp.kt
```

5. **Run the program**

```bash
kotlin HospitalAppKt
```

---

## 📊 Sample Data

👉 Data is added dynamically by user during runtime (no fixed pre-loaded dataset).

---

## 🏗️ Classes Used

| Class              | Purpose                                            |
| ------------------ | -------------------------------------------------- |
| Doctor             | Stores doctor details, workload, and patient count |
| Patient            | Stores patient details and billing history         |
| AdmissionRecord    | Tracks admission, discharge, and billing           |
| BillingSystem      | Handles bill calculation and summary               |
| HospitalManagement | Core system logic and operations                   |
| HospitalApp        | User interface and menu handling                   |

---

## 👨‍💻 Built With

* Kotlin
* Arrays (Fixed-size storage)
* Object-Oriented Programming (OOP)
* Console-based UI
* No external libraries

---

## 👨‍💻 Developed by

**Rajesh** 🚀
