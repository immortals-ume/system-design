# System Design(LLD + HLD)

## SOLID Principles

This repository explains the **SOLID principles** of object-oriented programming using simple, real-life analogies.
Whether you're a beginner or just looking to refresh your understanding, this guide breaks down complex ideas into
relatable concepts.

---

## 🧱 What is SOLID?

**SOLID** is an acronym that represents five key principles of object-oriented design. These principles help developers
build software that is:

- Easy to maintain
- Easy to understand
- Flexible to change
- Less error-prone

---

## 🔎 SOLID Principles Explained

### ✅ S — Single Responsibility Principle

**One job, one person.**
> Every class or module should have only one reason to change.

📦 _Real-world analogy_:  
A painter shouldn't be asked to fix plumbing. Give one task to one specialist. If there's a painting issue, you only
call the painter.

---

### ✅ O — Open/Closed Principle

**Open to extend, closed to break.**
> Software should be open for extension but closed for modification.

📦 _Real-world analogy_:  
If you want to add a new tool to your toolbox, you just place it in. You don’t change the old tools to make room — they
still work as is.

---

### ✅ L — Liskov Substitution Principle

**Substitute without breaking things.**
> Subtypes must be substitutable for their base types without altering the correctness of the program.

📦 _Real-world analogy_:  
If your regular plumber is on vacation, another plumber should be able to step in and do the same job — not an
electrician!

---

### ✅ I — Interface Segregation Principle

**Only ask for what you need.**
> No client should be forced to depend on methods it does not use.

📦 _Real-world analogy_:  
A painter shouldn't have to fill out forms for plumbing or electrical work. Give them only what’s relevant to their job.

---

### ✅ D — Dependency Inversion Principle

**Rely on ideas, not details.**
> Depend on abstractions, not concrete implementations.

📦 _Real-world analogy_:  
You don’t care *how* a nail is driven into the wall — hammer, drill, or nail gun — as long as the job is done. Just ask
for a “nail driver,” not a specific tool.

# DRY Principle in Layman's Terms

This repository explains the **DRY** principle of software development in simple, real-life terms to help beginners
understand why avoiding repetition makes code cleaner, smarter, and easier to maintain.

---

## 🧠 What is DRY?

**DRY** stands for:

> **Don't Repeat Yourself**

The idea is simple:  
**Write code once, reuse it everywhere.**  
Avoid writing the same logic, data, or instructions in multiple places.

---

## 🧱 Why It Matters

- Repeating code = More chances for bugs
- If something changes, you’ll have to update it in every place
- DRY code is easier to read, fix, and scale

---

## 🔧 Real-World Analogy

Imagine filling out a form 50 times with the **same phone number**.  
Would you rather:

- ❌ Write the number **50 times**, or
- ✅ Write it **once**, and **copy/paste** it or refer to it?

If the number changes, you only need to fix it once if you **DRY** it!

---

## 💻 Code Example

### ❌ Without DRY (repetition):

```python
# Calculating price with tax multiple times
price1 = 100 + (100 * 0.1)
price2 = 200 + (200 * 0.1)
price3 = 300 + (300 * 0.1)
```

###✅ With DRY (reusable code):

```python
def add_tax(price):
    return price + (price * 0.1)

price1 = add_tax(100)
price2 = add_tax(200)
price3 = add_tax(300)
```

# KISS Principle in Layman's Terms

This repository explains the **KISS** principle of software development using simple analogies and examples. Perfect for
beginners who want to write code that is clean, understandable, and maintainable.

---

## 💡 What is KISS?

**KISS** stands for:

> **Keep It Simple, Stupid**  
(Or more kindly: **Keep It Super Simple**)

The core idea:  
**Don't make things more complicated than they need to be.**

---

## 🧱 Why It Matters

- Simpler code is easier to read and understand
- Easier to maintain and extend
- Less prone to bugs
- Anyone on the team can work with it — even future you!

---

## 🔧 Real-World Analogy

Imagine you're building a birdhouse.

### ❌ Overcomplicated:

- Smart sensors
- Automated doors
- Solar panels

### ✅ Simple (KISS):

- Wooden box
- A hole
- A perch

If the goal is to give birds a place to nest, **keep it simple**.  
Fewer moving parts = fewer things that can go wrong.

---

## 💻 Code Example

### ❌ Not KISS (Too Clever):

```javascript
function isEven(num) {
  return num % 2 === 0 ? true : false;
}
```

### ✅ KISS (Straightforward):

```javascript
function isEven(num) {
  return num % 2 === 0;
}
```
