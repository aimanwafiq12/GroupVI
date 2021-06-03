package com.GroupProjectAssignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("\n---------------WELCOME TO THE SOCIOPATH---------------\n");
        //Generate a group of students (10 of them) each with reputation (rep), diving rate (dive), lunch
        //starting time (lunchStart), lunch period (lunchPeriod), and friends (friends).
        String [] studentsName = {null, "Bala", "Chen", "David", "Ella", "Fong", "Grealish","Harry", "Irene", "Joshua"};

        Scanner s = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        studentsName[0] = s.nextLine();

        WeightedGraph<String> students = new WeightedGraph();
        for(String a : studentsName)
            students.addVertex(a);
        Random r = new Random();
        for(int i=0;i<studentsName.length;i++){
            int a = r.nextInt(2)+1;
            ArrayList<Integer> check = new ArrayList<>();
            for(int j=0;j<a;j++){
                int b = r.nextInt(10);
                int c = r.nextInt(10)+1;
                int d = r.nextInt(10)+1;
                if(check.contains(b)||b==i)
                    continue;
                check.add(b);
                students.addEdge(studentsName[i], studentsName[b], c);
                students.addEdge(studentsName[b], studentsName[i], d);
            }
        }
        System.out.println("\nThese are your current details, "+studentsName[0]+":");
        students.printSpecificEdges(studentsName[0]);
        System.out.println("What do you want to do? Enter the option number below: ");
        Boolean operating = true;
        while(operating){
            System.out.println("1) Show my current profile details.");
            System.out.println("2) Show other student's profile details.");
            System.out.println("3) Display all students friendship list.");
            System.out.println("4) Check out events available.");
            System.out.println("\nEnter '0' to exit.\n");
            System.out.print("Option: ");
            int option = s.nextInt();
            System.out.println();

            switch(option){
                case 1:
                {
                    System.out.println("Name: " + studentsName[0]);
                    students.printSpecificEdges(studentsName[0]);
                    System.out.println("Option 1 completed.\n");
                    break;
                }
                case 2:
                {
                    System.out.print("Enter name of student to be checked: ");
                    s.nextLine();
                    String check = s.nextLine();
                    students.printSpecificEdges(check);
                    System.out.println("Option 2 completed.\n");
                    break;
                }
                case 3:
                {
                    System.out.println("Checking all students profile...\n");
                    students.printEdges();
                    System.out.println("Option 3 completed.\n");
                    break;
                }

                case 4:{
                    System.out.println("Which Event do you want to do?");
                    System.out.println("1) Event 1 - Teaching a stranger to solve lab questions.");
                    System.out.println("2) Event 2 - Chit-chat.");
                    System.out.println("3) Event 3 - Your road to glory.");
                    System.out.println("4) Event 4 - Arranging books.");
                    System.out.println("5) Event 5 - Meet your crush.");
                    System.out.println("6) Event 6 - Friendship.");
                    System.out.println("\nEnter '0' to exit.");
                    System.out.println("\nEnter your option by number:");
                    int option2 = s.nextInt();

                    switch (option2){
                        case 1:
                        {
                            System.out.println("Who is teaching?");
                            s.nextLine();
                            String teacherName = s.nextLine();
                            System.out.println("Who did the person teach?");
                            String studentName = s.nextLine();
                            System.out.println("Does the student you teach had good experience?(true/false)");
                            boolean goodExp = s.nextBoolean();
                            teach(teacherName,studentName,goodExp,students);

                            System.out.println("Below is the teacher's current details:");
                            System.out.println("Name: " + teacherName);
                            students.printSpecificEdges(teacherName);
                            System.out.println("\nEvent 1 completed.\n");

                            break;
                        }
                        case 2:
                        {
                            System.out.println("Who is the teller?");
                            s.nextLine();
                            String teller = s.nextLine();
                            System.out.println("Who is receiving?");
                            String receiver = s.nextLine();
                            System.out.println("Who are they talking about?");
                            String about = s.nextLine();
                            System.out.println("Does the student you teach had good experience?(true/false)");
                            boolean goodMsg = s.nextBoolean();
                            chat(teller,receiver,about,goodMsg,students);

                            System.out.println("\nBelow is the person they are chatting about current details:");
                            System.out.println("Name: " + about);
                            students.printSpecificEdges(about);
                            System.out.println("Event 2 completed.\n");

                            break;
                        }
                        case 3:
                        {
                            System.out.println("This is Event 3: Road to Glory.");
                            roadToGlory(students);
                            break;
                        }

                        case 4:
                        {
                            System.out.println("\nNumber of rounds needed to make the height of the books in non-increasing order: "+arrangeBook());
                            break;
                        }

                        case 5: {
                            System.out.println("In progress...");
                            break;
                        }

                        case 6:
                        {
                            System.out.println(formFriendship());
                            break;
                        }
                        case 0:
                        {
                            operating = false;
                            break;
                        }
                        default:
                            System.out.println("Please enter the right option number.");
                    }
                    break;
                }
                case 0:
                {
                    operating = false;
                    break;
                }
                default:
                    System.out.println("Please enter the right option number.");
            }
            System.out.print("\nDo you want to continue with other option? Enter any number to continue and '0' to exit: ");
            int exit = s.nextInt();
            if(exit==0){
                operating = false;
                break;
            }
        }
    }
    /**
     * Event 1
     * @param teacherName   (Name of the student who teach)
     * @param studentName   (Name of the student who learn)
     * @param goodExp       (True if it is good else false)
     * @param students      (The students graph)
     * @return True if the method runs successfully else false.
     */
    public static boolean teach(String teacherName, String studentName, boolean goodExp, WeightedGraph<String> students){
        if(!students.hasVertex(teacherName) || !students.hasVertex(studentName)){
            return false;
        }
        Vertex<String, Integer> teacher = students.getVertexObject(teacherName);
        teacher.addFriend(studentName);
        if(students.hasEdge(teacherName, studentName)){
            Edge<String, Integer> edge = students.getEdgeObject(teacherName, studentName);
            edge.weight += goodExp? 10: 2;
        }
        else{
            if(goodExp){
                students.addEdge(teacherName, studentName, 10);
            }
            else{
                students.addEdge(teacherName, studentName, 2);
            }
        }
        return true;
    }

    /**
     * Event 2
     * @param teller    (The student who tell the message)
     * @param receiver  (The student who receive the message)
     * @param about     (The student who is talked about)
     * @param goodMsg   (True if it is good message else false)
     * @param students  (The students graph)
     * @return True if the method runs successfully else false.
     */
    public static boolean chat(String teller, String receiver, String about, boolean goodMsg, WeightedGraph<String> students){
        if(!students.hasVertex(teller)|| !students.hasVertex(receiver) || !students.hasVertex(about)){
            return false;
        }
        else if(!students.hasEdge(about, teller)){
            return false;
        }
        int repPoint = students.getEdgeWeight(about, teller);
        repPoint = goodMsg? (int)(repPoint*0.5): repPoint*-1;
        if(students.hasEdge(about, receiver)){
            Edge<String, Integer> edge = students.getEdgeObject(about, receiver);
            edge.weight += repPoint;
        }
        else{
            students.addEdge(about, receiver, repPoint);
        }
        return true;
    }
    
    /**
     * Event 3
     * @param students 
     * Check if the user can have lunch with the specified student(s) and return the total reputation gained
     * maximum student per table is 3, excludes the user
     */
    public static void roadToGlory(WeightedGraph<String> students) {
       Scanner sc = new Scanner(System.in);
       int reputation = 0;
       System.out.println("Enter number of students you want to hv lunch with: ");
        int n = sc.nextInt();

        ArrayList<String> studentList = new ArrayList<>();
        System.out.println("Enter their name(s): ");
        for (int i = 0; i <= n; i++) {
            studentList.add(sc.nextLine());
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(students.checkLunchTime(studentList.get(i)));
            if(students.checkRep(studentList.get(i)))
                reputation+=1;
            System.out.println();
        }
        System.out.print("Total reputation gained: " + reputation);
    }

    /**
     * Event 4
     * @return the number of rounds needed to arrange the books based on the input.
     */
    public static int arrangeBook() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of books you want to arrange: ");
        int n = sc.nextInt();

        ArrayList<Integer> heights = new ArrayList<>();
        System.out.println("Enter height of each book you are going to arrange: ");
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
        return round;
    }


    /**
     * Event 5
     */
    public static void meetCrush(){
        RumorSpreadingSimulator.run();
    }
	


    /**
     * Event 6
     * @return the number of ways to form friendship based on the input.
     */
    public static String formFriendship(){
        return FriendshipCalculator.run();
    }

}
