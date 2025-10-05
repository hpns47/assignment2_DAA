# Selection Sort (Student B — Pair 1)

## Overview
This repository contains the implementation of **Selection Sort** for  
**Assignment 2 — Design and Analysis of Algorithms (DAA)**.  

The algorithm repeatedly selects the smallest element from the unsorted part of the array and places it in its correct position.  
An additional **pre-scan optimization** is implemented to detect already-sorted input, giving a **best-case time O(n)**.  
The sort runs **in-place** and uses **O(1)** extra memory.

This project includes:
- Clean Java implementation (`SelectionSort.java`)
- CLI benchmark runner with CSV export
- Metrics tracking (comparisons, swaps, allocations, runtime)
- Comprehensive JUnit 5 tests
- GitHub Actions CI for automated build & validation

---

## Usage Instructions

###  Build and Test
```bash
mvn clean verify