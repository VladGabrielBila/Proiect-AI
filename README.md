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
   git clone (https://github.com/VladGabrielBila/Proiect-AI.git
2. **Compile the Project**: Ensure you have javac installed and compile the Java files.
   ```sh
   javac Main.java RandomGraph.java BFS.java UCS.java A_Star.java
3. **Run the Main Class**: Execute the Main class to generate the graph and run the algorithms.
   ```sh
   java Main
   This will append the results to output.txt.
## Output
The program generates an output file named țoutput.txtț with the following details for each run:

- The generated graph
- The minimum travel cost and path found by each algorithm
- The time taken by each algorithm
## Requirements
- Java Development Kit (JDK) 8 or higher
