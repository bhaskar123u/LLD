Q. Design a Parking Lot
---

### Functional Requirements

### **1. Vehicle Entry**

* System should allow a vehicle to enter.
* Assign a spot based on vehicle type.
* Generate a parking ticket with
    * ticketId
    * vehicleNumber
    * entryTime
    * assignedSpot

---

### **2. Vehicle Exit**

* Accept ticket or vehicleNumber to exit.
* Compute fee based on parking duration.
* Release assigned spot.
* Return invoice with
    * duration
    * price
    * exitTime

---

### **3. Vehicle Types**

* 2W
* 4W

---

### **4. Pricing**

Use pricing strategy, by default **simple hourly pricing rule**:

* Pricing differs per vehicle type (e.g. Bike < Car)

---

### **5. Capacity**

* Parking lot has finite spot capacity for each spot type.
* If no compatible spot → entry denied.

---

### **6. Multi-floor Parking Lot**
* Multi-floor parking support
* Pre Booked reservations
* Passes for few customers

---

### Optional Add-ons

Pick extension:

* Multiple floors support
* Electric vehicle spot
* Payment abstraction (Card/Cash)
* Display availability

---

### Non-Functional Requirements

* Must avoid double-allocation of the same spot (atomic assignment)
* Must track availability efficiently
* Must be able to retrieve current usage
