/*

Q2.
We want to implement a disjoint set data structure with union and find operations. 
In this question, we model a partition of n elements with distinct integers ranging from 0 to n − 1
(i.e. each element is represented by an integer in [0, ··· , n − 1], and each integer in [0, ··· , n − 1]
represent one element). We choose to represent the disjoint sets with trees, and to implement the
forest of trees with an array named par. More precisely, the value stored in par[i] is parent of
the element i, and par[i]==i when i is the root of the tree and thus the representative of the disjoint set.

You will implement union by rank and the path compression technique seen in class. The rank is
an integer associated with each node. Initially (i.e. when the set contains one single object) its
value is 0. Union operations link the root of the tree with smaller rank to the root of the tree
with larger rank. In the case where the rank of both trees is the same, the rank of the new root
increases by 1. You can implement the rank with a specific array (called rank) that has been
added to the template, or use the array par (this is tricky). Note that path compression does not
change the rank of a node.

The constructor takes one argument n (a strictly positive integer) that indicates the number of elements in 
the partition, and initializes it by assigning a separate set to each element.

*/

import java.io.*;
import java.util.*;

public class DisjointSets {

    private int[] par;
    private int[] rank;

    /* constructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }

    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }

    /* find representative of element i */
    public int find(int i) {
        if (par[i] == i){
            return i;
        } else {
            par[i] = find(par[i]);
            return par[i];
        }
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
        if (find(i) != find(j)){
            if (rank[par[i]] < rank[par[j]]){
                par[find(i)] = find(j); // merge into j
            } else if (rank[par[i]] > rank[par[j]]){
                par[find(j)] = find(i); // merge into i
            } else { // rank[i] == rank[j]
                par[find(i)] = find(j); // merge into j
                rank[par[j]]++;
            }
        }
        return par[i];

    }
    
    // testing
    public static void main(String[] args) {

        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);

    }

}
