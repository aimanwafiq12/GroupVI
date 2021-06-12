package com.GroupProjectAssignment;

import java.util.ArrayList;
import java.util.Random;

public class Vertex <T extends Comparable<T>, N extends Comparable <N>> {
    T vertexInfo;
    int indeg;
    int outdeg;
    Vertex<T,N> nextVertex;
    Edge<T,N> firstEdge;
    int rep = 1;
    int dive;
    int lunchStart;
    int lunchPeriod;
    ArrayList<String> friends;


    Random r = new Random();

    public Vertex() {
        vertexInfo=null;
        indeg=0;
        outdeg=0;
        nextVertex = null;
        firstEdge = null;
        friends = new ArrayList<>();
    }

    public Vertex(T vInfo){
        vertexInfo = vInfo;
        indeg = 0;
        outdeg = 0;
        nextVertex = null;
        firstEdge = null;
        friends = null;
        friends = new ArrayList<>();

    }

    public Vertex(T vInfo, Vertex<T,N> next) {
        vertexInfo = vInfo;
        indeg=0;
        outdeg=0;
        nextVertex = next;
        friends = null;
        friends = new ArrayList<>();

        //Initialize these variables for each student as below:
        // 1 <= rep <= 10,
        // 0 < Diving rate (%) < 100,
        dive = r.nextInt((100-1)+1)+1;

        // 1100 <= lunch time <= 1400,
        // call the generateTime method
        lunchStart = generateTime();

        // to generate lunch period; minimum time period generated is 10 ; maximum is 60
        int a = r.nextInt(60-5)+5;
        // this is to generate period time of 5, 10, 15, 20, 25, until 60 with interval of 5
        while(a%10!=0 && a%10!=5){
            a = r.nextInt(60-5)+5;
        }
        //to get the LunchEnd time
        int afterPeriod = a + lunchStart;
        if(afterPeriod>1360){
            lunchPeriod = 1360 - lunchStart;
        }
        else{
            lunchPeriod = a;
        }
        // 5 < lunch period(minutes) < 60,
        // a list of friends as shown in Figure 1 below with their reputation relative to this person.
    }

    public int generateTime(){
        Boolean check = true;
        int a=0;
        a = r.nextInt(1350+1-1100) + 1100;

        while(check){
            if(a >= 1160 && a < 1200 || a >= 1260 && a < 1300 || a % 10 != 0 && a % 10 != 5){
                a = r.nextInt(1350+1-1100) + 1100;
                check = true;
            } else{
                check = false;
                return a;
            }
        }
        return a;
    }

    public int totalRep(int a){
        rep = rep + a;
        return rep;
    }

    public boolean addFriend(String name){
        if(friends.contains(name)){
            return false;
        }
        friends.add(name);
        return true;
    }

    public void addFriends(String... names){
        for(String name: names){
            addFriend(name);
        }
    }

    public boolean equals(Vertex<T, N> v) {
        return vertexInfo.compareTo(v.vertexInfo)==0;
    }

    /**
     * Fix the time when it is 1360 instead of 1400 and etc
     * @param time
     * @return the fixed time
     */
    public int fixTime(int time) {
        if (time>=1160 && time<=1210) {
            time = time + 40;
        }
        else if (time>=1260 && time<= 1310) {
            time = time + 40;
        }
        else if (time>=1360 && time<= 1410 ) {
            time = 1400;
        }
        else if (time>=1460 && time<= 1510) {
            time = 1400;
        }
        return time;
    }
}