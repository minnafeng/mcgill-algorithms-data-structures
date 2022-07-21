import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

public class FordFulkerson {

    public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
        ArrayList<Integer> path = new ArrayList<Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] parent = new int[graph.getNbNodes()]; // stores parent of each vertex for path
        boolean[] visited = new boolean[graph.getNbNodes()];
        boolean isReached = false; // destination is reached

        stack.push(source); // push source
        parent[source] = -1; // source node has no parent
        while (!stack.isEmpty()){
            int v = stack.pop(); // pop node
            if (!visited[v]){ // if unmarked
                visited[v] = true; // mark as visited
                for (Edge edge : graph.getEdges()){
                    if (edge.nodes[0] == v && !visited[edge.nodes[1]]){ // if edge comes from v and adj node unmarked
                        if (edge.nodes[1] == destination){
                            parent[edge.nodes[1]] = v; // set parent
                            isReached = true;
                            break;
                        }
                        stack.push(edge.nodes[1]); // push adjacent edges
                        parent[edge.nodes[1]] = v; // set parent
                    }
                }
            }
        }
        // Check if destination was reached
        if (!isReached){
            return null;
        }
        // Create path backing up from destination
        int v;
        for (v=destination; v!=source; v=parent[v]){
            path.add(0,v);
        }
        path.add(0,source); // add source at the head (?)
        return path;
    }

    public static String fordfulkerson( WGraph graph){
        String answer="";
        int maxFlow = 0;

        // Get source and sink
        int source = graph.getSource();
        int destination = graph.getDestination();

        // Create residual graph
        WGraph rGraph = new WGraph();
        rGraph.setSource(source);
        rGraph.setDestination(destination);
        for (Edge edge : graph.getEdges()){
            Edge forwardEdge = new Edge(edge.nodes[0],edge.nodes[1], edge.weight); // set forward edges
            rGraph.addEdge(forwardEdge);
            Edge backEdge = new Edge(edge.nodes[1],edge.nodes[0],0); // set backward edges
            rGraph.addEdge(backEdge);
        }

        while (pathDFS(source,destination,rGraph)!=null){ // while there is still a path
            ArrayList<Integer> path = pathDFS(source,destination,rGraph); // set path
            int pathFlow = Integer.MAX_VALUE; // initialise pathFlow to the highest value

            // get min capacity
            int i; // counter
            for (i=0; i<path.size()-1; i++){
                pathFlow = Math.min(pathFlow, rGraph.getEdge(path.get(i),path.get(i+1)).weight);
            }

            if (pathFlow==0){
                break;
            }

            // set residual capacities in residual graph
            for (i=0; i<path.size()-1; i++){
                if (graph.getEdge(path.get(i),path.get(i+1))!=null){ // forward edge (present in OG graph)
                    rGraph.getEdge(path.get(i),path.get(i+1)).weight -= pathFlow;
                } else { // back edge
                    rGraph.getEdge(path.get(i+1),path.get(i)).weight += pathFlow;
                }
            }



            maxFlow += pathFlow;
        }

        // change edge weights in original graph
        for (Edge edge: graph.getEdges()){
            edge.weight = rGraph.getEdge(edge.nodes[0],edge.nodes[1]).weight;
        }

        answer += maxFlow + "\n" + graph.toString();
        return answer;
    }


    public static void main(String[] args){
        WGraph g = new WGraph();
        g.setSource(0);
        g.setDestination(9);
        Edge[] edges = new Edge[] {
                new Edge(0, 1, 10),
                new Edge(0, 2, 5),
                new Edge(2, 3, 5),
                new Edge(1, 3, 10),
                new Edge(3, 4, 5),
                new Edge(4, 5, 10),
                new Edge(4, 6, 5),
                new Edge(6, 7, 5),
                new Edge(6, 8, 10),
                new Edge(8, 9, 10),
        };
        Arrays.stream(edges).forEach(e->g.addEdge(e));
        String result = FordFulkerson.fordfulkerson(g);
        System.out.println(result);

    }
}