import java.util.*;

/*

During Dr Banner mission in the planet Titan, he got captured by the army of Thanos. Dr Banner is currently trapped 
in a 3D jail and he needs your help to find the quickest way out. In his email, Dr Banner describes the jail to be 
composed by unit cubes which may or may not be filled with indestructible rock. Dr Banner can only move one unit in 
the following directions: east, west, north, south, up or down. He emphasizes that he is not able to move diagonally. 
Dr Banner says in his email that it takes one minute to move one unit in one of the allowed directions.

Given the description of the 3D jail made by Dr Banner, I have been able to code a representa-
tion of it. In particular, the jail will be represented by a 3D String array (jail[level][rows][columns]) of one-character 
String. Each character describes one cell of the jail. A cell of indestructible rock is indicated by a “#” and empty 
cells are represented by a “.”. The current position of Dr Banner (i.e., the starting point) is represented by “S” 
and the exit of the jail by the letter “E”.

For this assignment, you will complete the function public static int find_exit(String[][][] jail), which receives as a parameter 
the (jail[level][rows][columns]) and returns an integer representing the shortest time it takes for Dr Banner to escape from 
the 3D jail. If it is not possible to escape, the function must return -1.

*/

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

    // testing
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
