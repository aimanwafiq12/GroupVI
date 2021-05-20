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
                    System.out.println("In construction...");
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
            System.out.print("Do you want to continue with other option? Enter any number to continue and '0' to exit: ");
            int exit = s.nextInt();
            if(exit==0){
                operating = false;
                break;
            }
        }

    }
}
