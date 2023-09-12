package avengers;

public class Graph {
    private boolean v[];
    private int[][] matrix;
    public Graph(int[][] g){
        v= new boolean[g.length];
        matrix = g;
    }
    public void trav(int rr){
        v[rr] = true;
        for(int i =0;i<matrix.length;i++){
            if(matrix[rr][i]==1){
                if(v[i]== false){
                    trav(i);
                }
            }
        }
    }
    public boolean connect(boolean[] d){
        for(int i=0;i<d.length;i++){
            if(!(d[i])!= v[i]){
                return false;
            }
        }
        return true;
    }
}
