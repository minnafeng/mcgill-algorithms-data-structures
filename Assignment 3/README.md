# Assignment 3

## Q1 - GraphTraversal.java 

During Dr Banner mission in the planet Titan, he got captured by the army of Thanos. Dr Banner is currently trapped in a 3D jail and he needs your help to find the quickest way out. In his email, Dr Banner describes the jail to be composed by unit cubes which may or may not be filled with indestructible rock. Dr Banner can only move one unit in the following directions: east, west, north, south, up or down. He emphasizes that he is not able to move diagonally. Dr Banner says in his email that it takes one minute to move one unit in one of the allowed directions.

Given the description of the 3D jail made by Dr Banner, I have been able to code a representation of it. In particular, the jail will be represented by a 3D String array (jail[level][rows][columns]) of one-character String. Each character describes one cell of the jail. A cell of indestructible rock is indicated by a “#” and empty cells are represented by a “.”. The current position of Dr Banner (i.e., the starting point) is represented by “S” and the exit of the jail by the letter “E”.

For this assignment, you will complete the function public static int find_exit(String[][][] jail), which receives as a parameter the (jail[level][rows [columns]) and returns an integer representing the shortest time it takes for Dr Banner to escape from the 3D jail. If it is not possible to escape, the function must return -1.

## Q2 - TopologicalSort.java

Shang-Chi told me that during his last battle, one of his powerful rings got damaged. He needs to repair it as soon as possible; however fixing the ring is not an easy task. The reparation of the ring needs to be conducted in two different planets (planet Earth and planet Asgard). The reason for that is that each planet has a very specialized laboratory. The repair process has been divided into several stages; in which we know which planet each of the stages will take place on. Please notice that the reparation of the ring can begin in any of the two planets.

Transporting the ring (between the two planets) introduces additional risk (e.g., Thanos can try to steal it); therefore, it should be avoided whenever possible. Ideally, all the reparation work in the first planet will be done, and then the ring would be moved to the second one. Unfortunately, there are several dependencies between the reparation stages (i.e., some of them need to be completed before the others may begin). Your job for this question is to find an ordering of reparations stages that minimizes the number of times the ring needs to be moved from one planet to the other. In particular, you will need to complete the function public static int rings(Hashtable<Integer, ArrayList<Integer» graph, int[] location). This function receives as a parameter a hash table that represent the graph. The key of the hash table is a node, and the value of that key is a list of nodes that can not be completed before the key node is completed. A node in our problem represents one of the possible repair stages. The repair stages are enumerated from 0 to n − 1. The function also receive the integer array location, which contains n integers - where the i − th index has value of 1 if the i − th reparation stage will take place in the planet Earth, and 2 otherwise (i.e., it will take place in Asgard). Your function must return an integer representing the minimal number of times the ring needs to be transported between the two planets.

## Q3 - FordFulkerson.java, WGraph.java

In this exercise, we will implement the Ford-Fulkerson algorithm to calculate the Maximum Flow of a directed weighted graph. Your role will be to complete two methods in the template FordFulkerson.java.
