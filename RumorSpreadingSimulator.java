
import java.util.ArrayList;
import java.util.Scanner;

public class RumorSpreadingSimulator {
    private static Scanner sc = new Scanner(System.in);

    public static void run() {
        ArrayList<Vertex<String, Integer>> people = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            people.add(new Vertex<>(i + ""));
        }

        people.get(0).addFriends("2", "7");
        people.get(1).addFriends("1", "3", "5", "6");
        people.get(2).addFriends("2");
        people.get(3).addFriends("8", "10");
        people.get(4).addFriends("2");
        people.get(5).addFriends("2");
        people.get(6).addFriends("1");
        people.get(7).addFriends("4");
        people.get(8).addFriends("10");
        people.get(9).addFriends("4", "9");

        System.out.print("Enter your crush's ID: ");
        int crushID = sc.nextInt();
        Vertex<String, Integer> crush = people.get(crushID - 1);

        System.out.print("Enter the stranger's ID: ");
        int strangerID = sc.nextInt();
        while (crush.friends.contains(strangerID + "")) {
            System.out.println("\nCrush and stranger must be in different cluster!");
            System.out.print("Enter stranger ID: ");
            strangerID = sc.nextInt();
        }
        Vertex<String, Integer> stranger = people.get(strangerID - 1);

        System.out.println();
        if(!isConnected(crushID, strangerID)){
            System.out.println("The rumor is impossible to be delivered to your crush.");
        }
        else{
            if(crush.friends.size()==1){
                System.out.println("You can prevent your crush from knowing about the rumor by convincing: ");
                System.out.println("Person ID: "+crush.friends.get(0));
            }
            else if(stranger.friends.size()==1){
                System.out.println("You can prevent your crush from knowing about the rumor by convincing: ");
                System.out.println("Person ID: "+stranger.friends.get(0));
            }
            else{
                //Never reach here based on the friendship graph in question.
                System.out.println("You cannot prevent your crush from knowing about the rumor.");
            }
        }
    }

    public static boolean isConnected(int crushID, int strangerID){
        if(crushID<=7 && crushID!=4){
            if(strangerID<=7 && strangerID!=4){
                return true;
            }
            return false;
        }
        else{
            if(strangerID<=7 && strangerID!=4){
                return false;
            }
            return true;
        }
    }
}
