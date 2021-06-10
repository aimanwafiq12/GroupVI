package com.GroupProjectAssignment;

import java.util.ArrayList;
import java.util.Random;

public class WeightedGraph <T extends Comparable<T>> {
    private java.util.LinkedList<T> que = new java.util.LinkedList<>();
    private java.util.LinkedList<T> visited = new java.util.LinkedList<>();
    
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
        while (temp!=null) {
            System.out.print("# " + temp.vertexInfo + " : " );
            Edge<T,Integer> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
                System.out.print("[" + temp.vertexInfo + "," + currentEdge.toVertex.vertexInfo +"] " );
                currentEdge=currentEdge.nextEdge;
            }
            System.out.println();
            temp=temp.nextVertex;
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
            System.out.println("Vertex " + v + " cannot be found!");
        }
        else{
            Vertex<T,Integer> temp = head;
            while(temp!=null){
                if(temp.vertexInfo.compareTo(v)==0){
                    System.out.println("Current reputation: " + getRep(v));
                    System.out.println("Diving Rate: " + temp.dive + "\nLunch Starting Time: " + temp.lunchStart + "\nLunch Period = " + temp.lunchPeriod);
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

    /** For Event 3
     * Check and display the lunch time for the specified student
     * User can only have lunch with students that have the same lunch time range with him/her
     * @param studentN
     * @return a statement whether can have lunch with the specified student or not
     */
    public String checkLunchTime(T studentN) {
        int lunchEndStudent;
        int lunchEndUser;
        String str1 = "";
        Vertex<T, Integer> student = head;  //pointer for the specified student
        Vertex<T, Integer> user = head;     //pointer for the user (student[0])
        int lunchEndU = user.lunchStart + user.lunchPeriod;
        lunchEndUser = user.fixTime(lunchEndU);
        if (head==null) {
            return "No graph";
        }
            //Traverse through graph to find matching studentN vertex
            while (student!=null) {
                if ( student.vertexInfo.compareTo(studentN) == 0) {
                    int lunchEndS = student.lunchStart + student.lunchPeriod;
                    lunchEndStudent = student.fixTime(lunchEndS);
                    if ((student.lunchStart==user.lunchStart || lunchEndStudent==lunchEndUser) || (student.lunchStart>=user.lunchStart && lunchEndStudent<=lunchEndUser) || 
                        (student.lunchStart>user.lunchStart && student.lunchStart<lunchEndUser) || (lunchEndStudent>user.lunchStart && lunchEndStudent<lunchEndUser) &&
                        tableSize<=3) {
                        user.totalRep(1);   //user gained 1 rep point
                        tableSize++;        //table is occupied with 1 student excluding user
                        str1 = "You can have lunch with \n#" + studentN + "\nLunchStart: " + student.lunchStart + "\nLunchEnd: " + student.fixTime(lunchEndStudent) + "\nLunchPeriod: " + student.lunchPeriod + "\nReputation +1";
                    }
                    else {
                        str1 = "You cannot hv lunch with " + studentN + "\nLunchStart: " + student.lunchStart + "\nLunchEnd: " + student.fixTime(lunchEndStudent) + "\nLunchPeriod: " + student.lunchPeriod;
                    }
                }
                student = student.nextVertex;
            }
        return str1;
    }
    
    /**
     * Related to Event 3
     * @param studentN
     * @return true if the user can have lunch together with the specified student
     */
    public boolean checkRep(T studentN) {
        int lunchEndStudent;
        int lunchEndUser;
        Vertex<T, Integer> student = head;  //pointer for the specified student
        Vertex<T, Integer> user = head;     //pointer for the user (student[0])
        int lunchEndU = user.lunchStart + user.lunchPeriod;
        lunchEndUser = user.fixTime(lunchEndU);     
        if (head==null) {
            return false;
        }
            //Traverse through graph to find matching studentN vertex
            while (student!=null) {
                if ( student.vertexInfo.compareTo(studentN) == 0) {
                    int lunchEndS = student.lunchStart + student.lunchPeriod;
                    lunchEndStudent = student.fixTime(lunchEndS);
                    if ((student.lunchStart==user.lunchStart || lunchEndStudent==lunchEndUser) || (student.lunchStart>=user.lunchStart && lunchEndStudent<=lunchEndUser) || 
                        (student.lunchStart>user.lunchStart && student.lunchStart<lunchEndUser) || (lunchEndStudent>user.lunchStart && lunchEndStudent<lunchEndUser) &&
                        tableSize<=3) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                student = student.nextVertex;
            }
        return false;
    }
    
    //Trying out bfs for six degree of ken thompson
    /*
    public void enqueue(T e) {
        ///ArrayList<T> list = new ArrayList();
        //list.add(e);
        //return list;
        que.addLast(e);
    }

    
    public void enqueue(ArrayList<T> e) {
        //ArrayList<T> list = new ArrayList();
        //list.add(e);
        //return list;
        que.addLast(e);
    }
    
    public T dequeue() {
        //ArrayList<T> list = new ArrayList();;
        //list.remove(0);
        //return list;
        return que.removeFirst();
    }
    
    public ArrayList<T> getAdjacent (T v)  {
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
    
    public boolean bfs(T destination) {
        Vertex<T, Integer> current = head;
        ArrayList<T> adjacent = new ArrayList<>();
        int n = 0;
        if (head==null) {
            return false;
        }
        //Traverse through graph 
        while (current!=null) {
            enqueue(current.vertexInfo);    //add the current element to the queue
            visited.add(dequeue());         //dequeue the element and add to visited list
            adjacent = getNeighbours(visited.get(n));
            if (que.contains(adjacent))
                continue;
            else
                enqueue(adjacent);
            //enqueue(getAdjacent(visited.get(n)));     
        }
        n++;
        current = current.nextVertex;
        return false;
   }
*/
}

