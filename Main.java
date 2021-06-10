package com.GroupProjectAssignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("\n---------------------HELLO USER--------------------");
        System.out.println("\n---------------WELCOME TO THE SOCIOPATH---------------\n");
        //Generate a group of students (10 of them) each with reputation (rep), diving rate (dive), lunch
        //starting time (lunchStart), lunch period (lunchPeriod), and friends (friends).
        String [] studentsName = {null, "Bala", "Chen", "David", "Ella", "Fong", "Grealish","Harry", "Irene", "Joshua"};

        Scanner s = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        studentsName[0] = s.nextLine();

        WeightedGraph<String> students = new WeightedGraph();
        // add vertex of each students in the graph
        for(String a : studentsName) {
            students.addVertex(a);
        }

        // to randomly generate students' friends
        Random r = new Random();
        for(int i=0;i<studentsName.length;i++){
            //to generate no. of friends the current student have
            int a = r.nextInt(2)+1;
            ArrayList<Integer> check = new ArrayList<>();
            for(int j=0;j<a;j++){
                // to generate a random friend via student ID
                int b = r.nextInt(10);
                // c & d are to generate random rep points
                int c = r.nextInt(10)+1;
                int d = r.nextInt(10)+1;
                // if the random student ID generated is the same as current student OR the edge from Current Student to Student B has created
                // don't add as friend again
                if(b==i || students.hasEdge(studentsName[i], studentsName[b])){
                    continue;
                }
                students.addEdge(studentsName[i], studentsName[b], c);
                students.addEdge(studentsName[b], studentsName[i], d);
            }
        }

        System.out.println();
        System.out.println(studentsName[0]+", your current details are as below:");
        students.printSpecificEdges(studentsName[0]);
        boolean operating = true;
        while(operating){
            System.out.println("\n******************* THE MAIN PAGE *********************");
            System.out.println("\nWhat do you want to do?");

            System.out.println("1) Show my current profile details.");
            System.out.println("2) Show other student's profile details.");
            System.out.println("3) Display all students friendship list.");
            System.out.println("4) Check out events available.");
            System.out.println("\n'0' to exit.\n");
            System.out.print("Enter your option (0-4): ");
            int option = s.nextInt();
            System.out.println();

            switch(option){
                case 1:
                {
                    System.out.println("***************** OPTION 1 ******************" +
                            "\n\nBelow are your current profile details:");
                    System.out.println("Name: " + studentsName[0]);
                    students.printSpecificEdges(studentsName[0]);
                    System.out.println("********* Thank you for choosing option 1 **********\n");
                    break;
                }
                case 2:
                {
                    System.out.println("***************** OPTION 2 ******************");

                    System.out.print("Enter name of student to be checked: ");
                    s.nextLine();
                    String check = s.nextLine();
                    students.printSpecificEdges(check);
                    System.out.println("********* Thank you for choosing option 2 **********\n");
                    break;
                }
                case 3:
                {
                    System.out.println("***************** OPTION 3 ******************");
                    System.out.println("All 10 students' friendship list\n");
                    students.printEdges();
                    System.out.println("\n********* Thank you for choosing option 3 **********\n");
                    break;
                }

                case 4:{
                    System.out.println("\n\n********************** THE EVENTS ***********************\n");

                    System.out.println("Which Event do you want to do?");
                    System.out.println("1) Event 1 - Teaching a stranger to solve lab questions.");
                    System.out.println("2) Event 2 - Chit-chat.");
                    System.out.println("3) Event 3 - Your road to glory.");
                    System.out.println("4) Event 4 - Arranging books.");
                    System.out.println("5) Event 5 - Meet your crush.");
                    System.out.println("6) Event 6 - Friendship.");
                    System.out.println("\n'0' back to THE MAIN PAGE and EXIT PAGE.");
                    System.out.println("\nEnter your option (0-6):");
                    int option2 = s.nextInt();

                    switch (option2){
                        case 1:
                        {
                            System.out.println("\n**************** EVENT 1: TEACHING A STRANGER ****************");
                            System.out.println("\nWho is teaching?");
                            s.nextLine();
                            String teacherName = s.nextLine();
                            System.out.println("Who did the person teach?");
                            String studentName = s.nextLine();
                            System.out.println("Does the student you teach had good experience?(true/false)");
                            boolean goodExp = s.nextBoolean();
                            teach(teacherName,studentName,goodExp,students);

                            System.out.println("\n------------Updated "+teacherName+"'s Current Details --------------");
                            System.out.println("Name: " + teacherName);
                            students.printSpecificEdges(teacherName);
                            System.out.println("NOTE: "+studentName+" is added to "+teacherName+"'s friends list.\n");
                            System.out.println("*************** THANK YOU FOR CHOOSING EVENT 1 ****************\n");

                            break;
                        }
                        case 2:
                        {
                            System.out.println("\n******************* EVENT 2: CHIT-CHAT *******************");

                            System.out.println("Who is the teller?");
                            s.nextLine();
                            String teller = s.nextLine();
                            System.out.println("Who is receiving?");
                            String receiver = s.nextLine();
                            System.out.println("Who are they talking about?");
                            String about = s.nextLine();
                            System.out.println("Is it a good message?(true/false)");
                            boolean goodMsg = s.nextBoolean();
                            chat(teller,receiver,about,goodMsg,students);

                            System.out.println("\n------------Updated "+about+"'s Current Details --------------");
                            System.out.println("Name: " + about);
                            students.printSpecificEdges(about);
                            System.out.println("\n*************** THANK YOU FOR CHOOSING EVENT 2 ****************\n");

                            break;
                        }
                        case 3:
                        {
                            System.out.println("\n******************* EVENT 3: ROAD TO GLORY *******************");

                            System.out.println("This is Event 3: Road to Glory.");
                            roadToGlory(students);

                            System.out.println("\n*************** THANK YOU FOR CHOOSING EVENT 3 ****************\n");

                            break;
                        }

                        case 4:
                        {
                            System.out.println("\n******************* EVENT 4: ARRANGING BOOKS *******************");

                            System.out.println("\nNumber of rounds needed to make the height of the books in non-increasing order: "+arrangeBook());

                            System.out.println("\n*************** THANK YOU FOR CHOOSING EVENT 4 ****************\n");

                            break;
                        }

                        case 5: {
                            System.out.println("\n******************* EVENT 5: MEET YOUR CRUSH *******************");

                            System.out.println("\nThis is Event 5: Meet Your Crush" +
                                    "\nYou have a crush on a person and you can't stop thinking about your crush." +
                                    "\nYou must stop the rumour of you having a crush on the person before your crush knows about it.");
                            meetCrush();

                            System.out.println("\n*************** THANK YOU FOR CHOOSING EVENT 5 ****************\n");

                            break;
                        }

                        case 6:
                        {
                            System.out.println("\n******************* EVENT 6: FRIENDSHIP *******************");

                            System.out.println(formFriendship());

                            System.out.println("\n*************** THANK YOU FOR CHOOSING EVENT 6 ****************\n");
                            break;
                        }
                        case 0:
                        {
                            operating = false;
                            break;
                        }
                        default:
                            System.out.println("YOU ENTERED YOUR OPTION WRONGLY!!!");
                            System.out.println("Please enter the CORRECT OPTION number.");
                    }
                    break;
                }
                case 0:
                {
                    operating = false;
                    break;
                }
                default:
                    System.out.println("YOU ENTERED YOUR OPTION WRONGLY!!!");
                    System.out.println("Please enter the CORRECT OPTION number.");
            }
            System.out.print("\n************* OPTIONS *****************" +
                    "\n0) EXIT" +
                    "\n1) BACK TO THE MAIN PAGE" +
                    "\n\nEnter your option:");
            int exit = s.nextInt();
            if(exit==0) {
                System.out.println("\n---------------------- THANK YOU FOR USING THE SOCIOPATH ----------------------");
                System.out.println("\n----------------------------- SEE YOU AGAIN!!! --------------------------------");

                operating = false;
                break;

            }else{
                operating = true;
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
