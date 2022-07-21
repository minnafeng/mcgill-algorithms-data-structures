import java.util.*;

public class DynamicProgramming {

    public static boolean optimizes(int sum, int newWeight){ // returns true if adding new weight optimizes solution
        int currentDiff = Math.abs(1000-sum);
        int newSum = sum + newWeight;
        int newDiff = Math.abs(1000-newSum);
        if (newDiff < currentDiff){ // gets closer to 1000
            return true;
        } else if (newDiff == currentDiff){
            if (newSum > sum){ // we pick the heavier weight when difference is the same
                return true;
            }
        }
        return false;
    }

    public static int weight(int[] plates) {
        TreeSet<Integer> combinations = new TreeSet<>();

        int sum = 0;
        int i, j; // counters
        for (i=0; i<plates.length; i++){ // for each plate in plates
            int newSum = 0;
            if (plates[i] == 1000){ // break if 1000
                return 1000;
            }
            combinations.add(plates[i]); // add initial plate to list of combos


            sum = plates[i]; // initialize sum with initial plate
            for (j=0; j<plates.length; j++){ // for every other plate in plates

                if (j <= i){ // same plate, or combo has already been calculated
                    continue;
                }
                int k = j;
                newSum = sum;
                while (optimizes(newSum,plates[k]) == true){ // while adding new weight gets you close to 1000
                    newSum += plates[k]; // add new weight to sum
                    combinations.add(newSum); // add sum to list of combos

                    if (newSum == 1000){ // break if 1000
                        return newSum;
                    }

                    k++; // increment j

                    if (k == plates.length){ // if plate[j] is the last plate
                        break;
                    }
                }
            }
        }

        // find two closest sums to 1000
        if (combinations.size() == 1){
            return combinations.first();
        }
        int smaller = combinations.floor(1000);
        int bigger = combinations.ceiling(1000);
        if (Math.abs(1000-smaller) >= Math.abs(1000-bigger)){ // smaller is further away
            return bigger;
        }
        return smaller;
    }

    // testing
    public static void main(String[] args) {

    }

}
