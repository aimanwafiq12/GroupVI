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
        firstEdge = null;
        friends = new ArrayList<>();

        //Initialize these variables for each student as below:
        // 1 <= rep <= 10,
        // 0 < Diving rate (%) < 100,
        dive = r.nextInt(100)+1;

        // 1100 <= lunch time <= 1400,
        lunchStart = generateTime();
        int a = r.nextInt(60+1-5)+5;
        while(a%10!=0){
            a = r.nextInt(60+1-5)+5;
        }
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
        Boolean check = false;
        int a=0;
        while(!check){
            a = r.nextInt(1400+1-1100) + 1100;
            if(a>=1160&&a<1200){
                continue;
            }
            else if(a>=1260&&a<1300){
                continue;
            }
            else if(a>=1360&&a<1400){
                continue;
            }
            else if(a%10!=0){
                continue;
            }
            else
                break;
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
}
