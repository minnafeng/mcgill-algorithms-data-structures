Q1 - Chaining.java, OpenAddressing.java

We want to compare the performance of hash tables implemented using chaining and open addressing. In this assignment, we will consider hash tables implemented using the multiplication
and linear probing methods. Note that we are using the hash function h to define g.

We want to estimate the number of collisions when inserting keys with respect to keys and the choice of values for A.

Q2 - DisjointSets.java

We want to implement a disjoint set data structure with union and find operations. In this question, we model a partition of n elements with distinct integers ranging from 0 to n − 1
(i.e. each element is represented by an integer in [0, ··· , n − 1], and each integer in [0, ··· , n − 1] represent one element). We choose to represent the disjoint sets with trees, and to implement the
forest of trees with an array named par. More precisely, the value stored in par[i] is parent of the element i, and par[i]==i when i is the root of the tree and thus the representative of the disjoint set.

You will implement union by rank and the path compression technique seen in class. The rank is an integer associated with each node. Initially (i.e. when the set contains one single object) its
value is 0. Union operations link the root of the tree with smaller rank to the root of the tree with larger rank. In the case where the rank of both trees is the same, the rank of the new root
increases by 1. You can implement the rank with a specific array (called rank) that has been added to the template, or use the array par (this is tricky). Note that path compression does not
change the rank of a node.

The constructor takes one argument n (a strictly positive integer) that indicates the number of elements in the partition, and initializes it by assigning a separate set to each element.

Q3 - DiscussionBoard.java

The teaching staff in Comp251 is really happy of how our discussion board (Ed) is working; however, we believe there is one function missing. This function will allow us to identify important
topics (discussed in Ed) by filtering key words. In particular, given a list of messages posted in Ed, we want a function that reports the words used by every single user on the discussion board.
This list must be sorted from most to least used word (i.e., the word with the highest frequency must be the first one). In case of a frequency tie, the word needs to be sorted in alphabetical
order.

Let’s see now some features of the discussion board posts. The list of post will be provided to you as an array of strings (String[]), where every slot in the array will contain a message. All
messages will have the following characteristics:

• Each message is represented in Java as a String.

• Each message begins with a user’s name of no more than 20 characters.

• After the name, each message continues with the content of that user’s post all in lower case.

• The total number of characters across all messages, including spaces, will not exceed 2*10^6.
