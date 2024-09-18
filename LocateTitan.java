package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Julia Dymnicki
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }
        
        String forgeStormbreakerInputFile = args[0];
        String forgeStormbreakerOutputFile = args[1];
        StdIn.setFile(forgeStormbreakerInputFile);
        
        StdOut.setFile(forgeStormbreakerOutputFile);
        
        
        //StdIn.setFile("locatetitan3.in");
    	int g = StdIn.readInt();
        double[] a = new double[g];
        for(int i = 0; i<g;i++){
            int x = StdIn.readInt();
            double y = StdIn.readDouble();
            a[x] = y;
        }
        int[][] n = new int[g][g];
        for(int i = 0; i<g;i++){
            for(int j = 0; j<g;j++){
                n[i][j] = StdIn.readInt();
            }
        }
        for(int i = 0; i<g;i++){
            for(int j = 0; j<g;j++){
                n[i][j] /= (a[i] * a[j]);
            }
        }
        int[] min = new int[g];
        boolean[] h = new boolean[g];
        min[0] = 0;
        for(int i =1; i<g;i++){
            min[i] = Integer.MAX_VALUE;
        }
        int mm = 0;
        int x = 0;
        for(int i =0; i<g-1;i++){
            mm = getMinCost(min, h);
            h[mm] = true;
            for(int j=0; j< g; j++){
                if(h[j] == false  && n[mm][j] != 0 && min[mm] != Integer.MAX_VALUE && (min[j] > (min[mm] + n[mm][j]))){
                    //min[j] = min[mm] + n[mm][j];
                    min[j] =  min[mm] + n[mm][j];
                    
                    //min[j] = min[mm] + n[j][mm];
                    //min[mm] = min[j] + n[mm][j];
                }
            }
        }
        
        for(int i=0;i<g;i++){
            x = min[i];
        }
        
        
        StdOut.print(x);


    }
    private static int getMinCost(int[] a, boolean[] b){
       int y =Integer.MAX_VALUE;
       int v= 0;
        for (int i =0; i< a.length-1;i++){
            if(a[i] <= y  && b[i] == false) {
                y= a[i];
                v = i;
        }
        }
        return v;
    }




        //StdOut.setFile(forgeStormbreakerOutputFile);

    }
    



