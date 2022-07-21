import java.util.*;

/*

In this exercise, you will plan your homework with a greedy algorithm. The input is a list of homeworks
defined by two arrays: deadlines and weights (the relative importance of the homework towards your final grade). 
These arrays have the same size and they contain integers between 1 and 100. The index of each entry in the 
arrays represents a single homework, for example, Homework 2 is defined as a homework with deadline deadlines[2] 
and weight weights[2]. Each homework takes exactly one hour to complete.

Your task is to output a homeworkPlan: an array of length equal to the last deadline. Each entry in the array 
represents a one-hour timeslot, starting at 0 and ending at ’last deadline - 1’. For each time slot, homeworkPlan 
indicates the homework which you plan to do during that slot. You can only complete a single homework in one 1-hour 
slot. The homeworks are due at the beginning of a time slot, in other words if an assignment’s deadline is x, then 
the last time slot when you can do it is x - 1. For example, if the homework is due at t=14, then you can complete 
it before or during the slot t=13. If your solution plans to do Homework 2 first, then you should have homeworkPlan[0]=2 
in the output. Note that sometimes you will be given too much homework to complete in time, and that is okay.

Your homework plan should maximize the sum of the weights of completed assign- ments (regardless of the order on which 
they are completed). Please notice that an “≠1” value is allowed in between the homework plan.

Given two assignments A1 and A2, the method should output:
• 0, if the two items are equivalent
• 1, if a1 should appear after a2 in the sorted list • -1, if a2 should appear after a1 in the sorted list

*/

public class Greedy {
    ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
    int m;
    int lastDeadline = 0;

    protected Greedy(int[] weights, int[] deadlines, int size) {
        for (int i=0; i<size; i++) {
            Assignment homework = new Assignment(i, weights[i], deadlines[i]);
            this.Assignments.add(homework);
            if (homework.deadline > lastDeadline) {
                lastDeadline = homework.deadline;
            }
        }
        m =size;
    }

    /**
     *
     * @return Array where output[i] corresponds to the assignment
     * that will be done at time i.
     */
    public int[] SelectAssignments() {
        //TODO Implement this

        //Sort assignments
        //Order will depend on how compare function is implemented
        Collections.sort(Assignments, new Assignment());

        // If homeworkPlan[i] has a value -1, it indicates that the
        // i'th timeslot in the homeworkPlan is empty
        // homeworkPlan contains the homework schedule between now and the last deadline
        int[] homeworkPlan = new int[lastDeadline];
        for (int i=0; i < homeworkPlan.length; ++i) {
            homeworkPlan[i] = -1;
        }

        int weightSum = 0; // sum of weights
        int i;
        for (i=0; i<Assignments.size(); i++){
            Assignment a = Assignments.get(i);
            int j;
            for (j=a.deadline-1; j>=0; j--) { // place assignment at latest hour possible
                if (homeworkPlan[j] == -1) {
                    homeworkPlan[j] = a.number;
                    break;
                }
            }
        }
        return homeworkPlan;
    }
}
