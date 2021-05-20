
import java.util.ArrayList;
import java.util.Scanner;


public class Event4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        ArrayList<Integer> heights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            heights.add(sc.nextInt());
        }
        
        int round = 0;
        boolean done = false;
        while(!done){
            done = true;
            int left = heights.get(0);
            for(int i=1; i<heights.size(); i++){
                int current = heights.get(i);
                if(current>left){
                    heights.remove(i);
                    i--;
                    done = false;
                }
                left = current;
            }
            if(!done){
                round++;
            }
        }
        
        System.out.println(round);
    }
}
