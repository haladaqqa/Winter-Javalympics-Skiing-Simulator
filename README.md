# Winter Javalympics Skiing Simulator

## Project Description

### Introduction

We recently saw the 2022 Winter Olympics in which skaters, skiers, snowboarders, bobsledders, and many other winter Olympians from around the world competed in one of the most highly regarded sporting tournaments. We now introduce to you the Winter Javalympics, in which we will push our limits and demonstrate our unwavering work ethic as we implement a program that simulates a skier coming down a treacherous hill.

A hill is represented as a binary tree, and the skier starts at the root of the tree and has to quickly decide which way to ski next – left or right – based on what they see ahead in each of the segments below. In some cases, only one direction will be accessible, and when the skier reaches the bottom (i.e., there are no further places to ski below), they have finished the run. Note that there are three types of hill segments: jump segment (with a specified height), slalom segment (with a specified direction), and regular segments that do not contain anything special. The skier must decide the best path to take down the hill, but each hill is unknown to the skier until they begin down the frigid slopes.

### Requirements for Choosing the Next Path

The requirements for choosing the next path are as follows:
- If there are no subsequent paths, they are finished.
- If there is exactly one subsequent path, they must take that path regardless of what it contains or does not contain.
- If there are two subsequent paths,
  - If both contain jumps, choose the one with the greater height, or go right if their heights are equal.
  - If exactly one contains a jump, take that path.
  - If both contain slaloms, go to the one whose direction is leeward (you can safely assume that any time there are two slaloms from the same segment, one is leeward and one is windward – never two of the same direction).
  - If exactly one contains a slalom, and the other is a regular segment, go to the slalom ONLY if its direction is leeward; otherwise, go to the regular segment.
  - If both are regular segments, choose right as the default direction.

## Getting Started

### Prerequisites

- JDK (Java Development Kit)
- IDE for Java development (e.g., Eclipse, IntelliJ IDEA)

### Setup

1. Clone this repository to your local machine.

2. Open the project in your Java IDE.

3. Build the project.

## Usage

- To run the program, execute the `Ski.java` file. Make sure to provide the necessary input data, as explained below.

### Input Data Format

- Create a String array containing data about the node types. The node types could be:
  - `null` -> no child node there
  - `""` (empty string) -> regular segment
  - `jump-#` -> jump segment of height #, e.g., "jump-6" denotes a jump of height 6
  - `slalom-L` -> slalom segment in the leeward (safe) direction
  - `slalom-W` -> slalom segment in the windward (unsafe) direction

## Implementation Details

- The project consists of two main Java classes: `Ski.java` and `TreeBuilder.java`.
- `Ski.java` is responsible for simulating the skier's descent down the skiing hill.
- `TreeBuilder.java` is used to build the binary trees for the program using a queue-based approach.

## Conclusion

The Winter Javalympics Skiing Simulator project simulates a skier's descent down a skiing hill represented as a binary tree, following specific criteria for choosing the next path. It's an educational project showcasing Java programming and data structure concepts.

