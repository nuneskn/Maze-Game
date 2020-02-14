package solution;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * MazeGame2 is a backtracking version of MazeGame.
 * 
 * @author Mitch Parry
 * @version 2015-06-02
 */
public class MazeGame2
{
    /**
     * The size of each side of the game map.
     */
    private final static int HEIGHT = 99;
    private final static int WIDTH = 99;
    private final static int GOALROW = 98;
    private final static int GOALCOL = 98;

    /**
     * The game map, as a 2D array of ints.
     */
    private boolean[][] wall;
    
    private boolean[][] visited;
    
   

    /**
     * Constructor sets up the maps and the path list.
     * 
     * @param mazeFile
     *            - name of the file containing the map
     */
    public MazeGame2(String mazeFile)
    {
        loadMaze(mazeFile);
    }

    /**
     * Function loads the data from the maze file and creates the 'blocked' 
     * 2D array.
     *  
     * @param mazeFile the input maze file.
     */
    private void loadMaze(String mazeFile)
    {
        wall = new boolean[HEIGHT][WIDTH];
        visited = new boolean[HEIGHT][WIDTH];
        Scanner mazeScanner;
        try
        {
            mazeScanner = new Scanner(new FileReader(mazeFile));
            for (int i = 0; i < HEIGHT; i++)
            {
                for (int j = 0; j < WIDTH; j++)
                {
                    if (mazeScanner.hasNext() == true  
                            && mazeScanner.next().equals("1"))
                    {
                        wall[i][j] = true;
                    }
                }
            }
            mazeScanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + mazeFile);
        }
    }

    /**
     * Non-recursive starter method for finding the path.
     * 
     * @return The path (no solution if null.)
     */
    public String findSolution()
    {
        // Call recursive method from here and return the string
        // representing the path.
        return backtrack(0, 0, "");
    }
    
    /**
     * This is a helper method for checking the bounds.
     * 
     * @param row is an int
     * @param col is an int
     * @return boolean
     */
    /*public boolean bounds(int row, int col)
    {
        if (row <= HEIGHT && row > 0)
        {
            if (col <= WIDTH && col > 0)
            {
                return true;
            }
        }
        return false;
    }*/

    /**
     * Recursive backtracking method for finding a path from a starting point.
     * 
     * @param row
     *            - row of starting point
     * @param col
     *            - column of starting point
     * @param pathSoFar
     *            - the current path so far.
     * @return whether a path was found
     */
    public String backtrack(int row, int col, String pathSoFar)
    {
        // TODO: write the recursive backtrack algorithm.
        // fix the return value!
        
        String result = null;
        visited[row][col] = true;

        if (GOALROW == row && GOALCOL == col)
        {
            return pathSoFar;
        }
        else 
        {
            if (row - 1 > 0 && wall[row - 1][col] == false 
                && visited[row - 1][col] == false) 
            {
                /*String*/ result = backtrack(row - 1, col, 
                    pathSoFar + " up");
                if (result != null)
                {
                    return result;
                }

            }
            if (col + 1 < WIDTH && wall[row][col + 1] == false 
                && visited[row][col + 1] == false)
            {
                /*String*/ result = backtrack(row, col + 1, 
                    pathSoFar + " right");
                if (result != null)
                {
                    return result;
                }
            }
            if (row + 1 < HEIGHT && wall[row + 1][col] == false 
                && visited[row + 1][col] == false)
            {
                /*String*/ result = backtrack(row + 1, col,
                    pathSoFar + " down");
                if (result != null)
                {
                    return result;
                }
            }
            if (col - 1 > 0 && wall[row][col - 1] == false 
                && visited[row][col - 1] == false)
            {
                /*String*/ result = backtrack(row, col - 1, 
                    pathSoFar + " left");
                if (result != null)
                {
                    return result;
                }
            }
            /*if (bounds(row, col) == false || wall[row][col] == true 
                    || visited[row][col] == true)
            {
                return result = null;
            }*/
        }
        return result;

    }

    /**
     * Prints the map.
     */
    public void printMap()
    {
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                if (wall[i][j])
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Creates a new TreasureHunt2 object and tries to find a solution.
     * 
     * @param args
     *            - command line arguments
     */
    public static void main(String[] args)
    {
        MazeGame2 t = new MazeGame2("data/maze0.txt");
        t.printMap();
        String solution = t.findSolution();
        t.printMap();
        if (solution != null)
        {
            System.out.println(solution);
        }
        else
        {
            System.out.println("There is no solution.");
        }
    }
}
