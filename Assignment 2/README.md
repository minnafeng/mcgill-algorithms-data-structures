Q1 - CompleteSearch.java

We provide a two-dimensional board with some holes on it, where each hole can contain a small ball. During the game, and in each turn, you will be allowed to jump (horizontally or vertically) over an adjacent ball into the empty hole next to the jumped ball in line with it. Once the jump is performed, the ball (which was jumped over) is then removed from the board. The idea of the game is to apply the minimum number of moves (i.e., jumps) to end in a state with a minimum number of balls.

For this question, your task is to develop an algorithm (i.e., a complete search one) that de- termines the minimum number of balls that remains in the board after applying (recursively) the above-mentioned movement. You will also need to report the minimum number of moves required to reach that number of balls.

Q2 - DynamicProgramming.java

Dr. Robert Bruce Banner contacted me in order to help him with his weight training program. In particular, he is interestedin creating a program that will allow him to lift 1000kg. Dr Banner has several (positive integers less or equal than 1000) weight plates,possibly of different weights, and its goal is to add some of the plates to a bar so that it can train with a weight as close as possible to 1000kg. Given the (possible) case where two numbers are equally close to 1000 (e.g., 995 and 1005), Dr Banner will pick the greater one (in this case 1005).

Dr Banner is an excellent coder and he is really good in Dynamic Programming; however, he would be busy in a trip to planet Titanand he asked me for help. For this question, you will need to complete the function weight, which receives as a parameter a list of positive integers (where each integer is less than or equal than 1000), denoting the weight of each plate. You can safely assume that the length of the list is between 1 and 1000. The function must return one integer, the combined weight closest to 1000.

Q3 - Greedy.java, Assignment.java

In this exercise, you will plan your homework with a greedy algorithm. The input is a list of homeworks defined by two arrays: deadlines and weights (the relative importance of the homework towards your final grade).  These arrays have the same size and they contain integers between 1 and 100. The index of each entry in the arrays represents a single homework, for example, Homework 2 is defined as a homework with deadline deadlines[2] and weight weights[2]. Each homework takes exactly one hour to complete.

Your task is to output a homeworkPlan: an array of length equal to the last deadline. Each entry in the array represents a one-hour timeslot, starting at 0 and ending at ’last deadline - 1’. For each time slot, homeworkPlan indicates the homework which you plan to do during that slot. You can only complete a single homework in one 1-hour slot. The homeworks are due at the beginning of a time slot, in other words if an assignment’s deadline is x, then the last time slot when you can do it is x - 1. For example, if the homework is due at t=14, then you can complete it before or during the slot t=13. If your solution plans to do Homework 2 first, then you should have homeworkPlan[0]=2 in the output. Note that sometimes you will be given too much homework to complete in time, and that is okay.

Your homework plan should maximize the sum of the weights of completed assign- ments (regardless of the order on which they are completed). Please notice that an “≠1” value is allowed in between the homework plan.

Given two assignments A1 and A2, the method should output:
• 0, if the two items are equivalent
• 1, if a1 should appear after a2 in the sorted list • -1, if a2 should appear after a1 in the sorted list

Q4 - DivideAndConquer.java

For this question, you will need to implement a divide and conquer algorithm than given N and K as parameters, your function returns the K-th letter in the N-th string in the fibonacci sequence. For example, if N = 7 and K = 7, your algorithm must return X (please notice that the N=7 string in the sequence is “XYYXYYXYXYYXY”, and the K=7 letter in that string is “X”, which is bold). You can safely assume that K is at most the length of the Nth string.
