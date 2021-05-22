
import java.util.Scanner;


public class FriendshipCalculator{
    
    static int total = 0;
    static int size;
    static int[][] graph;
    
    public FriendshipCalculator() {
    }
    
    public static int run(){
        Scanner sc = new Scanner(System.in);
        
        size = sc.nextInt();
        
        graph = new int[size][size];
        for (int i = 0; i < size; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x-1][y-1] = 1;
            graph[y-1][x-1] = 1;
        }
        modifiedDfs();
        return (total/2);
    }
    
    public static void modifiedDfsUtil(int v, boolean visited[], int targetSize, int currentSize){
        if(currentSize==targetSize){
            total++;
            return;
        }
        for (int i = 0; i < size; i++) {
            if(!visited[i] && graph[v][i]==1){
                visited[v] = true;
                modifiedDfsUtil(i, visited, targetSize, currentSize+1);
                visited[v] = false;
            }
        }
    }
    
    public static void modifiedDfs(){
        boolean visited[] = new boolean[size];
        for (int i = 2; i <=size; i++) {
            for (int j = 0; j < size; j++) {
                modifiedDfsUtil(j, visited, i, 1);
            }
        }
    }
}
