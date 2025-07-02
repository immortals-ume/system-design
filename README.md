# System Design (LLD + HLD)

## Design Patterns & SOLID Principles

Implemented in Java, Design Patterns and SOLID Principles are used in day-to-day coding.

The repository consists of implementations of Gang of Four (GoF) design patterns and SOLID principles.

---

## Design Patterns Overview

Design patterns are proven solutions to common software design problems. They help you write code that is easier to
understand, maintain, and scale.

### Creation Design Patterns

These patterns are concerned with the way objects are created. They are used when decisions must be made at the time of
instantiation of a class.  
The following are the Creation Design Patterns used:

1. Factory Method
2. Builder
3. Abstract Factory Method
4. Prototype
5. Singleton

### Structural Design Patterns

Structural patterns simplify the design of large object structures by identifying relationships between them.  
They describe common ways of composing classes and objects so that they become repeatable solutions.

1. Adapter
2. Bridge
3. Composite
4. Decorator
5. Facade
6. Flyweight
7. Proxy

### Behavioural Design Patterns

Behavioural patterns are concerned with algorithms and the assignment of responsibilities between objects.

1. Chain of Responsibility
2. Command
3. Interpreter
4. Iterator
5. Mediator
6. Memento
7. Observer
8. State
9. Strategy
10. Template Method
11. Visitor

---

# SOLID Principles

This repository explains the **SOLID principles** of object-oriented programming using simple, real-life analogies.
Whether you're a beginner or just looking to refresh your understanding, this guide breaks down complex ideas into
relatable concepts.

## 🧱 What is SOLID?

**SOLID** is an acronym that represents five key principles of object-oriented design. These principles help developers
build software that is:

- Easy to maintain
- Easy to understand
- Flexible to change
- Less error-prone

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

---

# DRY Principle in Layman's Terms

This repository explains the **DRY** principle of software development in simple, real-life terms to help beginners
understand why avoiding repetition makes code cleaner, smarter, and easier to maintain.

## 🧠 What is DRY?

**DRY** stands for:

> **Don't Repeat Yourself**

The idea is simple:  
**Write code once, reuse it everywhere.**  
Avoid writing the same logic, data, or instructions in multiple places.

## 🧱 Why It Matters

- Repeating code = More chances for bugs
- If something changes, you’ll have to update it in every place
- DRY code is easier to read, fix, and scale

## 🔧 Real-World Analogy

Imagine filling out a form 50 times with the **same phone number**.  
Would you rather:

- ❌ Write the number **50 times**, or
- ✅ Write it **once**, and **copy/paste** it or refer to it?

If the number changes, you only need to fix it once if you **DRY** it!

## 💻 Code Example

### ❌ Without DRY (repetition):

```

# Calculating price with tax multiple times

price1 = 100 + (100 * 0.1)
price2 = 200 + (200 * 0.1)
price3 = 300 + (300 * 0.1)

```

### ✅ With DRY (reusable code):

```

def add_tax(price):
return price + (price * 0.1)

price1 = add_tax(100)
price2 = add_tax(200)
price3 = add_tax(300)

```

---

# KISS Principle in Layman's Terms

This repository explains the **KISS** principle of software development using simple analogies and examples. Perfect for
beginners who want to write code that is clean, understandable, and maintainable.

## 💡 What is KISS?

**KISS** stands for:

> **Keep It Simple, Stupid**  
(Or more kindly: **Keep It Super Simple**)

The core idea:  
**Don't make things more complicated than they need to be.**

## 🧱 Why It Matters

- Simpler code is easier to read and understand
- Easier to maintain and extend
- Less prone to bugs
- Anyone on the team can work with it — even future you!

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

## 💻 Code Example

### ❌ Not KISS (Too Clever):

```

function isEven(num) {
return num % 2 === 0 ? true : false;
}

```

### ✅ KISS (Straightforward):

```

function isEven(num) {
return num % 2 === 0;
}

```

---

# Additional Best Practices

## YAGNI Principle

**YAGNI** stands for:
> **You Aren't Gonna Need It**

Don’t add functionality until it’s necessary. Avoid building features “just in case.”  
_Focus on what you need now, not what you might need in the future._

## Composition Over Inheritance

Favor composing objects with simple, reusable components rather than relying heavily on inheritance hierarchies.  
This leads to more flexible and maintainable code.

## Principle of Least Astonishment

Design your code so that it behaves in a way users expect.  
Surprising or non-intuitive behavior leads to bugs and confusion.

---

## Summary Table

| Principle/Pattern            | Key Idea                                  | Real-World Analogy              |
|------------------------------|-------------------------------------------|---------------------------------|
| SOLID                        | Five OOP principles for maintainable code | Specialist workers              |
| DRY                          | Don't repeat yourself                     | Write phone number once         |
| KISS                         | Keep it simple                            | Simple birdhouse                |
| YAGNI                        | Don’t build what you don’t need           | Only pack essentials for a trip |
| Composition over Inheritance | Prefer combining simple objects           | Lego blocks over a fixed toy    |

---

**Feel free to expand or trim these sections based on your audience and use-case!**
