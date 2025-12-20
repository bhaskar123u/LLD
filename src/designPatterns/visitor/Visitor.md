````md
# Visitor Design Pattern

---

## What Problem Does Visitor Solve?

The **Visitor pattern** is useful when:

- You have a **stable set of object types**
- You frequently need to add **new operations / behaviors** on those objects
- You want to avoid:
  - `instanceof` checks
  - Large `if-else` / `switch` blocks
  - Modifying existing domain classes again and again

In short:

> **Visitor helps when operations change more often than object types.**

---

## Visitor vs Polymorphism vs Strategy (When to Use What)

### Polymorphism
> “We use polymorphism when behavior belongs to the object and the **method contract is stable**.”

- Method set is fixed
- Implementations vary per subtype
- Behavior is **intrinsic** to the object

### Strategy
> “We use Strategy when we want to **switch one algorithm** at runtime without changing the client.”

- Usually one primary method
- Algorithm varies and is chosen dynamically at runtime

### Visitor
> “We use Visitor when we have a stable object hierarchy but frequently **add new operations across those objects**.”

- Object structure is stable
- Operations are volatile
- Behavior is **extrinsic** to the object

---

## Code Analysis for Visitor

### Case 1: Polymorphism Fits Well

```java
interface Vehicle {
    void start();
    void stop();
    void applyBrake();
    void increaseSpeed();
    void decreaseSpeed();
}
````

Here:

* Every `Vehicle` **must** support these operations
* Even if new vehicle types are added, the **method set remains the same**
* Only implementations differ

➡ **Polymorphism is the correct choice**

---

### Case 2: Polymorphism Starts Breaking Down

Assume:

* Vehicle types are **mostly fixed**: `Car`, `Bike`, `Plane`, `Boat`
* New **features/operations** are added frequently:

  * Speed monitoring
  * Emission checks
  * Insurance validation
  * Analytics
  * Compliance rules

#### Naive Solution (Polymorphism)

1. Add new methods directly to vehicle interface.

Problems:

* Forces modification of all classes, voilated **I** of SOLID
* All sub classes become bloated with time

2. Add new methods directly to derived classes.

Problems:

* Violates **O** of SOLID
* Class become bloated with time

---

## Visitor Design Pattern to the Rescue

### Key Assumption (Very Important)

> **Object types are stable, but operations change frequently.**

---

### Visitor Interface

```java
interface Visitor {
    void visit(Car car);
    void visit(Bike bike);
    void visit(Plane plane);
    void visit(Boat boat);
}

class Bike implements Vehicle{
    // properties related to bike
    // additional functions related to bike
    void visit(Visitor visitor){
        visitor.visit(this); // passing this whole object to visitor implementation
    }
}
```

Each `visit()` method represents an operation **for a specific object type**.

---

### Concrete Visitor Example

**New Requirement:**
Add a warning buzzer if speed crosses the allowed limit.

```java
class SpeedLimitBuzzerVisitor implements Visitor {

    @Override
    public void visit(Car car) {
        // car-specific speed check
    }

    @Override
    public void visit(Bike bike) {
        // bike-specific speed check
    }

    @Override
    public void visit(Plane plane) {
        // plane-specific speed check
    }

    @Override
    public void visit(Boat boat) {
        // boat-specific speed check
    }
}
```

✅ New behavior added
✅ No modification to `Car`, `Bike`, `Plane`, `Boat`
✅ Open–Closed Principle preserved (for operations)

---

## The “Partial Visitor” Problem (and Solution)

Sometimes a new operation applies only to **some** object types.

Example:

* Applies only to `Car`, `Bike`, `Plane`
* Not applicable to `Boat`

### Problem

Every visitor still needs to declare all `visit()` methods.

### Solution (Recommended)

Use **default methods** or a **base visitor**.

```java
interface Visitor {

    default void visit(Car car) {}
    default void visit(Bike bike) {}
    default void visit(Plane plane) {}
    default void visit(Boat boat) {}
}
```

Now a visitor can override only what it needs:

```java
class RoadSafetyVisitor implements Visitor {

    @Override
    public void visit(Car car) {
        // logic
    }

    @Override
    public void visit(Bike bike) {
        // logic
    }
}
```

✔ Cleaner code
✔ No forced empty methods
✔ Visitor contract remains complete

---

## Core Trade-off of Visitor (Must Remember)

| Aspect                       | Visitor Pattern |
| ---------------------------- | --------------- |
| Easy to add new operations   | ✅               |
| Easy to add new object types | ❌               |
| Object hierarchy stability   | Required        |
| Operation extensibility      | Excellent       |

> **Visitor trades flexibility in object types for flexibility in operations.**

---

## Real-World LLD Use Case (Payments / Credit Cards)

### Problem Statement

You are building a **card transaction processing system**.

### Stable Transaction Types

* Domestic card transaction
* International card transaction
* EMI (installment) transaction

### Frequently Added Operations

* Risk scoring
* Fee calculation
* Compliance checks
* Reporting
* Limit validation

This is a **classic Visitor use case**.

---

### Design Constraints

🚫 You cannot modify transaction classes
🚫 No `instanceof`
🚫 No `if-else` on transaction types
🚫 Only Visitor-based solution

---

## One-Line Summary (Interview Ready)

> “Visitor is used when I have a stable object hierarchy and need to frequently add new cross-cutting operations without modifying existing classes.”

---

## Final Mental Model

* **Polymorphism** → Behavior belongs to the object
* **Strategy** → One algorithm varies
* **Visitor** → Operations vary across stable object types

---

**Note:**
If you find yourself frequently adding new object types, Visitor may not be the right pattern. It is intentionally rigid on the object side to enable extensibility on the operation side.

```
```
