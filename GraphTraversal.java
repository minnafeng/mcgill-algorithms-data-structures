import java.util.*;


public class GraphTraversal {

    public static int find_exit(String[][][] jail) {
        Queue<Integer[]> queue = new LinkedList<>();
        int moves = 0;
        Integer[] start = new Integer[4]; // store coordinates + moves performed
        String[] directions = {"east","west","north","south","up","down"};

        int i,j,k; // level, row, col
        for (i=0; i< jail.length; i++){
            for (j=0; j<jail[i].length; j++){
                for (k=0; k<jail[i][j].length; k++){
                    if (jail[i][j][k].equals("S")){
                        // start search
                        start[0]=i; // store starting coordinate
                        start[1]=j;
                        start[2]=k;
                        start[3]=0;
                        jail[i][j][k] = "v"; // set source node as visited
                        queue.add(start); // enqueue source node
                        while (!queue.isEmpty()){
                            Integer[] coord = queue.remove(); // dequeue
                            // add all adjacent unvisited nodes to queue
                            for (String dir: directions){ // for each possible direction
                                Integer[] newCoord = isValid(jail,coord,dir); // check if move is valid
                                if (newCoord!=null){
                                    if (jail[newCoord[0]][newCoord[1]][newCoord[2]].equals("E")) {
                                        return newCoord[3];
                                    }

                                    jail[newCoord[0]][newCoord[1]][newCoord[2]] = "#"; // set node as visited
                                    queue.add(newCoord); // enqueue if valid

                                }
                            }
                        }
                    }
                }
            }

        }
        return -1;
    }

    // checks if cell exists (not out of bounds), is an empty cell, has not been visited
    // returns coordinates of validated position
    public static Integer[] isValid(String[][][] jail, Integer[] coord, String dir){
        int level = coord[0];
        int row = coord[1];
        int col = coord[2];
        Integer[] newCoord = new Integer[4];
        newCoord[3] = coord[3] + 1;
        try{
            if (dir.equals("east")){
                if (!jail[level][row][col+1].equals("#")){
                    newCoord[0] = level;
                    newCoord[1] = row;
                    newCoord[2] = col+1;
                    return newCoord;
                }
            }
            if (dir.equals("west")){
                if (!jail[level][row][col-1].equals("#")){
                    newCoord[0] = level;
                    newCoord[1] = row;
                    newCoord[2] = col-1;
                    return newCoord;
                }
            }
            if (dir.equals("north")){
                if (!jail[level][row-1][col].equals("#")){
                    newCoord[0] = level;
                    newCoord[1] = row-1;
                    newCoord[2] = col;
                    return newCoord;
                }
            }
            if (dir.equals("south")){
                if (!jail[level][row+1][col].equals("#")){
                    newCoord[0] = level;
                    newCoord[1] = row+1;
                    newCoord[2] = col;
                    return newCoord;
                }
            }
            if (dir.equals("up")){
                if (!jail[level+1][row][col].equals("#")){
                    newCoord[0] = level+1;
                    newCoord[1] = row;
                    newCoord[2] = col;
                    return newCoord;
                }
            }
            if (dir.equals("down")){
                if (!jail[level-1][row][col].equals("#")){
                    newCoord[0] = level-1;
                    newCoord[1] = row;
                    newCoord[2] = col;
                    return newCoord;
                }
            }

        } catch (Exception e){
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        String[][][] jail = new String[][][]
                    {
                    {
                            {"S",".",".",".","."},
                            {".","#","#","#","."},
                            {".","#","#",".","."},
                            {"#","#","#",".","#"},
                    },
                    {
                            {"#","#","#","#","#"},
                            {"#","#","#","#","#"},
                            {"#","#",".","#","#"},
                            {"#","#",".",".","."},
                    },
                    {
                            {"#","#","#","#","#"},
                            {"#","#","#","#","#"},
                            {"#",".","#","#","#"},
                            {"#","#","#","#","E"},
                    }
                    };
        int moves = find_exit(jail);
        System.out.println(moves);


    }

}
