
import java.util.ArrayList;


public class Student{
    int studentIndex;
    Reputation rep;
    int dive;
    int lunchStart;
    int lunchPeriod;
    ArrayList<Student> friends;

    public Student(int studentIndex) {
        this.studentIndex = studentIndex;
        rep = null;
        dive = (int)(Math.random()*99)+1;
        lunchStart = (int)(Math.random()*300)+1100;
        lunchPeriod = (int)(Math.random()*53)+6;
        friends = null;
    }
    
    public boolean hasRep(Student relativeTo){
        Reputation current = rep;
        while(current!=null){
            if(current.relativeTo.equals(relativeTo)){
                return true;
            }
            current = current.nextRep;
        }
        return false;
    }
    
    public boolean addRep(int point, Student relativeTo){
        if(hasRep(relativeTo)){
            return false;
        }
        Reputation newRep = new Reputation(point, relativeTo, rep);
        this.rep = newRep;
        return true;
    }
    
    public boolean addRepPoint(int point, Student relativeTo){
        if(!hasRep(relativeTo)){
            return false;
        }
        Reputation current = rep;
        while(current!=null){
            if(current.relativeTo.equals(relativeTo)){
                current.point += point;
                return true;
            }
            current = current.nextRep;
        }
        return false;
    }
    
    public int getRepPoint(Student relativeTo){
        Reputation current = rep;
        while(current!=null){
            if(current.relativeTo.equals(relativeTo)){
                return current.point;
            }
            current = current.nextRep;
        }
        return 0;
    }
    
    public boolean addFriend(Student newFriend){
        if(friends.contains(newFriend)){
            return false;
        }
        friends.add(newFriend);
        return true;
    }
    
    public boolean equals(Student obj) {
        return studentIndex == obj.studentIndex;
    }
    
    /**
     * Event 1
     * @param s
     * @param goodExp (true if good experience else false)
     */
    public void teach(Student s, boolean goodExp){
        addFriend(s);
        if(goodExp){
            addRep(10, s);
        }
        else{
            addRep(2, s);
        }
    }
    
    /**
     * Event 2
     * @param talkTo (Student who talks with this student)
     * @param talkAround (Student they talk around) 
     * @param goodMsg  (True if it is good message else false)
     */
    public void chat(Student talkTo, Student talkAround, boolean goodMsg){
        int pTemp = talkAround.getRepPoint(this);
        pTemp = goodMsg? (int)(pTemp*0.5): pTemp*-1;
        if(talkAround.hasRep(talkTo)){
            talkAround.addRepPoint(pTemp, talkTo);
        }
        else{
            talkAround.addRep(pTemp, talkTo);
        }
    }
}
