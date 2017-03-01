import java.util.Arrays;
import java.util.stream.Collectors;


public class GraphInLists extends Graph{
    private int order;  // the number of vertices
    private List[] aLists;
    
    public GraphInLists(int order) {
        this.order = order;
    	aLists = new List[order];
    	for (int i = 0; i < order; i++)
    		aLists[i] = new List();
    }

    public GraphInLists(boolean[][] aMatrix) {
        this(aMatrix.length);
        for(int i=0;i<order;i++){
            for(int j = i+1;j<order;j++){
                if(aMatrix[i][j]) addEdge(i, j);
            }
        }
    	//
    }
    
    // This version doesn't check whether the edge is arealdy there.
    // It's not a problem for matrix version. Why?
    public void addEdge(int a, int b) {
        // usually we don't allow self-loop 
        // i.e., an edge with both ends on the same vertex
        if (a == b) return;
        aLists[a].insertAtFront(b);
        aLists[b].insertAtFront(a);
    }

    public void addEdge2(int a, int b){
        if(a == b) return;
        if(aLists[a].search(b) != null) return;
        aLists[a].insertAtFront(b);
        aLists[b].insertAtFront(a);
    }

    public boolean isAdjacent(int a, int b) {
    	List list = aLists[a];
    	return (list.search(b) != null);
    }

    // return the degree of vertex a.
    public int degree(int a) {
    	return aLists[a].length();
    }

    // return the total number of edges in the graph.
    public int size() {
    	int s = 0;
        for(int i = 0; i < order; i++)
        	s += degree(i);
        return s / 2;
    }

    // check whether the graph contains a triangle
    // i.e., three vertices pairwise adjacent
    public boolean triangle() {
    	//
        for(int i = 0; i<order; i++){
            for(int m = 0; m<order;m++){
                if(isAdjacent(i,m)){
                    for(int j = 0; j<order;j++){
                        if(isAdjacent(m,j)){
                            if(isAdjacent(i,j)) return true;
                        }
                    }
                }
            }
        }
    	return false;
    }
    
    public void bfs() { bfs(0); }

    // breadth-first search
    public void bfs(int a) {
    	//
    }

    public int vnum(){
        boolean[] visit = new boolean[this.order];
        IntQueue a = new IntQueue();
        visit[0] = true;
        a.enqueue(0);
        while(!a.isEmpty()){
            int u = a.dequeue();
            Node head = aLists[u].getHead();
            while(head != null){
                if(!visit[head.data]) {
                    visit[head.data] = true;
                    a.enqueue(head.data);
                }
                head = head.next;
            }
        }
        int count = 0;
        for(int i=0; i<this.order; i++)
            if(visit[i]) count++;
        return count;
    }

    // depth-first search
    public void dfs(int a) {
    }

    // print out the adjacency list
    public void display() {
    	for (int i = 0; i < order; i++) {
    		System.out.println(aLists[i]);
    	}
    }


    public GraphInLists square() {
        GraphInLists sq = new GraphInLists(this.order);
        for(int i=0; i<order; i++){
            for(int j=0; j<order; j++){
                if(isAdjacent(i,j)) sq.addEdge2(i,j);
                else{
                    for(int m =0; m< order; m++){
                        if(isAdjacent(j, m) && isAdjacent(m, i)){
                            sq.addEdge2(i,j);
                        }
                    }
                }
            }
        }
        return sq;
    }

    public int[] eulerian(){
        int size = this.size() + 1;
        int[] ecycle = new int[size];
        int num = 0;
        for(int i=0; i<this.order; i++){
            if(degree(i) % 2 == 1){
                ecycle[0] = -1;
                return ecycle;
            }
        }

        ecycle[0] = 0;
        while(num < size-1) {
            Node head = aLists[ecycle[num]].getHead();
            if (head.next == null) {
                num++;
                ecycle[num] = head.data;
                System.out.println(ecycle[num - 1] + " to " + ecycle[num]);
                int a = aLists[ecycle[num - 1]].delete(ecycle[num]);
                int b = aLists[ecycle[num]].delete(ecycle[num - 1]);
            }
            else {
                while (head != null) {
                    int originV = this.vnum();
                    aLists[ecycle[num]].delete(head.data);
                    aLists[head.data].delete(ecycle[num]);
                    int afterV = this.vnum();
                    if (originV == afterV) {
                        num++;
                        ecycle[num] = head.data;
                        break;
                    }
                    head = head.next;
                }
                aLists[ecycle[num - 1]].delete(ecycle[num]);
                aLists[ecycle[num]].delete(ecycle[num - 1]);
            }
        }
        return ecycle;
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
        GraphInLists graph1 = new GraphInLists(m1);
        GraphInLists graph2 = graph1.square();
        System.out.println("Origin: ");
        for(int i=0; i<graph1.order; i++){
            System.out.println(i + ": " + graph1.aLists[i]);
        }
        System.out.println("Square: ");
        for(int i=0; i<graph2.order; i++){
            System.out.println(i + ": " + graph2.aLists[i]);
        }

        GraphInLists g = new GraphInLists(8);
        g.addEdge(0,2);
        g.addEdge(2,1);
        g.addEdge(1,0);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(4,5);
        g.addEdge(5,2);
        g.addEdge(4,6);
        g.addEdge(6,7);
        g.addEdge(7,4);;
        System.out.println("Origin:");
        for(int i=0; i<g.order; i++){
            System.out.println(i + ": " + g.aLists[i]);
        }
        int[] result = g.eulerian();
        System.out.println("Eulerian route:");
        if(result[0] == -1) System.out.println("No eulerian route.");
        else System.out.println(Arrays.toString(result));
    }
}
