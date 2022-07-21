import com.sun.jdi.connect.Connector;

import java.util.*;


public class TopologicalSort {

    public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {
        // Computing the in-degrees
        int[] inDegree = new int[graph.size()]; // array to store in-degrees by index
        for (Integer key: graph.keySet()){
            ArrayList<Integer> list = graph.get(key);
            for (Integer node : list){
                inDegree[node]++;
            }
        }

        // Put all vertices with 0 in-degree into list
        ArrayList<Integer> list = new ArrayList<>();
        int i; // counter
        for (i=0; i<inDegree.length; i++){
            if (inDegree[i] == 0){
                list.add(i);
            }
        }

        // Create two queues with one starting on Earth, the other on Asgard
        LinkedList<Integer> queue1 = new LinkedList<>();
        LinkedList<Integer> queue2 = new LinkedList<>();
        for (Integer node : list){
            if (location[node] == 1){
                queue1.add(node);
            }
            else{
                queue2.add(node);
            }
        }

        // if both planets have reparations with in-degree 0, compute both transportations
        if (!queue1.isEmpty()&&!queue2.isEmpty()) {
            LinkedList<Integer> temp = new LinkedList<>();
            for (Integer node : queue1){
                temp.add(node);
            }
            for (Integer x : queue2){ // append queue2 to queue1
                queue1.add(x);
            }
            for (Integer x : temp){ // append queue1 to queue2
                queue2.add(x);
            }

            // sort both lists
            ArrayList<Integer> sortedVertices1 = sort(graph, queue1, inDegree);
            ArrayList<Integer> sortedVertices2 = sort(graph, queue2, inDegree);

            // Compute minimum transportations for both lists
            int transportations1 = compute(graph,sortedVertices1,location);
            int transportations2 = compute(graph,sortedVertices2,location);

            return Math.min(transportations1,transportations2);
        } else if (queue1.isEmpty()){ // only need to compute queue2
            ArrayList<Integer> sortedVertices2 = sort(graph, queue2, inDegree);
            int transportations2 = compute(graph,sortedVertices2,location);
            return transportations2;
        } else {
            ArrayList<Integer> sortedVertices1 = sort(graph, queue1, inDegree);
            int transportations1 = compute(graph,sortedVertices1,location);
            return transportations1;
        }
    }

    public static ArrayList<Integer> sort(Hashtable<Integer,ArrayList<Integer>> graph, Queue<Integer> queue, int[] inDegree){
        int[] inDegreeCopy = Arrays.copyOf(inDegree,inDegree.length);
        ArrayList<Integer> sortedVertices = new ArrayList<>(); // add dequeued vertices in list
        while (!queue.isEmpty()){
            int node = queue.remove(); // dequeue node
            sortedVertices.add(node); // add node into sorted list

            // decrease in-degree of all dependent adjacent nodes
            for (Integer adjNode : graph.get(node)){
                inDegreeCopy[adjNode]--;
                if (inDegreeCopy[adjNode] == 0){
                    queue.add(adjNode);
                }
            }
        }
        return sortedVertices;
    }

    public static int compute(Hashtable<Integer,ArrayList<Integer>> graph, ArrayList<Integer> sortedVertices,int[]location){
        Queue<Integer> queue = new LinkedList<>();
        int transportations = 0; // first planet doesn't count as transportation

        int planet = location[sortedVertices.get(0)];
        // Compute minimum transportations
        while (!sortedVertices.isEmpty()){
            int node = sortedVertices.remove(0); // remove first on list
            if (location[node]!=planet){
                transportations++;
            }
            planet = location[node]; // planet we're on
            queue.add(node); // enqueue starting node
            while (!queue.isEmpty()) {
                node = queue.remove();
                for (Integer adjNode : graph.get(node)) {
                    if (location[adjNode] == planet && isReparable(graph,sortedVertices,adjNode)) {
                        sortedVertices.remove(adjNode); // remove from reparations to do
                        queue.add(adjNode);
                    }
                }
            }
        }
        return transportations;
    }


    public static boolean isReparable(Hashtable<Integer,ArrayList<Integer>> graph, ArrayList<Integer> sortedVertices, int node){
        for (Integer v : sortedVertices){
            if (graph.get(v).contains(node)){
                return false;
            }
        }
        return true;
    }

    // testing
    public static void main(String[] args) {
        Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<>();
        graph.put(0,new ArrayList<Integer>(Arrays.asList(1,2)));
        graph.put(1,new ArrayList<Integer>(Arrays.asList(3,4)));
        graph.put(2,new ArrayList<Integer>(Arrays.asList(3,4)));
        graph.put(3,new ArrayList<Integer>());
        graph.put(4,new ArrayList<Integer>());

        int[] location = {1,2,1,2,1};

        Hashtable<Integer, ArrayList<Integer>> graph1 = new Hashtable<>();
        graph1.put(0,new ArrayList<Integer>());
        graph1.put(1,new ArrayList<Integer>(Arrays.asList(2)));
        graph1.put(2,new ArrayList<Integer>());

        int[] location1 = {1,2,1};

        int transportations = rings(graph1,location1);
        System.out.println(transportations);
    }

}
