import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;


public class GraphInMatrix extends Graph{
    private int order;  // the number of vertices
    private boolean[][] aMatrix;
    
    public GraphInMatrix(int order) {
        this.order = order;
        aMatrix = new boolean[order][order];
        // the following is not really necessary in Java.
        for (int i = 0; i < order; i++)
            for (int j = 0; j < order; j++)
                aMatrix[i][j] = false;
    }

    public GraphInMatrix(boolean[][] aMatrix) {
        order = aMatrix.length;
        // check the matrix is a square and symmetric
        this.aMatrix = aMatrix;
    }
    
    public void addEdge(int a, int b) {
        // usually we don't allow self-loop 
        // i.e., an edge with both ends on the same vertex
        if (a == b) return;
        aMatrix[a][b] = aMatrix[b][a] = true;
    }
    
    public boolean isAdjacent(int a, int b) {
        return aMatrix[a][b];
    }

    // return the degree of vertex a.
    public int degree(int a) {
    	int d = 0;
        for(int i = 0; i < order; i++)
        	d += aMatrix[a][i]?1:0;
    	return d;
    }

    // return the total number of edges in the graph.
    public int size() {
//    	int s = 0;
//        for(int i = 0; i < order; i++)
//        	s += degree(i);
//        return s / 2;
    	int s = 0;
        for(int i = 0; i < order; i++)
        	for(int j = 0; j < i; j++)
        		s += aMatrix[i][j]?1:0;
        return s;
    }

    // check whether the graph contains a triangle
    // i.e., three vertices pairwise adjacent
    public boolean triangle() {
        for(int i = 0; i < order; i++)
            for(int j = 0; j < i; j++) {
            	if (!aMatrix[i][j]) continue;
                for(int k = 0; k < j; k++)
                	if (aMatrix[i][k] && aMatrix[j][k]) return true;
            }
        return false;
    }

    public void delete(int a, int b){
        if(!aMatrix[a][b]){
            System.out.println("No such edge");
            return;
        }
        aMatrix[a][b] = false;
        aMatrix[b][a] = false;
    }


    public void bfs() { bfs(0); }

    // breadth-first search
    public void bfs(int a) {
    }
    
    public void dfs(int a) {
    }

    public GraphInMatrix square() {
        int a = order;
        GraphInMatrix r = new GraphInMatrix(a);
        for(int i = 0; i < order; i++){
            for(int j = i+1;j<order;j++){
                if(aMatrix[i][j]){
                    r.aMatrix[i][j] = true;
                    r.aMatrix[j][i] = true;
                    continue;
                }
                for(int m =0;m<order;m++){
                    if(aMatrix[i][m] && aMatrix[m][j]){
                        r.aMatrix[i][j] = true;
                        r.aMatrix[j][i] = true;
                        break;
                    }
                }
            }
        }
        return r;
    }

    public int[] eulerian(int[] route){
        IntStack s = new IntStack();
        int count = 0;
        int curV = 0;
        do{
            boolean flag = true;
            for(int i=0; i<order; i++){
                if(aMatrix[curV][i]){
                    s.push(curV);
                    this.delete(curV, i);
                    curV = i;
                    flag = false;
                    break;
                }
            }
            if(flag){
                route[count++] = curV;
                curV = s.pop();
            }
        }while(!s.isEmpty());
        route[count] = curV;
        return route;
    }

    public int[] eulerian(){
        int[] a = new int[this.size() + 1];
        for(int i=0; i<a.length; i++) a[i] = -1;
        for(int i=0; i<order; i++){
            if(degree(i) % 2 != 0)
                return a;
        }
        return eulerian(a);
    }

    public static void main(String[] args) {
        boolean[][] m1 = {
                {false, false, false, true, false, false, false, false},
                {false, false, false, true, true, false, false, false}, 
                {false, false, false, false, true, false, false, false}, 
                {true, true, false, false, false, true, false, false}, 
                {false, true, true, false, false, false, true, false}, 
                {false, false, false, true, false, false, true, true}, 
                {false, false, false, false, true, true, false, true}, 
                {false, false, false, false, false, true, true, false}};
        GraphInMatrix graph1 = new GraphInMatrix(m1);
        System.out.println("Origin:");
        for(int i = 0; i< 8; i++) {
            System.out.println(Arrays.toString(graph1.aMatrix[i]));
        }
        GraphInMatrix graph2 = graph1.square();
        System.out.println("Square:");
        for(int i = 0; i< 8; i++) {
            System.out.println(Arrays.toString(graph2.aMatrix[i]));
        }
        GraphInMatrix g = new GraphInMatrix(8);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,0);
        g.addEdge(1,4);
        g.addEdge(4,5);
        g.addEdge(5,3);
        g.addEdge(3,1);
        g.addEdge(5,6);
        g.addEdge(6,7);
        g.addEdge(7,5);
        int[] result;
        result = g.eulerian();
        System.out.println("Origin:");
        for(int i = 0; i< 8; i++) {
            System.out.println(Arrays.toString(graph2.aMatrix[i]));
        }
        System.out.println("Eulerian:");
        System.out.println(Arrays.toString(result));
    }
}
