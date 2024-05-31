# Traveling Salesman Problem Solver

This project implements three different algorithms to solve the Traveling Salesman Problem (TSP):

1. **Breadth-First Search (BFS)**
2. **Uniform Cost Search (UCS)**
3. **A* Search with Minimum Spanning Tree (MST) heuristic**

The program generates a random weighted graph and applies each algorithm to find the minimum travel cost and the corresponding path. It measures and records the time taken by each algorithm and appends the results to an output file.

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [How to Run](#how-to-run)
- [Output](#output)
- [Classes and Methods](#classes-and-methods)
- [Requirements](#requirements)
- [License](#license)

## Overview

The Traveling Salesman Problem (TSP) is a classic problem in computer science and optimization. It involves finding the shortest possible route that visits each city exactly once and returns to the original city.

This project provides an implementation of three different algorithms to solve the TSP: BFS, UCS, and A* with an MST heuristic. It generates a random weighted graph, applies each algorithm to find the minimum travel cost and the corresponding path, and records the results in an output file.

## Project Structure

- **Main.java**: Main class that generates a random graph, runs the TSP algorithms, and writes the results to an output file.
- **RandomGraph.java**: Utility class to generate a random graph.
- **BFS.java**: Contains the BFS implementation for solving the TSP.
- **UCS.java**: Contains the UCS implementation for solving the TSP.
- **A_Star.java**: Contains the A* implementation with an MST heuristic for solving the TSP.

## How to Run

1. **Clone the Repository**: Clone this repository to your local machine using Git.
   ```sh
   git clone https://github.com/your_username/traveling-salesman-problem.git
