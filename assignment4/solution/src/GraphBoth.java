import java.io.BufferedReader;
import java.io.*;
import java.util.Arrays;

/**
 * Created by michael on 2016/12/6.
 */
public class GraphBoth {
    private int order;  // the number of vertices
    private boolean[][] aMatrix;

    public GraphBoth(int order) {
        this.order = order;
        aMatrix = new boolean[order][order];
    }

    public GraphBoth(boolean[][] aMatrix) {
        order = aMatrix.length;
        // check the matrix is a square and symmetric
        this.aMatrix = aMatrix;
        for(int i=0;i<order;i++){
            for(int j = i+1;j<order;j++){
                if(aMatrix[i][j]) addEdge(i, j);
            }
        }
    }

    public void addEdge(int a, int b) {
        // usually we don't allow self-loop
        // i.e., an edge with both ends on the same vertex
        aMatrix[a][b] = aMatrix[b][a] = true;
    }

    public static GraphBoth importGraph(String file){
        GraphBoth n;
        int[][] r;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("c://Users/michael/Desktop/test-case/" + file + ".graph"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String b = sb.toString();
            String[] a = b.split("\\s+");
            int length = a.length / 2;
            r = new int[length][2];
            int max = 0;
            for(int i=0; i<a.length; i+=2){
                int a1 = Integer.parseInt(a[i]);
                int a2 = Integer.parseInt(a[i + 1]);
                r[i/2][0] = a1;
                r[i/2][1] = a2;
                if(a1 >= a2){
                    if(a1 > max) max = a1;
                }
                else{
                    if(a2 > max) max = a2;
                }
            }
            n = new GraphBoth(max + 1);
           // System.out.println(max);
            for(int i=0; i<r.length; i++){
                n.addEdge(r[i][0], r[i][1]);
            }
            return n;
        }
        catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    public int delete(int a, int b){
        if(!aMatrix[a][b]){
            System.out.println("No such edge " + a + " " + b);
            return 1;
        }
        aMatrix[a][b] = false;
        aMatrix[b][a] = false;
        return 0;
    }

    public void deleteVertex(int a){
        for(int i=0; i<order; i++){
            aMatrix[a][i] = false;
        }
        for(int i=0; i<order; i++){
            aMatrix[i][a] = false;
        }
    }

    /*
        delete the vertex and generate a new graph without the vertex
    */
    public GraphBoth deleteV(int a) {
        GraphBoth r = new GraphBoth(order - 1); // construct a new graph with the order decrease 1
        for (int i = 0; i < r.order; i++) {
            for (int m = 0; m < r.order; m++) {
                int a1, a2;
                a1 = i >= a ? i + 1 : i; // pass the row which equal to a(the vertex need to delete)
                a2 = m >= a ? m + 1 : m; // pass the column which equal to a
                r.aMatrix[i][m] = aMatrix[a1][a2];
            }
        }
        return r;
    }

    public boolean isAdjacent(int a, int b) {
        return aMatrix[a][b];
    }

    public int degree(int a) {
        int d = 0;
        for(int i = 0; i < order; i++)
            d += aMatrix[a][i]?1:0;
        return d;
    }

    public GraphBoth reduce(){
        int delete = 0;
        int solution = 0;
        boolean flag = true;
        while(flag) {
            flag = false;
            for (int i = 0; i < order; i++) {
                if (degree(i) == 2) {
                    int n1 = 0;
                    int n2 = 0;
                    for(int m=0; m<order; m++){
                        if(aMatrix[i][m]){
                            n1 = m;
                            break;
                        }
                    }
                    for(int m=order-1; m>=0; m--){
                        if(aMatrix[i][m]){
                            n2 = m;
                            break;
                        }
                    }
                    if (isAdjacent(n1, n2)) {
                        delete(i, n1);
                        delete(i, n2);
                        deleteVertex(n1);
                        deleteVertex(n2);
                        delete++;
                        solution += 2;
                    }
                    else {
                        for(int m=0; m<order; m++){
                            if(aMatrix[n2][m]){
                                aMatrix[n1][m] = true;
                                aMatrix[m][n1] = true;
                            }
                        }
                        delete(n1, i);
                        delete(n2, i);
                        deleteVertex(n2);
                    }
                    flag = true;
                }
                else if(degree(i) == 1){
                    int n1 = 0;
                    for(int m=0; m<order; m++){
                        if(aMatrix[i][m]){
                            n1 = m;
                            break;
                        }
                    }
                    delete(i, n1);
                    delete++;
                    solution++;
                    deleteVertex(n1);
                    flag = true;
                }
            }
        }
        System.out.println(delete + " vertices have been deleted.");
        System.out.println(solution + " vertices have been put into the solution.");
        return null;
    }

    public GraphBoth reducea(){
        GraphBoth g = this;
        int delete = 0;
        int solution = 0;
        boolean flag = true;
        while(flag) { //loop until the graph can't reduce
            flag = false;
            for (int i = 0; i<g.order; i++) {
                if (g.degree(i) == 2) { //if there is a vertex of degree 2 neighbor
                    int n1 = 0;
                    int n2 = 0;
                    for(int m=0; m<g.order; m++){ //find the two
                        if(g.aMatrix[i][m]){
                            n1 = m;
                            break;
                        }
                    }
                    for(int m=g.order-1; m>=0; m--){
                        if(g.aMatrix[i][m]){
                            n2 = m;
                            break;
                        }
                    }
                    if (g.isAdjacent(n1, n2)) { //if the two neighbor is adjacent
                        g = g.deleteV(i);// delete vertex i and return a new graph without i.
                        g = g.deleteV(n1); //delete two neighbor
                        g = g.deleteV(n2);
                        delete++;
                        solution += 2;
                    }
                    else { //if not adjacent
                        for(int m=0; m<g.order; m++){ //merge two neighbor to one(the first vertex) vertex
                            if(g.aMatrix[n2][m]) {
                                g.aMatrix[n1][m] = true;
                                g.aMatrix[m][n1] = true;
                            }
                        }
                        g = g.deleteV(i); //delete i
                        g = g.deleteV(n2); //delete neighbor which is not the new merge vertex
                        delete++;
                        solution++;
                    }
                    flag = true; // change the flag
                }
                else if(g.degree(i) == 1){ //if there is a vertex of degree one
                    int n1 = 0;
                    for(int m=0; m<g.order; m++){//find the neighbor
                        if(g.aMatrix[i][m]){
                            n1 = m;
                            break;
                        }
                    }
                    g = g.deleteV(i);//delete i
                    delete++;
                    solution++;
                    g = g.deleteV(n1);//delete neighbor
                    flag = true;
                }
                else if(g.degree(i) == 0){//if a vertex is of degree one
                    delete++;
                    g = g.deleteV(i);
                    flag = true;
                }
            }
        }
        System.out.println(delete + " vertices have been deleted.");
        System.out.println(solution + " vertices have been put into the solution.");
        return g;
    }

    public static GraphBoth reducer(GraphBoth g){
        int delete = 0;
        int solution = 0;
        for (int i = 0; i < g.order; i++) {
                if (g.degree(i) == 2) {
                    int n1 = 0;
                    int n2 = 0;
                    for(int m=0; m<g.order; m++){
                        if(g.aMatrix[i][m]){
                            n1 = m;
                            break;
                        }
                    }
                    for(int m=g.order-1; m>=0; m--){
                        if(g.aMatrix[i][m]){
                            n2 = m;
                            break;
                        }
                    }
                    if (g.isAdjacent(n1, n2)) {
                        g.delete(i, n1);
                        g.delete(i, n2);
                        g.deleteVertex(n1);
                        g.deleteVertex(n2);
                        delete++;
                        solution += 2;
                    }
                    else {
                        for (int m = 0; m < g.order; m++) {
                            if (g.aMatrix[n2][m]) {
                                g.aMatrix[n1][m] = true;
                                g.aMatrix[m][n1] = true;
                            }
                        }
                        g.delete(n1, i);
                        g.delete(n2, i);
                        g.deleteVertex(n2);
                    }
                }
                else if(g.degree(i) == 1){
                    int n1 = 0;
                    for(int m=0; m<g.order; m++){
                        if(g.aMatrix[i][m]){
                            n1 = m;
                            break;
                        }
                    }
                    g.delete(i, n1);
                    delete++;
                    solution++;
                    g.deleteVertex(n1);
                }
            }
        System.out.println(delete + " vertices have been deleted.");
        System.out.println(solution + " vertices have been put into the solution.");
        return null;
    }

    public static void main(String[] args){
        GraphBoth a;
        a = importGraph("008");
        System.out.println("order of new graph " + a.order);
        System.out.println("The import graph is 024");
        a = a.reducea();
        System.out.println("order of new graph " + a.order);
        System.out.println("the output: ");
        //System.out.println(a.aMatrix[0][0]);
    }

}
