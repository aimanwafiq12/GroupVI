package com.GroupProjectAssignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class WeightedGraph <T extends Comparable<T>> {

    Vertex<T,Integer> head;
    int size;
    int tableSize;

    Random r = new Random();
    public WeightedGraph()	{
        head=null;
        size=0;
        tableSize = 0;
    }

    public void clear() {
        head=null;
    }

    public int getSize()   {
        return this.size;
    }

    public int getIndeg(T v)  {
        if (hasVertex(v)==true)	{
            Vertex<T,Integer> temp = head;
            while (temp!=null) {
                if ( temp.vertexInfo.compareTo( v ) == 0 )
                    return temp.indeg;
                temp=temp.nextVertex;
            }
        }
        return -1;
    }

    public int getOutdeg(T v)  {
        if (hasVertex(v)==true)	{
            Vertex<T,Integer> temp = head;
            while (temp!=null) {
                if ( temp.vertexInfo.compareTo( v ) == 0 )
                    return temp.outdeg;
                temp=temp.nextVertex;
            }
        }
        return -1;
    }

    public boolean hasVertex(T v)	{
        if (head==null)
            return false;
        Vertex<T,Integer> temp = head;
        while (temp!=null)	{
            if ( temp.vertexInfo.compareTo( v ) == 0 )
                return true;
            temp=temp.nextVertex;
        }
        return false;
    }

    public boolean addVertex(T v)	{
        if (hasVertex(v)==false)	{
            Vertex<T,Integer> temp=head;
            Vertex<T,Integer> newVertex = new Vertex<>(v, null);
            if (head==null)
                head=newVertex;
            else {
                Vertex<T,Integer> previous=head;;
                while (temp!=null)  {
                    previous=temp;
                    temp=temp.nextVertex;
                }
                previous.nextVertex=newVertex;
            }
            size++;
            return true;
        }
        else
            return false;
    }

    public int getIndex(T v) {
        Vertex<T,Integer> temp = head;
        int pos=0;
        while (temp!=null)	{
            if ( temp.vertexInfo.compareTo( v ) == 0 )
                return pos;
            temp=temp.nextVertex;
            pos+=1;
        }
        return -1;
    }

    public ArrayList<T> getAllVertexObjects() {
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,Integer> temp = head;
        while (temp!=null)	{
            list.add(temp.vertexInfo);
            temp=temp.nextVertex;
        }
        return list;
    }

    public ArrayList<Vertex<T,Integer>> getAllVertices() {
        ArrayList<Vertex<T,Integer>> list = new ArrayList<>();
        Vertex<T,Integer> temp = head;
        while (temp!=null)	{
            list.add(temp);
            temp=temp.nextVertex;
        }
        return list;
    }

    public T getVertex(int pos) {
        if (pos>size-1 || pos<0)
            return null;
        Vertex<T,Integer> temp = head;
        for (int i=0; i<pos; i++)
            temp=temp.nextVertex;
        return temp.vertexInfo;
    }

    public boolean addEdge(T source, T destination, int w)   {
        if (head==null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T,Integer> sourceVertex = head;
        while (sourceVertex!=null)	{
            if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
                // Reached source vertex, look for destination now
                Vertex<T,Integer> destinationVertex = head;
                while (destinationVertex!=null)	{
                    if ( destinationVertex.vertexInfo.compareTo( destination ) == 0 )   {
                        // Reached destination vertex, add edge here
                        Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
                        Edge<T,Integer> newEdge = new Edge<>(destinationVertex, w, currentEdge);
                        sourceVertex.firstEdge=newEdge;
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex=destinationVertex.nextVertex;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return false;
    }

    public boolean hasEdge(T source, T destination) {
        if (head==null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T,Integer> sourceVertex = head;
        while (sourceVertex!=null)	{
            if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
                // Reached source vertex, look for destination now
                Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        // destination vertex found
                        return true;
                    currentEdge=currentEdge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return false;
    }

    public int getEdgeWeight(T source, T destination) {
        int notFound=0;
        if (head==null)
            return notFound;
        if (!hasVertex(source) || !hasVertex(destination))
            return notFound;
        Vertex<T,Integer> sourceVertex = head;
        while (sourceVertex!=null)	{
            if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
                // Reached source vertex, look for destination now
                Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        // destination vertex found
                        return currentEdge.weight;
                    currentEdge=currentEdge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return notFound;
    }

    public ArrayList<T> getNeighbours (T v)  {
        if (!hasVertex(v))
            return null;
        ArrayList<T> list = new ArrayList<T>();
        Vertex<T,Integer> temp = head;
        while (temp!=null)	{
            if ( temp.vertexInfo.compareTo( v ) == 0 )   {
                // Reached vertex, look for destination now
                Edge<T,Integer> currentEdge = temp.firstEdge;
                while (currentEdge != null) {
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge=currentEdge.nextEdge;
                }
            }
            temp=temp.nextVertex;
        }
        return list;
    }

    public void printEdges()   {
        Vertex<T,Integer> temp=head;
        int n=1;
        while (temp!=null) {
            System.out.print("# "+ n +") "+ temp.vertexInfo + " : " );
            Edge<T,Integer> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
                System.out.print("[" + temp.vertexInfo + "," + currentEdge.toVertex.vertexInfo +"] " );
                currentEdge=currentEdge.nextEdge;
            }
            System.out.println();
            temp=temp.nextVertex;
            n++;
        }
    }

    public int getRep(T v){
        if (!hasVertex(v))
            return 0;
        Vertex<T,Integer> temp = head;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(v) == 0){
                return temp.rep;
            }
            temp = temp.nextVertex;
        }
        return 0;
    }

    public void printSpecificEdges(T v){
        if(!hasVertex(v)){
            System.out.println("Student " + v + " is not in the list!");
        }
        else{
            Vertex<T,Integer> temp = head;
            while(temp!=null){
                if(temp.vertexInfo.compareTo(v)==0){
                    System.out.println("Current reputation(rep): " + getRep(v));
                    System.out.println("Diving Rate(%): " + temp.dive + "\nLunch Starting Time: " + temp.lunchStart + "\nLunch Period(minutes): " + temp.lunchPeriod);
                    Edge<T,Integer> currentEdge = temp.firstEdge;
                    System.out.println("\nFriends List (reputation point):");
                    while (currentEdge != null) {
                        System.out.println(currentEdge.toVertex.vertexInfo + "(" + currentEdge.weight + ")" );
                        currentEdge=currentEdge.nextEdge;
                    }
                    System.out.println();
                }
                temp=temp.nextVertex;
            }
        }
    }

    public Vertex<T, Integer> getVertexObject(T info){
        if(!hasVertex(info)){
            return null;
        }
        Vertex<T, Integer> temp = head;
        while(temp != null){
            if(temp.vertexInfo.compareTo(info)==0){
                return temp;
            }
            temp = temp.nextVertex;
        }
        return null;
    }

    public Edge<T, Integer> getEdgeObject(T source, T destination){
        if (!hasVertex(source) || !hasVertex(destination)) {
            return null;
        }
        Vertex<T, Integer> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                // Reached source vertex, look for destination now
                Edge<T, Integer> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0){
                        return currentEdge;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return null;
    }

    /**
     * For Event 3
     * Check and display the lunch time for the specified student
     * s1 can only have lunch with s2 that have the same lunch time range with s1
     * @param s1    //the student who is inviting/checking their lunch time (User) /student1
     * @param s2    //the chosen student of s1 (other students) /student2
     * @return 
     */
    public String checkLunchTime(T s1, T s2) {
        int lunchEndStudent1 = 0;
        int lunchEndStudent2 = 0;
        String str;
        Vertex<T, Integer> temp1 = head;     //pointer for s1
        Vertex<T, Integer> temp2 = head;     //pointer for s2
        Vertex<T, Integer> student1 = head;
        Vertex<T, Integer> student2 = head;
        if (head==null) {
            return "No graph";
        }
        //Traverse through graph to find matching s1 and s2 vertex
        while (temp1!=null || temp2!=null) {
            if ( temp1.vertexInfo.compareTo(s1) == 0 ) {
                student1 = temp1;
                int lunchEndS1 = student1.lunchStart + student1.lunchPeriod;
                lunchEndStudent1 = student1.fixTime(lunchEndS1);
            }
            if (temp2.vertexInfo.compareTo(s2) == 0) {
                student2 = temp2;
                int lunchEndS2 = student2.lunchStart + student2.lunchPeriod;
                lunchEndStudent2 = student2.fixTime(lunchEndS2);
            }
            temp1 = temp1.nextVertex;
            temp2 = temp2.nextVertex;
        }
        if ((student2.lunchStart==student1.lunchStart || lunchEndStudent2==lunchEndStudent1) || (student2.lunchStart>=student1.lunchStart && lunchEndStudent2<=lunchEndStudent1) || (student2.lunchStart>student1.lunchStart && student2.lunchStart<lunchEndStudent1) || (lunchEndStudent2>student1.lunchStart && lunchEndStudent2<lunchEndStudent1) && tableSize<=3) {
                    student1.totalRep(1);   //student1 gained 1 rep point
                    tableSize++;        //table is occupied with 1 student excluding student1
                    str = "You can have lunch with \n#" + s2 + "\nLunchStart: " + student2.lunchStart + "\nLunchEnd: " + student2.fixTime(lunchEndStudent2) + "\nLunchPeriod: " + student2.lunchPeriod + "\nReputation +1";
                }
                else {
                    str = "You cannot have lunch with " + s2 + "\nLunchStart: " + student2.lunchStart + "\nLunchEnd: " + student2.fixTime(lunchEndStudent2) + "\nLunchPeriod: " + student2.lunchPeriod;
                }
        return str;
    }

    /**
     * Related to Event 3
     * @param s1    //student1
     * @param s2    //student2
     * @return true if the s1 can have lunch together with s2
     */
    public boolean checkRep(T s1, T s2) {
        int lunchEndStudent1 = 0;
        int lunchEndStudent2 = 0;
        Vertex<T, Integer> temp1 = head;     //pointer for s1
        Vertex<T, Integer> temp2 = head;     //pointer for s2
        Vertex<T, Integer> student1 = head;
        Vertex<T, Integer> student2 = head;
        if (head==null) {
            return false;
        }
        //Traverse through graph to find matching s1 and s2 vertex
        while (temp1!=null || temp2!=null) {
            if ( temp1.vertexInfo.compareTo(s1) == 0 ) {
                student1 = temp1;
                int lunchEndS1 = student1.lunchStart + student1.lunchPeriod;
                lunchEndStudent1 = student1.fixTime(lunchEndS1);
            }
            if (temp2.vertexInfo.compareTo(s2) == 0) {
                student2 = temp2;
                int lunchEndS2 = student2.lunchStart + student2.lunchPeriod;
                lunchEndStudent2 = student2.fixTime(lunchEndS2);
            }
            temp1 = temp1.nextVertex;
            temp2 = temp2.nextVertex;
        }
        if ((student2.lunchStart==student1.lunchStart || lunchEndStudent2==lunchEndStudent1) || (student2.lunchStart>=student1.lunchStart && lunchEndStudent2<=lunchEndStudent1) || (student2.lunchStart>student1.lunchStart && student2.lunchStart<lunchEndStudent1) || (lunchEndStudent2>student1.lunchStart && lunchEndStudent2<lunchEndStudent1) && tableSize<=3) {
            tableSize++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Related to Event 1
     * @param v
     * @param exp
     * @param a
     * @return the total rep with updated reputation points
     */    public int addRepEvent1(T v, boolean exp, int a){
        if (!hasVertex(v))
            return 0;
        // a==1 is for teacher
        if(a==1) {
            Vertex<T, Integer> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0 && exp) {
                    return temp.totalRep(10);
                } else if (temp.vertexInfo.compareTo(v) == 0 && !exp) {
                    return temp.totalRep(2);
                }
                temp = temp.nextVertex;
            }
        // else if a!=1 is for student
        }else{
            Vertex<T, Integer> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0 && exp) {
                    return temp.totalRep(2);
                }
                temp = temp.nextVertex;
            }
        }
        return 0;
    }

    // addRep method for EVENT 2
    //tak setel lagi
    public int addRepEvent2(T v, boolean msg){
        if (!hasVertex(v))
            return 0;
        Vertex<T,Integer> temp = head;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(v) == 0 && msg){
                return temp.totalRep(10);
            }else if(temp.vertexInfo.compareTo(v) == 0 && !msg){
                return temp.totalRep(2);
            }
            temp = temp.nextVertex;
        }
        return 0;
    }


    //------------------ Ken thompson-----------------------------------------------


    private ArrayList<Vertex<T, Integer>> getAdjacentObjects(Vertex<T, Integer> target) {
        ArrayList<Vertex<T, Integer>> list = new ArrayList<>();
        Edge<T, Integer> currentEdge = target.firstEdge;
        while (currentEdge != null) {
            list.add(currentEdge.toVertex);
            currentEdge = currentEdge.nextEdge;
        }
        return list;
    }

    /**
     * Find the path from source to destination
     * @param source
     * @param destination
     * @return the path start from source and end at destination.
     * */

    public ArrayList<T> dfs(T source, T destination) {
        Stack<T> pathStack = new Stack<>();
        ArrayList<T> visited = new ArrayList<>();
        Vertex<T, Integer> start = getVertexObject(source);

        ArrayList<T> path = new ArrayList<>();
        path.add(source);
        dfsUtil(destination, pathStack, visited, start);
        while(!pathStack.isEmpty()){
            path.add(pathStack.pop());
        }
        return path;
    }
    private boolean dfsUtil(T destination, Stack<T> path, ArrayList<T> visited, Vertex<T, Integer> current) {
        if (visited.contains(current.vertexInfo)) {
            return false;
        } else if (current.vertexInfo.compareTo(destination) == 0) {
            return true;
        }

        visited.add(current.vertexInfo);
        ArrayList<Vertex<T, Integer>> adjacent = getAdjacentObjects(current);
        for (Vertex<T, Integer> temp : adjacent) {
            if (dfsUtil(destination, path, visited, temp)) {
                path.push(temp.vertexInfo);
                return true;
            }
        }
        return false;
    }

    public String hop(T source, T destination) {
        String note;
        int hop = dfs(source,destination).indexOf(destination);
        if (hop <= 6 && hop!=0)
            note = "Yeay! you both are connected.";
        else
            note = "Sorry, you are too far from him!";
        return note + "\nYour hop to Ken Thompson is: " + hop;
    }

}
