/* 

Q1.
We want to compare the performance of hash tables implemented using chaining and open
addressing. In this assignment, we will consider hash tables implemented using the multiplication
and linear probing methods. Note that we are using the hash function h to define g.

We want to estimate the number of collisions when inserting keys with respect to keys and the
choice of values for A.

Implemented methods OpenAddressing.probe(), Chaining.chain(), insertKey() (in both Chaining and OpenAddressing), and OpenAddressing.removeKey().

*/

import java.io.*;
import java.util.*;

public class OpenAddressing {
    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    protected OpenAddressing(int w, int seed, int A) {

        this.w = w;
        this.r = (int) (w-1)/2 +1;
        this.m = power2(r);
        if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
        }
        else{
            this.A = A;
        }
        this.Table = new int[m];
        for (int i =0; i<m; i++) {
            Table[i] = -1;
        }

    }

    /** Calculate 2^w*/
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        if(seed>=0){
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max-min-1);
        return i+min+1;
    }
    /**Implements the hash function g(k)*/
    public int probe(int key, int i) {
        int hashVal = (int) ((int) ((((A * key) % power2(w)) >> (w-r)) + i) % power2(r));
        return hashVal;
    }


    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public int insertKey(int key){
        int collision = 0;
        int hashVal; // computed from hash function
        int i = 0;
        while (i < m) { // while index less than # slots
            hashVal = probe(key,i);
            if (Table[hashVal]<0){ // empty space
                Table[hashVal] = key;
                break;
            }  // else -> collision
            collision++;
            i++;
        }
        return collision;
    }

    /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
    public int insertKeyArray (int[] keyArray){
        int collision = 0;
        for (int key: keyArray) {
            collision += insertKey(key);
        }
        return collision;
    }

    /**Removes key k into hash table. Returns the number of collisions encountered*/
    public int removeKey(int key){
        int collision = 0;
        int hashVal; // value computed from hash function
        int i = 0;
        while (i < m) { // while index less than # slots
            hashVal = probe(key,i);
            if (Table[hashVal] == key){ // found key
                Table[hashVal] = -2; // set distinct value to ensure probe skips for next search
                break;
            } else if (Table[hashVal] == -1){ // probed empty slot, key not found
                collision++;
                break;
            }
            collision++;
            i++;
        }
        return collision;
    }
}
