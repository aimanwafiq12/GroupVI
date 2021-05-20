
import java.util.Scanner;


public class Event6 {
    
    static int total = 0;
    static int size;
    static int[][] graph;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        size = sc.nextInt();
        
        graph = new int[size][size];
        for (int i = 0; i < size; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x-1][y-1] = 1;
            graph[y-1][x-1] = 1;
        }
        
        dfs();
        System.out.println("Output: "+(total/2));
    }
    
    public static void dfsUtil(int v, boolean visited[], int targetSize, int currentSize){
        if(currentSize==targetSize){
            total++;
            return;
        }
        for (int i = 0; i < size; i++) {
            if(!visited[i] && graph[v][i]==1){
                visited[v] = true;
                dfsUtil(i, visited, targetSize, currentSize+1);
                visited[v] = false;
            }
        }
    }
    
    public static void dfs(){
        boolean visited[] = new boolean[size];
        for (int i = 2; i <=size; i++) {
            for (int j = 0; j < size; j++) {
                dfsUtil(j, visited, i, 1);
            }
        }
    }
}
